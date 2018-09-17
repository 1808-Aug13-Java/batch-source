/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/service/Attic/SimpleTcpConnector.java,v 1.6.2.1 2000/07/27 18:16:22 costin Exp $
 * $Revision: 1.6.2.1 $
 * $Date: 2000/07/27 18:16:22 $
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


package org.apache.tomcat.service;

import org.apache.tomcat.util.*;
import org.apache.tomcat.core.*;
import org.apache.tomcat.net.*;
import java.io.*;
import java.net.*;
import java.util.*;

//import org.apache.tomcat.server.HttpServer;

/* Similar with MPM module in Apache2.0. Handles all the details related with
   "tcp server" functionality - thread management, accept policy, etc.
   It should do nothing more - as soon as it get a socket ( and all socket options
   are set, etc), it just handle the stream to ConnectionHandler.processConnection. (costin)
*/



/**
 * Connector for a TCP-based connector using the API in tomcat.service.
 * You need to set a "connection.handler" property with the class name of
 * the TCP connection handler
 *
 * @author costin@eng.sun.com
 * @author Gal Shachor [shachor@il.ibm.com]
 */
public class SimpleTcpConnector  extends TcpEndpointConnector implements ServerConnector  {
    // Attributes we accept ( to support the old model of
    // configuration, will be deprecated )
    public static final String VHOST_PORT="vhost_port";
    public static final String VHOST_NAME="vhost_name";

    // needed only as a "hack" to HttpServerConnector
    public static final String SERVER="server";
    public static final String VHOST_ADDRESS="vhost_address";
    public static final String SOCKET_FACTORY="socketFactory";


    public static final String PORT = "port";
    public static final String INET = "inet";
    public static final String HANDLER = "handler";

    // XXX define ConnectorException
    // XXX replace strings with sm.get...
    // XXX replace static strings with constants
    String handlerClassName;
    SimpleTcpEndpoint ep;
    TcpConnectionHandler con;

    ContextManager cm;

    private InetAddress address;
    private int port;

    int vport;

    private ServerSocketFactory socketFactory;
    private ServerSocket serverSocket;

    boolean running = true;

    public SimpleTcpConnector() {
    	ep = new SimpleTcpEndpoint();
    }

    public void start() throws Exception {
    	if(con==null)
    	    throw new Exception( "Invalid ConnectionHandler");

	con.setAttribute("context.manager",cm );
    	ep.setPort(port);
	ep.setAddress( address );
	if(socketFactory != null) {
	    ep.setServerSocketFactory( socketFactory );
	}
	ep.setConnectionHandler( con );
	ep.startEndpoint();
	cm.log("Starting endpoint port=\"" + port + "\" handler=\"" + con.getClass().getName() + "\" ");
    }

    public void stop() throws Exception {
	cm.log("Stoping endpoint port=\"" + port + "\" handler=\"" + con.getClass().getName() + "\" ");
    	ep.stopEndpoint();
    }

    public void setServer( Object ctx ) {
	this.cm=(ContextManager)ctx; 
    }

    public void setLogger(org.apache.tomcat.logging.Logger l) {
	// I don't how to use the Logger, sorry
    }
    
    public void setContextManager( ContextManager ctx ) {
	    this.cm=ctx;
    }

    public void setTcpConnectionHandler( TcpConnectionHandler handler) {
    	this.con=handler;
    }

    public TcpConnectionHandler getTcpConnectionHandler() {
	    return con;
    }

    public void setPort( int port ) {
    	this.port=port;
    }

    public void setPort(  String portS ) {
	    this.port=string2Int( portS );
    }

    public int getPort() {
    	return port;
    }

    public void setProperty( String prop, String value) {
    	if(PORT.equals(prop) ) {
    	    setPort( value );
    	} else if(HANDLER.equals(prop)) {
    	    try {
		Class chC=Class.forName( value );
    	    	con=(TcpConnectionHandler)chC.newInstance();
    	    } catch( Exception ex) {
		ex.printStackTrace();
    	    }
    	} else if(INET.equals(prop)) {
    	    try {
		address=InetAddress.getByName( value );
    	    } catch( Exception ex) {
    	    }
    	}
    }

    // XXX use constants, remove dep on HttpServer
    public void setAttribute( String prop, Object value) {
    	if(VHOST_NAME.equals(prop) ) {
	        //vhost=(String)value;
	} else if(VHOST_PORT.equals(prop) ) {
	    vport=((Integer)value).intValue();
	} else if(VHOST_ADDRESS.equals(prop)) {
	    address=(InetAddress)value;
	} else if(SERVER.equals(prop)) {
    	    //server=(HttpServer)value;
	} else if(SOCKET_FACTORY.equals(prop)) {
    	    socketFactory=(ServerSocketFactory)value;
	}
    }
    
    public Object getAttribute( String prop ) {
	    return null;
    }

    private int string2Int( String val) {
    	try {
	        return Integer.parseInt(val);
    	} catch (NumberFormatException nfe) {
	        return 0;
    	}
    }
}
