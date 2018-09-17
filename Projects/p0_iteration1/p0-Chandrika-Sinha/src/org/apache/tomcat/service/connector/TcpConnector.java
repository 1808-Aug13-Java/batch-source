/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/service/connector/Attic/TcpConnector.java,v 1.2.2.2 2000/12/12 09:41:44 hgomez Exp $
 * $Revision: 1.2.2.2 $
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

import org.apache.tomcat.service.*;
import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.tomcat.core.*;
import org.apache.tomcat.util.*;
//import org.apache.tomcat.server.*;
import javax.servlet.*;
import javax.servlet.http.*;

class TcpConnector implements MsgConnector 
{
    public static final int MAX_PACKET_SIZE=8192;
    public static final int H_SIZE=4;
    OutputStream out;
    InputStream in;
    
    MsgBuffer msg;
    
    public TcpConnector() {
	msg= new MsgBuffer( MAX_PACKET_SIZE );
    }

    public void setSocket( Socket socket ) throws IOException {
	socket.setSoLinger( true, 100);
	
	out = socket.getOutputStream();
	in = socket.getInputStream();
    }

    public void recycle() {

    }
    
    public MsgBuffer getMsgBuffer() {
	msg.reset();
	return msg;
    }
    

    /**
     * Read the next message from the input stream, and return the
     * message length that was actually read (not counting the header).
     *
     * @param msg Message buffer into which we should read
     *
     * @exception IOException if an input/output error occurs
     */
    public int receive(MsgBuffer msg) throws IOException {

	// Acquire our byte buffer
	byte b[]=msg.getBuff();

        // Read the entire header
        if (receiveFully(b, 0, H_SIZE) < H_SIZE)
            return (-1);        // End of file indication
	
        // Read the entire message
	int len = msg.checkIn();
        int read = receiveFully(b, H_SIZE, len);
        return (read);

    }


    /**
     * Read the specified number of bytes into the specified buffer,
     * continuing to read until the required number of bytes has been
     * encountered or end-of-file is reached.  Return the number of
     * bytes actually read (which will be less than the specified length
     * <strong>only</strong> if EOF was reached.
     *
     * @param buff Byte array into which reading takes place
     * @param off Initial offset at which read bytes are placed
     * @param len Number of bytes to be read
     *
     * @exception IOException if an input/output error occurs
     */
    private int receiveFully(byte buff[], int off, int len)
        throws IOException {

        int count = 0;
        while (len > 0) {
            int read = in.read(buff, off, len);
            if (read < 0)       // End of file indication
                break;
            count += read;
            off += read;
            len -= read;
        }
        return (count);

    }


    public void send( MsgBuffer msg ) throws IOException {
	msg.end();
	byte b[]=msg.getBuff();
	int len=msg.getLen();
	//	msg.dump("SEND");
	out.write( b, 0, len );
    }
    
    public void close() throws IOException {
    if(null != out) {        
    out.close();
    }
    if(null !=in) {
    in.close();
    }
    }
}
