
/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/service/connector/Attic/Ajp13ConnectorResponse.java,v 1.4.2.3 2000/12/12 09:41:44 hgomez Exp $
 * $Revision: 1.4.2.3 $
 * $Date: 2000/12/12 09:41:44 $
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

import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.tomcat.core.*;
import org.apache.tomcat.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class Ajp13ConnectorResponse extends ResponseImpl 
{
	public static final int  MAX_SEND_SIZE = TcpConnector.MAX_PACKET_SIZE - 
	                                         TcpConnector.H_SIZE - 
	                                         4;
	
    public static final byte JK_AJP13_SEND_BODY_CHUNK   = 3;
    public static final byte JK_AJP13_SEND_HEADERS      = 4;
    public static final byte JK_AJP13_END_RESPONSE      = 5;
    
    public static final int SC_RESP_CONTENT_TYPE        = 0xA001;
    public static final int SC_RESP_CONTENT_LANGUAGE    = 0xA002;
    public static final int SC_RESP_CONTENT_LENGTH      = 0xA003;
    public static final int SC_RESP_DATE                = 0xA004;
    public static final int SC_RESP_LAST_MODIFIED       = 0xA005;
    public static final int SC_RESP_LOCATION            = 0xA006;
    public static final int SC_RESP_SET_COOKIE          = 0xA007;
    public static final int SC_RESP_SET_COOKIE2         = 0xA008;
    public static final int SC_RESP_SERVLET_ENGINE      = 0xA009;
    public static final int SC_RESP_STATUS              = 0xA00A;
    public static final int SC_RESP_WWW_AUTHENTICATE    = 0xA00B;
    
    MsgConnector con;

    private boolean finished = false;

    public Ajp13ConnectorResponse() 
    {
    }

    // XXX if more headers that MAX_SIZE, send 2 packets!   
    public void endHeaders() throws IOException 
    {
        super.endHeaders();
    
        if (request.getProtocol() == null) {
            return;
        }
    
		// Servlet Engine header will be set per/adapter - smarter adapters will
		// not send it every time ( have it in C side ), and we may also want
		// to add informations about the adapter used 
		if(request.getContext() != null) {
	    	setHeader("Servlet-Engine", request.getContext().getEngineHeader());
	    }

        MsgBuffer msg=con.getMsgBuffer();
        msg.reset();
        msg.appendByte(JK_AJP13_SEND_HEADERS);
                
        msg.appendInt(getStatus());
        msg.appendString("");
        
        // No need for these two!!!
        headers.removeHeader("Status");
        headers.removeHeader("Servlet-Engine");
    
		int numHeaders = headers.size();
		msg.appendInt(numHeaders);
        
		for( int i=0 ; i < numHeaders ; i++ ) {
			String headerName = headers.getHeaderName(i);
            int sc = headerNameToSc(headerName);
            if(-1 != sc) {
                msg.appendInt(sc);
            } else {
                msg.appendString(headerName);
            }
            msg.appendString(headers.getHeader(i));
        }

        msg.end();
        con.send(msg);
    } 
         
    public void finish() throws IOException 
    {
        if (!finished) {
            super.finish();
            finished = true;
            MsgBuffer msg = con.getMsgBuffer();
            msg.reset();
            msg.appendByte(JK_AJP13_END_RESPONSE);
            msg.appendByte((byte)1);        
            msg.end();
            con.send(msg);
        }
    }
    
    protected int headerNameToSc(String name)
    {       
        switch(name.charAt(0)) {
            case 'c':
            case 'C':
                if(name.equalsIgnoreCase("Content-Type")) {
                    return SC_RESP_CONTENT_TYPE;
                } else if(name.equalsIgnoreCase("Content-Language")) {
                    return SC_RESP_CONTENT_LANGUAGE;
                } else if(name.equalsIgnoreCase("Content-Length")) {
                    return SC_RESP_CONTENT_LENGTH;
                }
            break;
            
            case 'd':
            case 'D':
                if(name.equalsIgnoreCase("Date")) {
                    return SC_RESP_DATE;
                }
            break;
            
            case 'l':
            case 'L':
                if(name.equalsIgnoreCase("Last-Modified")) {
                    return SC_RESP_LAST_MODIFIED;
                } else if(name.equalsIgnoreCase("Location")) {
                    return SC_RESP_LOCATION;
                }
            break;

            case 's':
            case 'S':
                if(name.equalsIgnoreCase("Set-Cookie")) {
                    return SC_RESP_SET_COOKIE;
                } else if(name.equalsIgnoreCase("Set-Cookie2")) {
                    return SC_RESP_SET_COOKIE2;
                }
            break;
            
            case 'w':
            case 'W':
                if(name.equalsIgnoreCase("WWW-Autheticate")) {
                    return SC_RESP_WWW_AUTHENTICATE;
                }
            break;          
        }
        
        return -1;
    }
    
    public void recycle() 
    {
        super.recycle();
        finished = false;
    }
    
    public void setConnector(MsgConnector con) 
    {
        this.con = con;
    }
        
    public void doWrite(  byte b[], int off, int len) throws IOException 
    {
	int sent = 0;
	while(sent < len) {
	    int to_send = len - sent;
	    to_send = to_send > MAX_SEND_SIZE ? MAX_SEND_SIZE : to_send;
	    
	    MsgBuffer buf = con.getMsgBuffer();
	    buf.reset();
	    buf.appendByte(Ajp13ConnectorResponse.JK_AJP13_SEND_BODY_CHUNK);	        	
	    buf.appendBytes(b, off + sent, to_send);	        
	    con.send(buf);
	    sent += to_send;
	}
    }
    
}
