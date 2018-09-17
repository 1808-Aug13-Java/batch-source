/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/service/http/Attic/HttpConnectionHandler.java,v 1.26.2.5 2001/11/06 15:59:54 marcsaeg Exp $
 * $Revision: 1.26.2.5 $
 * $Date: 2001/11/06 15:59:54 $
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

import org.apache.tomcat.service.*;
import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.tomcat.core.*;
import org.apache.tomcat.util.*;
import org.apache.tomcat.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class HttpConnectionHandler  implements  TcpConnectionHandler {

    private int timeout = 300000;  // 5 minutes

    boolean secure=false;
    ContextManager contextM;
    
    public HttpConnectionHandler() {
	super();
    }

    public void setAttribute(String name, Object value ) {
	if("context.manager".equals(name) ) {
	    contextM=(ContextManager)value;
	}
	if("secure".equals(name) )
	    setSecure(true);
    }

    public void setSecure( boolean b ) {
	secure=b;
    }

    public void setServer( Object  contextM ) {
	this.contextM=(ContextManager)contextM;
    }

    public Object[] init() {
	if(reuse) return null;
	Object thData[]=new Object[3];
	HttpRequestAdapter reqA=new HttpRequestAdapter();
	HttpResponseAdapter resA=new HttpResponseAdapter();
	contextM.initRequest( reqA, resA );
	thData[0]=reqA;
	thData[1]=resA;
	thData[2]=null;
	return  thData;
    }

    //    static Vector pool=new Vector();
    Object pool[]=new Object[100]; // XXX 
    int pos=0;
    static boolean reuse=true;

    public void setReuse( boolean b ) {
	reuse=b;
	System.out.println("Reuse = " + b );
    }
    // XXX
    //    Nothing overriden, right now AJPRequest implment AJP and read everything.
    //    "Shortcuts" to be added here ( Vhost and context set by Apache, etc)
    // XXX handleEndpoint( Endpoint x )
    public void processConnection(TcpConnection connection, Object thData[]) {
	Socket socket=null;
	HttpRequestAdapter reqA=null;
	HttpResponseAdapter resA=null;

	//	System.out.println("New Connection");
	try {
	    // XXX - Add workarounds for the fact that the underlying
	    // serverSocket.accept() call can now time out.  This whole
	    // architecture needs some serious review.
	    if (connection == null)
		return;
	    //	    System.out.print("1");
	    socket=connection.getSocket();
	    if (socket == null)
		return;
        socket.setSoTimeout(timeout);
	    //	    System.out.print("2");
	    InputStream in=socket.getInputStream();
	    OutputStream out=socket.getOutputStream();
	    if( thData != null ) {
		reqA=(HttpRequestAdapter)thData[0];
		resA=(HttpResponseAdapter)thData[1];
		if( reqA!=null ) reqA.recycle();
		if( resA!=null ) resA.recycle();
		//		System.out.println("Request ID " + thData[2]);
	    }
	    // No thData - use Pool

	    if( reuse && ( reqA==null || resA==null ) ) {
		int myPos=-1;
		synchronized( this ) {
		    if( pos>0 ) {
			pos--;
			myPos=pos; // >=0
			reqA =  (HttpRequestAdapter)pool[pos]; // (HttpRequestAdapter)pool.lastElement();
			if( reqA==null )
			    System.out.println("Get Obj " + pos + " " + reqA);
			else
			    resA= (HttpResponseAdapter)reqA.getResponse();
		    }
		}
		if( reqA==null ) {
		    //System.out.println("XXX REQUEST_IMPL new " + pool.size());
		    reqA=new HttpRequestAdapter();
		    resA=new HttpResponseAdapter();
		    contextM.initRequest( reqA, resA );
		} 
		reqA.recycle();
		resA.recycle();
	    } 
	    
	    if( reqA==null || resA==null ) {	
		//System.out.println("XXX NO POOL " );
		reqA=new HttpRequestAdapter();
		resA=new HttpResponseAdapter();
		contextM.initRequest( reqA, resA );
	    }
	    
	    resA.setRequest(reqA);
	    reqA.setResponse( resA );
	    
	    reqA.setSocket( socket );

	    connection.getEndpoint().getServerSocketFactory().preProcessRequest( socket, reqA );

	    resA.setOutputStream( out );

	    reqA.readNextRequest(resA);

	    int contentLength = reqA.getContentLength();
	    if (contentLength != -1) {
		BufferedServletInputStream sis =
		    (BufferedServletInputStream)reqA.getInputStream();
		sis.setLimit(contentLength);
	    }

	    // If this connection handler was declared as "secure",
	    // mark this in the request
	    if( secure ) {
		reqA.setScheme( "https" );
	    }
	    
	    contextM.service( reqA, resA );

	    try {
               InputStream is = socket.getInputStream();
               int available = is.available ();
	       
               // XXX on JDK 1.3 just socket.shutdownInput () which
               // was added just to deal with such issues.

               // skip any unread (bogus) bytes
               if (available > 1) {
                   is.skip (available);
               }
	    }catch(NullPointerException npe) {
		// do nothing - we are just cleaning up, this is
		// a workaround for Netscape \n\r in POST - it is supposed
		// to be ignored
	    }
	}
	catch(java.net.SocketException e) {
	    // SocketExceptions are normal
	    contextM.doLog( "SocketException reading request, ignored", e, Logger.INFORMATION);
	}
    catch (java.io.InterruptedIOException ioe) {
        // InterruptedIOException are timeout and we must abort process
        ioe.printStackTrace();
        contextM.doLog( "Timeout while reading request, aborting", ioe, Logger.ERROR);
        try{
            if (socket != null)
                socket.close ();
        }
        catch (IOException e){
             /* ignore */
        }
    }	
	catch (java.io.IOException e) {
	    // IOExceptions are normal 
	    contextM.doLog( "IOException reading request, ignored", e, Logger.INFORMATION);
	}
	// Future developers: if you discover any other
	// rare-but-nonfatal exceptions, catch them here, and log
	// with INFORMATION level.
	catch (Throwable e) {
	    // any other exception or error is odd. Here we log it
	    // with "ERROR" level, so it will show up even on
	    // less-than-verbose logs.
	    contextM.doLog( "Error reading request, ignored", e, Logger.ERROR);
	} 
	finally {
	    // recycle kernel sockets ASAP
	    try { if (socket != null) socket.close (); }
	    catch (IOException e) { /* ignore */ }
        }
	if( reuse ) {
	    synchronized( this ) {
		if( pos<pool.length && reqA!= null ) {
		    //System.out.println("Set Obj " + pos + " " + reqA);
		    pool[pos]= reqA ;
		    pos++;
		}
	    }
	}

	//	System.out.print("6");
    }


}
