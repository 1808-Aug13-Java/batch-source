/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/util/Attic/MessageString.java,v 1.5 2000/06/23 02:16:29 costin Exp $
 * $Revision: 1.5 $
 * $Date: 2000/06/23 02:16:29 $
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


package org.apache.tomcat.util;

import java.io.OutputStream;
import java.io.IOException;

/**
 * This class is used to represent a string in an HTTP message.
 *
 * @author dac@eng.sun.com
 */
public class MessageString {
    /**
     * The message String.
     */
    protected String str;

    /**
     * Creates a new, uninitialized message string.
     */
    public MessageString() {
    }

    /**
     * Creates a new message string with the specified String object.
     * @param s the String
     */
    public MessageString(String s) {
	str = s;
    }

    /**
     * Resets the message string to an uninitialized state.
     */
    public void reset() {
	str = null;
    }

    /**
     * Sets the message string to the specified String.
     * @param s the String
     */
    public void setString(String s) {
	str = s;
    }

    /**
     * Returns true if the message string is set.
     */
    public boolean isSet() {
	return str != null;
    }

    public int getChars( char buf[], int buf_off )
    {
	if (str != null) {
	    int length = str.length();
	    str.getChars( 0, length, buf, buf_off);
	    return length;
	}
	return 0;
    }
    
    /**
     * Returns the message string as a String object.
     */
    public String toString() {
	return str;
    }

    /**
     * Returns the message string parsed as an unsigned integer.
     * @exception NumberFormatException if the integer format was invalid
     */
    public int toInteger() throws NumberFormatException {
	return str != null ? Integer.parseInt(str) : -1;
    }

    /**
     * Compares this message string to the specified String object.
     * @param s the String to compare
     * @return true if the comparison succeeded, false otherwise
     */
    public boolean equals(String s) {
	return str != null ? str.equals(s) : null==s;
    }

    /**
     * Compares this message string to the specified String object. Case is
     * ignored in the comparison.
     * @param s the String to compare
     * @return true if the comparison succeeded, false otherwise
     */
    public boolean equalsIgnoreCase(String s) {
	return  str.equalsIgnoreCase(s);
    }

    /**
     * Returns the length of the message string.
     */
    public int length() {
	return str != null ? str.length() : 0;
    }
}
