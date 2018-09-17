/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/core/Attic/BufferedServletInputStream.java,v 1.8 2000/05/26 17:32:04 costin Exp $
 * $Revision: 1.8 $
 * $Date: 2000/05/26 17:32:04 $
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


package org.apache.tomcat.core;

import java.io.*;
import javax.servlet.*;

/* - Not buffered yet
   - 
 */

/**
 * Default implementation use RequestAdapter to read data.
 * Works only if you extend RequestImpl and override doRead() -
 * all other implementations of Request should provide their own Stream
 *
 * @author James Duncan Davidson <duncan@eng.sun.com>
 * @author Jason Hunter <jch@eng.sun.com>
 */
public class BufferedServletInputStream extends ServletInputStream {

    // XXX
    // our limit code is not syncronized... Does it need to be?
    // what do other streams like FileInputStream do when they
    // smack into their limit?
    
    private int bytesRead = 0;
    private int limit = -1;
    private RequestImpl reqA;
    
    public BufferedServletInputStream() {
    }

    public BufferedServletInputStream( Request reqA ) {
	setRequest(reqA);
    }
    
    public void setRequest(Request reqA ) {
	this.reqA=(RequestImpl)reqA;
    }
    
    public int doRead() throws IOException {
	// assert reqA!=null
	return reqA.doRead(); // XXX do the real "buffered" reads, this should go away
    }
    
    public  int doRead( byte b[], int off, int len ) throws IOException {
	// assert reqA!=null
	return reqA.doRead( b, off, len );
    }    
    
    public void setLimit(int limit) {
	bytesRead = 0;
	this.limit = limit;
    }
    
    public int read() throws IOException {
	if (limit != -1) {
	    if (bytesRead < limit) {
		bytesRead++;
		return doRead();
	    } else {
		return -1;
	    }
	} else {
	    return doRead();
	}
    }

    public int read(byte[] b) throws IOException {
	return read(b, 0, b.length);
    }

    public int read(byte[] b, int off, int len) throws IOException {
	if (limit != -1) {
	    if (bytesRead == limit) {
		return -1;
	    }
	    if (bytesRead + len > limit) {
		len = limit - bytesRead;
	    }
	    int numRead = doRead(b, off, len);
	    if (numRead > 0) {
		bytesRead += numRead;
	    }
	    return numRead;
	} else {
	    return doRead(b, off, len);
	}
    }
    

//     /**
//      * @deprecated Not part of Servlet API, without it we can avoid a lot of GC.
//      */
//     public String readLine() throws IOException {

// 	// don't need to do any limit checking here
// 	// as the superclasses' readLine(buf, off, len)
// 	// method delegates back to our our read() method
// 	// which does check for limits.
	
// 	byte[] buf = new byte[1024];
// 	int count = readLine(buf, 0, buf.length);
// 	if (count >= 0) {
// 	    return new String(buf, 0, count, Constants.DEFAULT_CHAR_ENCODING);
// 	} else {
// 	    return ""; 
// 	}
//     }
}
