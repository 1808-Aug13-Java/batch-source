/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/service/connector/Attic/Ajp13ConnectionHandler.java,v 1.4.2.1 2000/12/12 09:41:43 hgomez Exp $
 * $Revision: 1.4.2.1 $
 * $Date: 2000/12/12 09:41:43 $
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

package org.apache.tomcat.service.connector;


import org.apache.tomcat.service.*;
import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.tomcat.core.*;
import org.apache.tomcat.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class Ajp13ConnectionHandler implements  TcpConnectionHandler
{
    ContextManager contextM;

    public static final byte JK_AJP13_FORWARD_REQUEST   = 2;
    public static final byte JK_AJP13_SHUTDOWN          = 7;

    public Ajp13ConnectionHandler()
    {
        super();
    }

    public Object[] init()
    {
        Object thData[]=new Object[3];
        Ajp13ConnectorRequest req=new Ajp13ConnectorRequest();
        Ajp13ConnectorResponse res=new Ajp13ConnectorResponse();
        contextM.initRequest(req, res);
        thData[0]=req;
        thData[1]=res;
        thData[2]=new TcpConnector();

        return  thData;
    }

    // XXX
    //    Nothing overriden, right now AJPRequest implment AJP and read everything.
    //    "Shortcuts" to be added here ( Vhost and context set by Apache, etc)
    // XXX handleEndpoint( Endpoint x )
    public void processConnection(TcpConnection connection, Object thData[])
    {
        try {
            if(connection == null) {
                return;
            }
            Socket socket = connection.getSocket();
            if(socket == null) {
                return;
            }

            socket.setSoLinger( true, 100);

            TcpConnector con=null;
            Ajp13ConnectorRequest req=null;
            Ajp13ConnectorResponse res=null;

            if(thData != null) {
                req = (Ajp13ConnectorRequest)thData[0];
                res = (Ajp13ConnectorResponse)thData[1];
                con = (TcpConnector)thData[2];
                if(req != null) req.recycle();
                if(res != null) res.recycle();
                if(con != null) con.recycle();
            }

            if(req == null || res == null || con == null) {
                req = new Ajp13ConnectorRequest();
                res = new Ajp13ConnectorResponse();
                con = new TcpConnector();
                contextM.initRequest( req, res );
            }

            con.setSocket(socket);
            res.setConnector(con);
            req.setConnector(con);

            boolean moreRequests = true;
            while(moreRequests) { // XXX how to exit ? // request.hasMoreRequests()) {
                MsgBuffer msg = con.getMsgBuffer();
                int err = con.receive(msg);
                if(err < 0) {
                    //System.out.println("ERR rec " + err );
                    moreRequests=false;
                    break;
                }

                // XXX right now the only incoming packet is "new request"
                // We need to deal with arbitrary calls
                int type = (int)msg.getByte();
                switch(type) {
                    
                    case JK_AJP13_FORWARD_REQUEST:
                        err = req.decodeRequest(msg);                
                        contextM.service(req, res);

                        req.recycle();
                        res.recycle();                                                                    
                    break;
                    
                    case JK_AJP13_SHUTDOWN:
                        if(!doShutdown(con, 
                                       socket.getLocalAddress(),
			                           socket.getInetAddress())) {
                            moreRequests = false;
                        }                        
                    break;
                }                
            }
            //System.out.println("Closing connection");
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAttribute(String name, Object value ) {
        if("context.manager".equals(name) ) {
            contextM=(ContextManager)value;
        }
    }

    public void setServer(Object contextM)
    {
        this.contextM=(ContextManager)contextM;
    }
    
    protected boolean doShutdown(TcpConnector con,
                                 InetAddress serverAddr,
                                 InetAddress clientAddr)
    {
        try {
		    // close the socket connection before handling any signal
			// but get the addresses first so they are not corrupted			
			con.close();
            if(isSameAddress(serverAddr, clientAddr)) {
				// Shutdown - probably apache was stoped with apachectl stop
				contextM.stop();
				
				// same behavior as in past, because it seems that
				// stopping everything doesn't work - need to figure
				// out what happens with the threads ( XXX )
				System.exit(0);
	        }
		} catch(Exception ignored) {
		    System.err.println(ignored);
	    }
	    System.err.println("Shutdown command ignored");
	    return false;
    }
    
    /**
     * Return <code>true</code> if the specified client and server addresses
     * are the same.  This method works around a bug in the IBM 1.1.8 JVM on
     * Linux, where the address bytes are returned reversed in some
     * circumstances.
     * <br>
     * Was copied from <code>Ajp12ConnectionHandler</code>
     * 
     * @param server The server's InetAddress
     * @param client The client's InetAddress
     */
    private boolean isSameAddress(InetAddress server, InetAddress client) {

	    // Compare the byte array versions of the two addresses
	    byte serverAddr[] = server.getAddress();
	    byte clientAddr[] = client.getAddress();
	    if (serverAddr.length != clientAddr.length)
	        return (false);
	    boolean match = true;
	    for (int i = 0; i < serverAddr.length; i++) {
	        if (serverAddr[i] != clientAddr[i]) {
		        match = false;
		        break;
	        }
	    }
	    if(match)
	        return (true);

	    // Compare the reversed form of the two addresses
	    for (int i = 0; i < serverAddr.length; i++) {
	        if (serverAddr[i] != clientAddr[(serverAddr.length-1)-i])
		    return (false);
	    }
	    return (true);
    }    
}
