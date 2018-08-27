/*
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:  
 *       "This product includes software developed by the 
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written 
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * [Additional notices, if required by prior licensing conditions]
 *
 */ 


package org.apache.tomcat.service.http;

import org.apache.tomcat.core.*;
import org.apache.tomcat.util.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HttpRequestAdapter extends RequestImpl {
    private static StringManager sm1=
	StringManager.getManager("org.apache.tomcat.util");
    private Socket socket;
    private boolean moreRequests = false;
    RecycleBufferedInputStream sin;
    byte[] buf;
    int bufSize=2048; // default
    int off=0;
    int count=0;
    public static final String DEFAULT_CHARACTER_ENCODING = "8859_1";
    
    
    public HttpRequestAdapter() {
        super();
	buf=new byte[bufSize];
    }

    public void setSocket(Socket socket) throws IOException {
	if( sin==null)
	    sin = new RecycleBufferedInputStream ( socket.getInputStream());
	else
	    sin.setInputStream( socket.getInputStream());
	in = new BufferedServletInputStream(this);
        this.socket = socket;
    	moreRequests = true;
    }

    public void recycle() {
	super.recycle();
	off=0;
	count=0;
	if( sin!=null )  sin.recycle();
    }
    
    public Socket getSocket() {
        return this.socket;
    }

    public boolean hasMoreRequests() {
        return moreRequests;
    }
    
    public int doRead() throws IOException {
	return sin.read();
    }

    public int doRead(byte[] b, int off, int len) throws IOException {
	return sin.read(b, off, len);
    }

    public void readNextRequest(Response response) throws IOException {

	// Odd, but works: we use the ServletInputStream, which uses doRead.
	// We do implement doRead in Http protocol to return from the input
	// stream - it works for the normal body but also for the HTTP
	// head. The limit is set after ( and if ) we have a content-length.
	
	count = in.readLine(buf, 0, buf.length);

	if (count < 0 ) {
	    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	    return;
	}
	
	processRequestLine(response  );

	// for 0.9, we don't have headers!
	if (protocol!=null) { // all HTTP versions with protocol also have headers ( 0.9 has no HTTP/0.9 !)
	    readHeaders( headers, in  );
	}

	// XXX
	// detect for real whether or not we have more requests
	// coming
	moreRequests = false;	
    }


    /**
     * Reads header fields from the specified servlet input stream until
     * a blank line is encountered.
     * @param in the servlet input stream
     * @exception IllegalArgumentException if the header format was invalid 
     * @exception IOException if an I/O error has occurred
     */
    public void readHeaders( MimeHeaders headers, ServletInputStream in )  throws IOException {
	// use pre-allocated buffer if possible
	off = count; // where the request line ended
	
	while (true) {
	    int start = off;

	    while (true) {
		int len = buf.length - off;

		if (len > 0) {
		    len = in.readLine(buf, off, len);

		    if (len == -1) {
                        String msg =
                            sm1.getString("mimeHeader.connection.ioe");

			throw new IOException (msg);
		    }
		}

		off += len;

		if (len == 0 || buf[off-1] == '\n') {
		    break;
		}

		// overflowed buffer, so temporarily expand and continue

		// XXX DOS - if the length is too big - stop and throw exception
		byte[] tmp = new byte[buf.length * 2];

		System.arraycopy(buf, 0, tmp, 0, buf.length);
		buf = tmp;
	    }

	    // strip off trailing "\r\n"
	    if (--off > start && buf[off-1] == '\r') {
		--off;
	    }

	    if (off == start) {
		break;
	    }
	    
	    // XXX this does not currently handle headers which
	    // are folded to take more than one line.
	    MimeHeaderField mhf=headers.putHeader();
	    if( ! parseHeaderFiled(mhf, buf, start, off - start) ) {
		// error parsing header
		return;
	    }
	}
    }

    /**
     * Parses a header field from a subarray of bytes.
     * @param b the bytes to parse
     * @param off the start offset of the bytes
     * @param len the length of the bytes
     * @exception IllegalArgumentException if the header format was invalid
     */
    public boolean parseHeaderFiled(MimeHeaderField mhf, byte[] b, int off, int len)
    {
	int start = off;
	byte c;

	while ((c = b[off++]) != ':' && c != ' ') {
	    if (c == '\n') {
		System.out.println("Parse error, empty line: " + new String( b, off, len ));
		return false;
	    }
	}

	mhf.setName(b, start, off - start - 1);

	while (c == ' ') {
	    c = b[off++];
	}

	if (c != ':') {
	    System.out.println("Parse error, missing : in  " + new String( b, off, len ));
	    System.out.println("Full  " + new String( b, 0, b.length ));
	    return false;
	}

	while ((c = b[off++]) == ' ');

	mhf.setValue(b, off - 1, len - (off - start - 1));
	return true;
    }

    public int getServerPort() {
        if (serverPort != -1)
            return serverPort;
        String hostHeader = this.getHeader("host");
        if (hostHeader == null)
            serverPort = socket.getLocalPort();
        else {
            int i = hostHeader.indexOf(':');
            if (i > -1) {
                hostHeader = hostHeader.substring(i+1);
                try {
                    serverPort = Integer.parseInt(hostHeader);
                } catch (NumberFormatException pex) {
                }
            } else if (isSecure())
                serverPort = 443;
            else
                serverPort = 80;
        }
        return serverPort;
    }

    public String getServerName() {
	if(serverName!=null) return serverName;
	
	// XXX Move to interceptor!!!
	String hostHeader = this.getHeader("host");
	if (hostHeader != null) {
	    int i = hostHeader.indexOf(':');
	    if (i > -1) {
		hostHeader = hostHeader.substring(0,i);
	    }
	    serverName=hostHeader;
	    return serverName;
	}

	if (hostHeader == null) {
		// XXX
		// we need a better solution here
		InetAddress localAddress = socket.getLocalAddress();
		serverName = localAddress.getHostName();
	}
	return serverName;
    }
    
    
    public String getRemoteAddr() {
        return socket.getInetAddress().getHostAddress();
    }
    
    public String getRemoteHost() {
	return socket.getInetAddress().getHostName();
    }    

    /** Advance to first non-space
     */
    private  final int skipSpaces() {
	while (off < count) {
	    if ((buf[off] != (byte) ' ') 
		&& (buf[off] != (byte) '\t')
		&& (buf[off] != (byte) '\r')
		&& (buf[off] != (byte) '\n')) {
		return off;
	    }
	    off++;
	}
	return -1;
    }

    /** Advance to the first space
     */
    private  int findSpace() {
	while (off < count) {
	    if ((buf[off] == (byte) ' ') 
		|| (buf[off] == (byte) '\t')
		|| (buf[off] == (byte) '\r')
		|| (buf[off] == (byte) '\n')) {
		return off;
	    }
	    off++;
	}
	return -1;
    }

    /** Find a character, no side effects
     */
    private  int findChar( char c, int start, int end ) {
	byte b=(byte)c;
	int offset = start;
	while (offset < end) {
	    if (buf[offset] == b) {
		return offset;
	    }
	    offset++;
	}
	return -1;
    }

    private void processRequestLine(Response response)
	throws IOException
    {
	off=0;

	// if end of line is reached before we scan all 3 components -
	// we're fine, off=count and remain unchanged
	
	if( buf[count-1]!= '\r' && buf[count-1]!= '\n' ) {
	    response.setStatus(HttpServletResponse.SC_REQUEST_URI_TOO_LONG);
	    return;
	}	    
	
	int startMethod=skipSpaces();
	int endMethod=findSpace();

	int startReq=skipSpaces();
	int endReq=findSpace();

	int startProto=skipSpaces();
	int endProto=findSpace();

	if( startReq < 0   ) {
	    // we don't have 2 "words", probably only method
	    // startReq>0 => method is fine, request has at least one char
	    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	    protocol=null;
	    return;
	}
	
	method= new String( buf, startMethod, endMethod - startMethod );

	if( startProto < 0 ) {
	    protocol=null;
	} else {
	    if( endProto < 0 ) endProto = count;
	    protocol=new String( buf, startProto, endProto-startProto );
	}

	int qryIdx= findChar( '?', startReq, endReq );
	if( qryIdx <0 ) {
	    requestURI = new String( buf, startReq, endReq - startReq );
	} else {
	    requestURI = new String( buf, startReq, qryIdx - startReq );
	    queryString = new String( buf, qryIdx+1, endReq - qryIdx -1 );
	}

	//	System.out.println("XXX " + method + " " + requestURI + " " + queryString + " " + protocol );

    }

    
}
