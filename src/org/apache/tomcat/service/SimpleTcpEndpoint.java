/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/service/Attic/SimpleTcpEndpoint.java,v 1.4.2.2 2000/11/10 06:42:51 craigmcc Exp $
 * $Revision: 1.4.2.2 $
 * $Date: 2000/11/10 06:42:51 $
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
import org.apache.tomcat.net.*;
import java.io.*;
import java.net.*;
import java.util.*;

/* Similar with MPM module in Apache2.0. Handles all the details related with
   "tcp server" functionality - thread management, accept policy, etc.
   It should do nothing more - as soon as it get a socket ( and all socket options
   are set, etc), it just handle the stream to ConnectionHandler.processConnection. (costin)
*/



/**
 * Handle incoming TCP connections.
 *
 * This class implement a simple server model: one listener thread accepts on a socket and
 * creates a new worker thread for each incoming connection.
 *
 * More advanced Endpoints will reuse the threads, use queues, etc.
 *
 * @author James Duncan Davidson [duncan@eng.sun.com]
 * @author Jason Hunter [jch@eng.sun.com]
 * @author James Todd [gonzo@eng.sun.com]
 * @author Costin@eng.sun.com
 * @author Gal Shachor [shachor@il.ibm.com]
 */
public class SimpleTcpEndpoint  extends PoolTcpEndpoint {

    private static StringManager sm = StringManager.getManager("org.apache.tomcat.service");

    private static final int BACKLOG = 100;
    private static final int TIMEOUT = 5000;

    private int backlog = BACKLOG;
    private int timeout = TIMEOUT;

    TcpConnectionHandler handler;

    private InetAddress inet;
    private int port;

    private ServerSocketFactory factory;
    private ServerSocket serverSocket;

    Runnable listener;
    boolean running = true;

    public SimpleTcpEndpoint() {
    }

    // -------------------- Configuration --------------------

    public int getPort() {
	    return port;
    }

    public void setPort(int port ) {
	    this.port=port;
    }

    public InetAddress getAddress() {
	    return inet;
    }

    public void setAddress(InetAddress inet) {
	this.inet=inet;
    }

    public void setServerSocket(ServerSocket ss) {
	    serverSocket = ss;
    }

    public void setServerSocketFactory(  ServerSocketFactory factory ) {
	    this.factory=factory;
    }

    public void setConnectionHandler( TcpConnectionHandler handler ) {
    	this.handler=handler;
    }

    public TcpConnectionHandler getConnectionHandler() {
	    return handler;
    }

    /**
     * Allows the server developer to specify the backlog that
     * should be used for server sockets. By default, this value
     * is 100.
     */
    public void setBacklog(int backlog) {
	    this.backlog = backlog;
    }

    public int getBacklog() {
        return backlog;
    }

    /**
     * Sets the timeout in ms of the server sockets created by this
     * server. This method allows the developer to make servers
     * more or less responsive to having their server sockets
     * shut down.
     *
     * <p>By default this value is 5000ms.
     */
    public void setTimeout(int timeout) {
	    this.timeout = timeout;
    }

    // -------------------- Public methods --------------------

    public void startEndpoint() throws IOException, InstantiationException {
	try {
	    if(factory==null)
		factory=ServerSocketFactory.getDefault();
	    if(serverSocket==null) {
		if (inet == null) {
		    serverSocket = factory.createSocket(port, backlog);
		} else {
		    serverSocket = factory.createSocket(port, backlog, inet);
		}
		if (serverSocket != null) {
		    serverSocket.setSoTimeout(this.timeout);
		}
	    }
	} catch( IOException ex ) {
	    // throw?
	    // ex.printStackTrace();
	    running=false;
            throw ex;
	    // throw new HttpServerException(msg);
	} catch( InstantiationException ex1 ) {
	    // throw?
	    // ex1.printStackTrace();
	    running=false;
            throw ex1;
	    // throw new HttpServerException(msg);
	}
	running=true;
	listener = new TcpListenerThread( this );
	Thread thread = new Thread(listener);
	thread.start();
    }
    
    public void stopEndpoint() {
    	running=false;
	try {
	    serverSocket.close(); // XXX?
	} catch(Exception e) {
	    e.printStackTrace();
	}
	serverSocket = null;
    }
    
    // -------------------- Private methods

    void processSocket(Socket s) throws IOException
    {
    	// XXX reuse, pools, etc

    	// XXX set socket options
    	// 	s.setSoLinger( true, 100);
    	//	s.setSoTimeout( 1000 );

    	TcpConnection con=new TcpConnection();
    	con.setEndpoint(this);
    	con.setSocket( s );
    	TcpConnectionHandler handler = getConnectionHandler();
    	TcpConnectionThread handlerThread=new TcpConnectionThread(handler, con);
	
	new Thread(handlerThread).start();
    }

    void acceptConnections() {
    	try {
    	    if(running == false)
		return;
	    
    	    if(null!= serverSocket) {
		Socket socket = acceptSocket();
    	    	if(running != false) {
		    processSocket(socket);
		}
    	    }
    	} catch(Throwable e) {
    	    running = false;
    	    String msg = sm.getString("endpoint.err.fatal",
				      serverSocket, e);
    	    e.printStackTrace(); // something very wrong happened - better know what
    	    System.err.println(msg);
    	}
    }

    Socket acceptSocket() {
        Socket accepted = null;
    	try {
    	    if(running == true) {
		if(null!= serverSocket) {
		    accepted = serverSocket.accept();
		    if(running == false) {
			if(null != accepted) {
			    accepted.close();  // rude, but unlikely!
			    accepted = null;
			}
		    }
    	        }
    	    }
    	} catch(InterruptedIOException iioe) {
    	    // normal part -- should happen regularly so
    	    // that the endpoint can release if the server
    	    // is shutdown.
    	    // you know, i really wish that there was a
    	    // way for the socket to timeout without
    	    // tripping an exception. Exceptions are so
    	    // 'spensive.
    	} catch (SocketException e) {
    	    if (running != false) {
		running = false;
		String msg = sm.getString("endpoint.err.fatal",
					  serverSocket, e);
    	    	e.printStackTrace(); // something very wrong happened - better know what
		System.err.println(msg);
    	    }
    	} catch(Throwable e) {
    	    running = false;
    	    String msg = sm.getString("endpoint.err.fatal",
				      serverSocket, e);
    	    e.printStackTrace(); // something very wrong happened - better know what
    	    System.err.println(msg);
    	}
	
    	return accepted;
    }
}

// -------------------- Threads --------------------
// XXX add a more efficient model - use thread pools, use a Queue, etc

// Keep the thread model in one place !

// Listener thread
class TcpListenerThread implements Runnable {
    SimpleTcpEndpoint endpoint;
    
    public TcpListenerThread( SimpleTcpEndpoint endpoint) {
    	this.endpoint=endpoint;
    }
    
    public void run() {
	while (endpoint.running) {
	    endpoint.acceptConnections();
    	}
	//endpoint.manager.notifyEndpointDown(this);
    }
}

// Worker Thread
// call handleConnection() in a new thread
// XXX thread reuse!
class TcpConnectionThread implements Runnable {
    TcpConnectionHandler handler;
    TcpConnection connection;
    
    public TcpConnectionThread( TcpConnectionHandler handler, TcpConnection connection) {
    	this.handler=handler;
	this.connection=connection;
    }
    
    public void run() {
	handler.processConnection(connection, null);
    }
}
