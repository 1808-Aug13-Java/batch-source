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


package org.apache.tomcat.util;

import org.apache.tomcat.util.StringManager;
import org.apache.tomcat.core.Constants;
import java.io.*;

/**
 *
 *
 */
public class BuffTool {
    
    public static  void addInt( byte buff[], int pos, int v ) {
	buff[pos]=(byte) ((v >>>  8) & 0xFF);
	buff[pos+1]=(byte) ((v >>>  0) & 0xFF);
    }

    /** @returns new position
     */ 
    public static int addString( byte buff[], int pos, String v ) {
	if(v!=null) {
	    int len=v.length();
	    addInt( buff, pos, len );
	    System.arraycopy( v.getBytes(), 0, buff, pos+2, len);
	    buff[pos+len+2]=0;
	    return len+pos+3;
	}  else {
	    addInt( buff, pos, 0);
	    buff[pos+2] = 0;
	    return pos+3;
	}                   
    }

    public static  int getInt( byte b[], int offset )  {
	int b1=b[offset]&0xFF; // No swap, Java order
	int b2=b[offset+1]&0xFF;
	return  (b1<<8) + (b2<<0);
    }

    public static String getString( byte b[] , int pos, int len ) 
        throws UnsupportedEncodingException
    {
	return new String( b, pos, len, Constants.DEFAULT_CHAR_ENCODING );
    }


    public static void dump( byte buff[], int len )
        throws UnsupportedEncodingException
    {
	for (int i=0; i<len; i+=8 ) {
	    for(int j=i; j<i+8; j++ ) {
		if( j<len) {
		    if( buff[j]<16 && buff[j]>=0 ) System.out.print("0");
		    System.out.print( Integer.toHexString( ((int)buff[j]) & 0xFF) + " " );
		}
	    }
	    if( i+8 <len )
		System.out.print( new String( buff, i, 8, Constants.DEFAULT_CHAR_ENCODING ));
	    else
		System.out.print( new String( buff, i, len-i, Constants.DEFAULT_CHAR_ENCODING ));
	    System.out.println();
	}
	System.out.println();
    }

    // Various byte[] utils, used in parsing request and protocols

    /** Find a character, no side effects.
     *  @returns index of char if found, -1 if not
     */
    public static int findChar( byte buf[], int start, int end, char c ) {
	byte b=(byte)c;
	int offset = start;
	while (offset < end) {
	    if (buf[offset] == b) {
		return offset;
	    }
	    offset++;
	}
	return -1;
    }

    /** Find a character, no side effects.
     *  @returns index of char if found, -1 if not
     */
    public static int findChars( byte buf[], int start, int end, byte c[] ) {
	int clen=c.length;
	int offset = start;
	while (offset < end) {
	    for( int i=0; i<clen; i++ ) 
		if (buf[offset] == c[i]) {
		    return offset;
		}
	    offset++;
	}
	return -1;
    }

    /** Find the first character != c 
     *  @returns index of char if found, -1 if not
     */
    public static int findNotChars( byte buf[], int start, int end, byte c[] ) {
	int clen=c.length;
	int offset = start;
	boolean found;
		
	while (offset < end) {
	    found=true;
	    for( int i=0; i<clen; i++ ) {
		if (buf[offset] == c[i]) {
		    found=false;
		    break;
		}
	    }
	    if( found ) { // buf[offset] != c[0..len]
		return offset;
	    }
	    offset++;
	}
	return -1;
    }


    /** Read a line from input to buf.
     *  Hope that in is buffered !!
     *  @returns The number of bytes read, of -1 if end of stream
     */
    public static int readLine(InputStream in, byte[] b, int off, int len)
	throws IOException
    {

	if (len <= 0) {
	    return 0;
	}
	int count = 0;
	int c;

	while ((c = in.read()) != -1) {
	    b[off++] = (byte)c;
	    count++;
	    if (c == '\n' || count == len) {
		break;
	    }
	}
	return count > 0 ? count : -1;
    }

}
