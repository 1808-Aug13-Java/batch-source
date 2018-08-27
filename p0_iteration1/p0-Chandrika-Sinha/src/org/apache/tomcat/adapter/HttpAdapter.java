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
package org.apache.tomcat.adapter;

import org.apache.tomcat.core.*;
import org.apache.tomcat.util.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.http.*;


// We implement Response part of the adapter first, Request will be next

/**
 */
public final class HttpAdapter implements BufferListener {
    protected OutputStream sout;
    static int debug=0;
    ContextManager contextM;

    // hack - quick solution to keep only one object in pool
    RequestImpl req;
    ResponseImpl res;

    RecycleBufferedInputStream in;
    

    public static final int MAX_HEAD_LEN = 8 * 1024;
    byte buf[]=new byte[ MAX_HEAD_LEN ];
    int off=0;
    int count=0;

    // input stream
    int readLimit=-1;
    int readCount=0;
    
    private Socket socket;

    public static final String DEFAULT_CHARACTER_ENCODING = "8859_1";
    
    
    public HttpAdapter() {
        super();
    }

    // -------------------- Methods used by ConnectionHandler
    public void init(ContextManager contextM) {
	ByteBuffer ob=new ByteBuffer();
	ByteBuffer ib=new ByteBuffer();
	ob.addBufferListener( this );
	ib.addBufferListener( this );

	req=new RequestImpl();
	res=new ResponseImpl();
	res.setOutputBuffer( ob );
	req.setInputBuffer( ib );
	this.contextM=contextM;
	contextM.initRequest( req, res );
    }
    
    public void readNextRequest() throws IOException {
	readNextRequest( req, res );
	int contentLength = req.getContentLength();
	if (contentLength != -1) {
	    readLimit=contentLength;
	}
    }

    public void setSocket(Socket socket) throws IOException {
	if( in == null )
	    in =  new RecycleBufferedInputStream( socket.getInputStream());
	else
	    in.setInputStream( socket.getInputStream() );
	sout= socket.getOutputStream();
	
        this.socket = socket;
    }

    public Request getRequest() {
	return req;
    }

    public Response getResponse() {
	return res;
    }
    
    // -------------------- Other util methods

    public void recycle() {
 	bufferCount=0;
	readLimit=-1;
	readCount=0;
    }

    void log( String s ) {
	System.out.println("HttpAdapter: " + s );
    }

    public void setDebug(int debug) {
	this.debug=debug;
    }


    // -------------------- Buffer listener - implement IN/OUT
    
    /** Read from the input stream to fill a buffer.
     *  We read _once_ ( it may be a partial read, if not enough
     *  data available). Note that we read at least on byte -
     *  it's blocking IO.
     */
    public void bufferEmpty( BufferEvent ev ) {
	try {
	    // Find if this is the first chunk , if so do send head 
	    if( debug > 0 ) log( "Buffer empty event ");
	    
	    ByteBuffer bb=(ByteBuffer)ev.getSource();
	    ResponseImpl resp=(ResponseImpl)bb.getParent();
	    int len=ev.getLength();
	    if( readLimit >=0 ) {
		if( readCount + len >= readLimit ) {
		    len = readLimit - readCount;
		}
	    }
	    int n=0;
	    if( len >= 0 )
		n=in.read( ev.getByteBuffer(), ev.getOffset(), len );
	    ev.setLength( n );
	    readCount +=n;
	    if( debug > 0 ) log( "Read: " + readLimit + " " + readCount + " " + n + " " + len + " " + ev.getLength());
	} catch( IOException ex ) {
	    ex.printStackTrace();
	}
    }

    // Called when  we need to send  data
    public void bufferFull( BufferEvent ev ) {
	try {
	    // Find if this is the first chunk , if so do send head 
	    if( debug > 0 ) log( "Buffer full event ");
	    
	    ByteBuffer bb=(ByteBuffer)ev.getSource();
	    ResponseImpl resp=(ResponseImpl)bb.getParent();
	    
	    if( ! resp.isBufferCommitted() ) {
		// notify -
		resp.notifyEndHeaders();
		sendHead( resp );
		resp.setBufferCommitted( true );
	    }
	    sout.write( ev.getByteBuffer(), ev.getOffset(), ev.getLength() );
	} catch( IOException ex ) {
	    if( ! "Broken pipe".equals(ex.getMessage())) {
		ex.printStackTrace();
	    }
	}
    }


    // -------------------- Implementation methods--------------------
    // Response - sending the head
    
    protected static final int DEFAULT_HEAD_BUFFER_SIZE = 1024;
    protected byte[] headBuffer = new byte[DEFAULT_HEAD_BUFFER_SIZE];
    protected int bufferCount = 0;
    
