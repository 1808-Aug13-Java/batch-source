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

import java.io.OutputStream;
import java.io.IOException;

/**
 * This class is used to represent a char region in an HTTP message.
 *
 * @author Costin Manolache costin@eng.sun.com
 */
public final class MessageChars {
    // cache for toString conversions
    String str;
    
    /** The message chars.  */
    char[] chars;

    /** The start offset  */
    int offset;

    /** The length */
    int length;

    /**
     * Creates a new, uninitialized message string.
     */
    public MessageChars() {
    }

    /**
     * Creates a new message string with the specified chars
     * @param b the chars
     * @param off the offset
     * @param len the length
     */
    public MessageChars(char[] c, int off, int len) {
	setChars( c, off, len );
    }

    /**
     * Resets the message string to an uninitialized state.
     */
    public void reset() {
	chars=null;
	str = null;
	length=0;
    }

    /**
     * Sets the message string to the specified bytes.
     * @param b the bytes
     * @param off the offset of the bytes
     * @param len the length of the bytes
     */
    public void setChars(char[] b, int off, int len) {
	chars=b;
	this.offset=off;
	this.length=len;
	str = null;
    }

    /**
     * Returns true if the message string is set.
     */
    public boolean isSet() {
	return  chars != null;
    }

    public int getChars( char buf[], int buf_off )
    {
	System.arraycopy(chars, offset, buf, buf_off, length);
	return length;
	
    }
    
    /**
     * Returns the message string as a String object.
     */
    public String toString() {
	if( str != null )
	    return str;
	str=new String( chars, offset, length);
	return str;
    }

    /**
     * Returns the message string parsed as an unsigned integer.
     * @exception NumberFormatException if the integer format was invalid
     */
    public int toInteger() throws NumberFormatException {
	toString(); // str will be updated
	return Integer.parseInt(str);
    }

    /**
     * Compares this message string to the specified String object.
     * @param s the String to compare
     * @return true if the comparison succeeded, false otherwise
     */
    public boolean equals(String s) {
	toString();
	return  str.equals(s);
    }

    /**
     * Compares this message string to the specified String object. Case is
     * ignored in the comparison.
     * @param s the String to compare
     * @return true if the comparison succeeded, false otherwise
     */
    public boolean equalsIgnoreCase(String s) {
	toString();
	return  str.equalsIgnoreCase(s);
    }

    /**
     * Returns the length of the message string.
     */
    public int length() {
	return  length;
    }
}
