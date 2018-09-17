/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/service/connector/Attic/MsgBuffer.java,v 1.7.2.2 2001/06/19 02:42:23 marcsaeg Exp $
 * $Revision: 1.7.2.2 $
 * $Date: 2001/06/19 02:42:23 $
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
//import org.apache.tomcat.server.*;
import javax.servlet.*;
import javax.servlet.http.*;

// XXX check limits?


/** Encapsulated messages passed between Tomcat and Web servers
 */
public class MsgBuffer {
    byte buff[];
    int len;
    int pos;
    int maxlen;

    public MsgBuffer( int size ) {
	buff=new byte[size];
	maxlen=size;
    }

    public MsgBuffer( byte b[] ) {
	buff=b;
	maxlen=b.length;
    }
    
    public byte[] getBuff() {
	return buff;
    }

    public void setBuff(byte[] b) {
	buff=b;
	maxlen = b.length;
    }

    public int getLen() {
	return len;
    }
    
    public int getMaxLen() {
	return maxlen;
    }
    
    /** Verify the buffer and read the len
     */
    public int checkIn() {
	pos=4;
	int mark=BuffTool.getInt( buff,0 );
	len=BuffTool.getInt( buff,2 );
	if( mark != 0x1234 ) {
	    System.out.println("BAD packet " + mark);
	    dump( "In: " );
	    return -1;
	}
	return len;
    }

    public void reset() {
	len=4;
	pos=4;
	buff[0]=(byte)'A';
	buff[1]=(byte)'B';
    }

    public void end() {
	len=pos;
	setInt( 2, len-4 );
    }
    
    public void setInt(int bpos, int val ) {
	BuffTool.addInt( buff, bpos, val );
    }

    public void appendByte( byte val ) {
	buff[pos] = val;
	pos++;
    }

    public void appendInt( int val ) {
	BuffTool.addInt( buff, pos, val );
	pos+=2;
    }

    public void appendString( String val ) {
	pos=BuffTool.addString( buff, pos, val );
    }

    public void appendBytes( byte b[], int off, int len ) {
	BuffTool.addInt( buff, pos, len );
	pos+=2;
	if( pos + len >= buff.length ) {
	    System.out.println("Buffer overflow " + buff.length + " " + pos + " " + len );
	}
	System.arraycopy( b, off, buff, pos, len);
	buff[pos+len]=0;
	pos+=len;
	pos++;
    }

    public int getInt() {
	int res=BuffTool.getInt( buff, pos );
	pos+=2;
	return res;
    }

    public int peekInt() {
	return BuffTool.getInt( buff, pos );
    }

    public byte getByte() {
	byte res = buff[pos];
	pos++;
	return res;
    }

    public byte peekByte() {
	return buff[pos];
    }

    public String getString() throws java.io.UnsupportedEncodingException {
	int ll= getInt();
	if( (ll == 0xFFFF) || (ll==-1) ) {
	    //	    System.out.println("null string " + ll);
	    return null;
	}
	String s=BuffTool.getString( buff, pos, ll );
	pos +=ll;
	pos++;
	return s;
    }

    public int getBytes(byte dest[]) {
	int ll= getInt();
	if( ll > buff.length ) {
	    System.out.println("XXX Assert failed, buff too small ");
	}
	
	if( (ll == 0xFFFF) || (ll==-1) ) {
	    System.out.println("null string " + ll);
	    return 0;
	}

	System.arraycopy( buff, pos,  dest, 0, ll );
	pos +=ll;
	pos++; // ??? 
	return ll;
    }

    private String hex( int x ) {
	//	    if( x < 0) x=256 + x;
	String h=Integer.toHexString( x );
	if( h.length() == 1 ) h = "0" + h;
	return h.substring( h.length() - 2 );
    }

    private void hexLine( int start ) {
	for( int i=start; i< start+16; i++ ) {
	    System.out.print( hex( buff[i] ) + " ");
	}
	System.out.print(" | ");
	for( int i=start; i< start+16; i++ ) {
	    if( Character.isLetterOrDigit( (char)buff[i] ))
		System.out.print( new Character((char)buff[i]) );
	    else
		System.out.print( "." );
	}
	System.out.println();
    }
    
    public void dump(String msg) {
	System.out.println( msg + ": " + buff + " " + pos +"/" + len + "/" + maxlen );

	for( int j=0; j<len + 16; j+=16 )
	    hexLine( j );
	
	System.out.println();
    }

    
}
