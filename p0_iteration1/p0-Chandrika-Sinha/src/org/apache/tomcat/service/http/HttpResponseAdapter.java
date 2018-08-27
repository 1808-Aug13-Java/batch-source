/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/service/http/Attic/HttpResponseAdapter.java,v 1.11.2.2 2001/07/30 00:57:34 marcsaeg Exp $
 * $Revision: 1.11.2.2 $
 * $Date: 2001/07/30 00:57:34 $
 *
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

// no "buffering" - we send the status and headers as soon as
// the method is called.

// this method is _not_ thread-safe. ( if 2 threads call ServletOutputStream.out

/**
 */
public class HttpResponseAdapter extends  ResponseImpl {
    protected OutputStream sout;

    protected static final int DEFAULT_HEAD_BUFFER_SIZE = 1024;
    protected byte[] buffer = new byte[DEFAULT_HEAD_BUFFER_SIZE];
    protected int bufferCount = 0;
    
    public HttpResponseAdapter() {
        super();
    }

    public void recycle() {
	super.recycle();
	bufferCount=0;
    }

    public void setOutputStream(OutputStream os) {
	sout = os;
    }

    static final byte CRLF[]= { (byte)'\r', (byte)'\n' };
    
    public void endHeaders()  throws IOException {

	super.endHeaders();
	
	sendStatus( status, ResponseImpl.getMessage( status ));

	int count=headers.size();
	for( int i=0; i<count; i++ ) {
	    MimeHeaderField field=headers.getField( i );
	    // response headers are set by the servlet, so probably we have only
	    // Strings.
	    // XXX date, cookies, etc shoud be extracted from response
	    printHead( field.getName() );
	    printHead(": ");
	    printHead( field.getValue() );
	    printHead("\r\n");
	}
	
	printHead( "\r\n" );

	sout.write( buffer, 0, bufferCount );
	sout.flush();
    }

    /** Needed for AJP  support - the only difference between AJP response and
	HTTP response is the status line
    */
    protected void sendStatus( int status, String message ) throws IOException {

	printHead("HTTP/1.0 ");
	printHead(String.valueOf(status));
	if(message!=null) {
	    printHead(" ");
	    printHead(message);
	}
	printHead("\r\n");
	// Hack: set Date header.
	// This method is overriden by ajp11, ajp12 - so date will not be set
	// for any of those ( instead the server will generate the date )
	// This avoids redundant setting of date ( very expensive ).
	// XXX XXX Check if IIS, NES do generate the date
	MimeHeaderField dateH= headers.find( "Date" );
	if( dateH == null ) {
	    // no date header set by user
	    dateH=headers.putHeader();
	    dateH.setName("Date");
	    dateH.setDateValue(System.currentTimeMillis());
	    // will reuse the HttpDate instance
	}
	
	// Servlet Engine header will be set per/adapter - smarter adapters will
	// not send it every time ( have it in C side ), and we may also want
	// to add informations about the adapter used 
	if( request.getContext() != null)
	    setHeader("Servlet-Engine", request.getContext().getEngineHeader());
    }

    public void doWrite( byte buffer[], int pos, int count) throws IOException {
	sout.write( buffer, pos, count);
    }

    // From BufferedServletOutputStream
    // XXX will be moved in a new in/out system, temp. code
    // Right now it's not worse than BOS
    protected void printHead( String s ) {
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
	    if( bufferCount >= buffer.length ) {
		byte bufferNew[]=new byte[ buffer.length * 2 ];
		System.arraycopy( buffer,0, bufferNew, 0, buffer.length );
		buffer=bufferNew;
	    }
	    buffer[bufferCount] = (byte)c;
	    bufferCount++;
	}	
    }
					
}
