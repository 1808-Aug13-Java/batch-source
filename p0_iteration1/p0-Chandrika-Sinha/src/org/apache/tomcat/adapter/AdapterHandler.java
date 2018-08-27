/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/adapter/Attic/AdapterHandler.java,v 1.2 2000/06/23 02:16:13 costin Exp $
 * $Revision: 1.2 $
 * $Date: 2000/06/23 02:16:13 $
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
package org.apache.tomcat.adapter;

import org.apache.tomcat.service.*;
import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.tomcat.core.*;
import org.apache.tomcat.util.*;

public class AdapterHandler  implements  TcpConnectionHandler {
    
    ContextManager contextM;
    SimplePool pool = new SimplePool(60);
    public static boolean usePool=true;

    
    public AdapterHandler() {
	super();
    }

    public void setAttribute(String name, Object value ) {
	if("context.manager".equals(name) ) {
	    contextM=(ContextManager)value;
	}
    }
    
    public void setServer( Object  contextM ) {
	this.contextM=(ContextManager)contextM;
    }

    public Object[] init() {
	if( usePool ) return null;
        Object thData[]=new Object[1];
	thData[0]=createAdapter();
        return  thData;
    }

    HttpAdapter createAdapter() {
	//System.out.println("XXX REQUEST_IMPL new " + pool.size());
	HttpAdapter httpA=new HttpAdapter();
	httpA.init(contextM);
	return httpA;
    }
    
    public void processConnection(TcpConnection connection, Object thData[]) {
	Socket socket=null;
	Request reqA=null;
	Response resA=null;
	HttpAdapter httpA=null;

	//	System.out.println("New Connection");
	try {
	    if (connection == null)
		return;
	    socket=connection.getSocket();
	    if (socket == null)
		return;

	    if( usePool || thData==null || thData[0]==null) {
		httpA=(HttpAdapter)pool.get();
		if( httpA==null ) httpA=createAdapter();
	    } else {
		httpA =(HttpAdapter)thData[0];
	    }

	    httpA.setSocket( socket );

	    httpA.readNextRequest();

	    contextM.service( httpA.getRequest(), httpA.getResponse() );

	    httpA.recycle();
	    
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
	    } catch(java.net.SocketException ex) {
		// do nothing - same
	    }
	    //	    System.out.print("5");
	} catch (Exception e) {
	    contextM.log( "Error reading request " + e.getMessage());
	    e.printStackTrace();
	} finally {
	    // recycle kernel sockets ASAP
	    try { if (socket != null) socket.close (); }
	    catch (IOException e) { /* ignore */ }
        }
	if( usePool || thData==null || thData[0]==null ) {
	    pool.put( httpA );
	}

	//	System.out.print("6");
    }


}