    private void sendHead( ResponseImpl resp )  throws IOException {
	//	super.endHeaders(); // CM will notify endHeaders(), we just need to
	// do our job
	if( debug > 0 ) log( "Sending head ");

	Request request=resp.getRequest();
	int status= resp.getStatus();
	// XXX inefficient - will create 2 strings and a hashtable lookup
	String message=	ResponseImpl.getMessage( status );
	
	MimeHeaders headers=resp.getMimeHeaders();
	
	headAppend("HTTP/1.0 ");
	headAppend(String.valueOf(status));
	if(message!=null) {
	    headAppend(" ");
	    headAppend(message);
	}
	headAppend("\r\n");

	// Hack: set Date header. We need to implement append(date)
	MimeHeaderField dateH= headers.find( "Date" );
	if( false && dateH == null ) {
	    // no date header set by user
	    dateH=headers.putHeader();
	    dateH.setName("Date");
	    dateH.setDateValue(System.currentTimeMillis());
	    // will reuse the HttpDate instance
	}
	
	// Servlet Engine header will be set per/adapter - smarter adapters will
	// not send it every time ( have it in C side ), and we may also want
	// to add informations about the adapter used 
	if( request.getContext() != null) {
	    headAppend( "Servlet-Engine: ");
	    headAppend( request.getContext().getEngineHeader());
	}

	int count=headers.size();
	for( int i=0; i<count; i++ ) {
	    MimeHeaderField field=headers.getField( i );
	    // response headers are set by the servlet, so probably we have only
	    // Strings.
	    // XXX date, cookies, etc shoud be extracted from response
	    headAppend( field.getName() );
	    headAppend(": ");
	    headAppend( field.getValue() );
	    headAppend("\r\n");
	}
	
	headAppend( "\r\n" );

	sout.write( headBuffer, 0, bufferCount );
	sout.flush();
    }

    // From BufferedServletOutputStream
    // XXX will be moved in a new in/out system, temp. code
    // Right now it's not worse than BOS
    protected void headAppend( String s ) {
	if (s==null) s="null";

	int len = s.length();
	for (int i = 0; i < len; i++) {
	    char c = s.charAt (i);
	    
	    //
	    // XXX NOTE:  This is clearly incorrect for many strings,
	    // but is the only consistent approach within the current
	    // servlet framework.  It must suffice until servlet output
	    // streams properly encode their output.
	    //
	    if ((c & 0xff00) != 0) {	// high order byte must be zero
		// XXX will go away after we change the I/O system
		System.out.println("Header character is not iso8859_1, not supported yet: " + c ) ;
	    }
	    if( bufferCount >= headBuffer.length ) {
		byte bufferNew[]=new byte[ headBuffer.length * 2 ];
		System.arraycopy( headBuffer,0, bufferNew, 0, headBuffer.length );
		headBuffer=bufferNew;
	    }
	    headBuffer[bufferCount] = (byte)c;
	    bufferCount++;
	}	
    }

    // -------------------- Request Methods --------------------

    private void readNextRequest(Request req, Response response) throws IOException {
	// Read the full buffer - this should mean the full request
	// and maybe part of the body or next request

	count = BuffTool.readLine(in, buf, 0, buf.length);
	
	if (count < 0  ) {
	    //	    System.out.println("No request");
	    // 	    System.out.println("Request too long ");
	    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	    return;
	}
	
	int status=processRequestLine(req);
	if( status != 0 ) {
	    response.setStatus( status );
	    return;
	}

	// for 0.9, we don't have headers!
	if (req.getProtocol() !=null) { // all HTTP versions with protocol also have headers ( 0.9 has no HTTP/0.9 !)
	    status=readHeaders( req );
	    if( status!= 0 ) {
		response.setStatus( status );
		return;
	    }
	}

	
	//req.setServerPort( socket.getLocalPort() );
	//req.setLocalHost( socket.getLocalAddress().getHostName() );
	// req.setRemoteAddr( socket.getInetAddress().getHostAddress());
	// req.setRemoteHost( socket.getInetAddress().getHostName());
    }


    /**
     * Reads header fields from the specified servlet input stream until
     * a blank line is encountered.
     * @param in the servlet input stream
     * @exception IllegalArgumentException if the header format was invalid 
     * @exception IOException if an I/O error has occurred
     */
    public int readHeaders( Request req )  throws IOException {
	MimeHeaders headers = req.getMimeHeaders();
	
	// use pre-allocated buffer if possible
	off = count; // where the request line ended
	
	while (true) {
	    int start = off;

	    while (true) {
		int len = buf.length - off;

		if (len > 0) {
		    len = BuffTool.readLine(in, buf, off, len);

		    if (len == -1) {
			return HttpServletResponse.SC_BAD_REQUEST;
		    }
		}

		off += len;

		if (len == 0 || buf[off-1] == '\n') {
		    break;
		}

		// overflowed buffer, so temporarily expand and continue
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

	    int status= parseHeaderFiled(mhf, buf, start, off - start);
	    if( status != 0 ) {
		// error parsing header
		return status;
	    }
	}
	return 0;
    }

    /**
     * Parses a header field from a subarray of bytes.
     * @param b the bytes to parse
     * @param off the start offset of the bytes
     * @param len the length of the bytes
     * @exception IllegalArgumentException if the header format was invalid
     */
    public int parseHeaderFiled(MimeHeaderField mhf, byte[] b, int off, int len)
    {
	int start = off;
	byte c;
	int end=off+len;

	int nameEnd= BuffTool.findChars( b, start, end, NAME_DELIMS );
	if( nameEnd < 0 || b[nameEnd]=='\r' || b[nameEnd]=='\n' ) {
	    System.out.println("Parse error, empty line: " + new String( b, off, len ));
	    return HttpServletResponse.SC_BAD_REQUEST;
	}
	
	mhf.setName(b, start, nameEnd - start);

	// skip spaces. We should find ":" after the spaces
	int sepIdx= BuffTool.findNotChars( b, nameEnd, end, SPACE_DELIMS );

	if (b[sepIdx] != ':') {
	    System.out.println("Parse error, missing : in  " + new String( b, off, len ));
	    System.out.println("Full  " + new String( b, 0, b.length ));
	    return  HttpServletResponse.SC_BAD_REQUEST;
	}

	// skip spaces
	int valueStart= BuffTool.findNotChars( b, sepIdx + 1, end, SPACE_DELIMS);
	if( valueStart < 0 ) {
	    System.out.println("Parse error, no value after : " + new String( b, off, len ));
	    System.out.println("Full  " + new String( b, 0, b.length ));
	    return  HttpServletResponse.SC_BAD_REQUEST;
	}

	mhf.setValue(b, valueStart, end - valueStart );
	return 0;
    }

    static final byte NAME_DELIMS[]={ (byte)':', (byte)' ', (byte)'\t', (byte)'\r', (byte)'\n' };
    static final byte SPACE_DELIMS[]={ (byte)' ', (byte)'\t'};
    
    /** Parse the request line and set the fields of req
     */
    private int processRequestLine(Request req)
	throws IOException
    {
	off=0;
	
	// if end of line is reached before we scan all 3 components -
	// we're fine, off=count and remain unchanged
	
	if( buf[count-1]!= '\r' && buf[count-1]!= '\n' ) {
	    return HttpServletResponse.SC_REQUEST_URI_TOO_LONG;
	}	    
	
	int startMethod = BuffTool.findNotChars( buf, 0, count, SPACE_DELIMS);
	if( startMethod == -1 ) return HttpServletResponse.SC_BAD_REQUEST;
	int endMethod = BuffTool.findChars( buf, startMethod, count, SPACE_DELIMS );
	if( endMethod == -1 ) return HttpServletResponse.SC_BAD_REQUEST;
	
	req.setMethod( new String( buf, startMethod, endMethod - startMethod ));

	int startReq= BuffTool.findNotChars( buf, endMethod, count, SPACE_DELIMS);
	if( startReq == -1 ) return  HttpServletResponse.SC_BAD_REQUEST;
	int endReq= BuffTool.findChars( buf, startReq, count, SPACE_DELIMS);
	if( endReq != -1 ) {
	    // we have a space after query string, we might have protocol

	    int startProto= BuffTool.findNotChars(buf, endReq, count, SPACE_DELIMS);
	    if( startProto != -1 ) {
		// yes, we do have protocol
		int endProto=BuffTool.findChars( buf, startProto, count, SPACE_DELIMS);
		// no ' ' found after end, that's ok
		if( endProto == -1 ) endProto = count;
		req.setProtocol( new String( buf, startProto, endProto-startProto ));
	    } else {
		req.setProtocol(null);
	    }
	} else {
	    // no protocol
	    req.setProtocol(null);
	    endReq=count;
	}
	
	int qryIdx= BuffTool.findChar( buf, startReq, endReq, '?' );
	if( qryIdx <0 ) {
	    req.setRequestURI( new String( buf, startReq, endReq - startReq ));
	} else {
	    req.setRequestURI( new String( buf, startReq, qryIdx - startReq ));
	    req.setQueryString( new String( buf, qryIdx+1, endReq - qryIdx -1 ));
	}

	return 0;
    }

    
}
