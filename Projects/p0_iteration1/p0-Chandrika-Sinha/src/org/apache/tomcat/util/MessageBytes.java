/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/util/Attic/MessageBytes.java,v 1.5.2.1 2000/11/10 06:42:52 craigmcc Exp $
 * $Revision: 1.5.2.1 $
 * $Date: 2000/11/10 06:42:52 $
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
import org.apache.tomcat.core.Constants;

/**
 * This class is used to represent a subarray of bytes in an HTTP message.
 *
 * @author dac@eng.sun.com
 * @author James Todd [gonzo@eng.sun.com]
 */
public class MessageBytes {

    private static StringManager sm =
        StringManager.getManager("org.apache.tomcat.util");

    /**
     * The message bytes.
     */
    protected byte[] bytes;

    /**
     * The start offset of the bytes.
     */
    protected int offset;

    /**
     * The length of the bytes.
     */
    protected int length;

    /**
     * Creates a new, uninitialized MessageBytes object.
     */
    public MessageBytes() {
    }

    /**
     * Creates a new MessageBytes object with the specified bytes.
     * @param b the bytes
     * @param off the start offset of the bytes
     * @param len the length of the bytes
     */
    public MessageBytes(byte[] b, int off, int len) {
	setBytes(b, off, len);
    }

    /**
     * Resets the message bytes to an uninitialized state.
     */
    public void reset() {
	bytes = null;
    }

    /**
     * Sets the message bytes to the specified subarray of bytes.
     * @param b the ascii bytes
     * @param off the start offset of the bytes
     * @param len the length of the bytes
     */
    public void setBytes(byte[] b, int off, int len) {
	bytes = b;
	offset = off;
	length = len;
    }

    /**
     * Returns the message bytes.
     */
    public byte[] getBytes() {
	return bytes;
    }

    /**
     * Puts the message bytes in buf starting at buf_offset.
     * @return the number of bytes added to buf.
     */
    public int getBytes(byte buf[],
			int buf_offset) 
    {
	if (bytes != null) 
	    System.arraycopy(bytes, offset, buf, buf_offset, length);
	return length;
    }

    /**
     * Returns the start offset of the bytes.
     */
    public int getOffset() {
	return offset;
    }

    /**
     * Returns the length of the bytes.
     */
    public int getLength() {
	return length;
    }

    /**
     * Returns true if the message bytes have been set.
     */
    public boolean isSet() {
	return bytes != null;
    }

    /**
     * Returns the message bytes parsed as an unsigned integer.
     * @exception NumberFormatException if the integer format was invalid
     */
    public int toInteger() throws NumberFormatException {
	return Ascii.parseInt(bytes, offset, length);
    }

    /**
     * Compares the message bytes to the specified subarray of bytes.
     * @param b the bytes to compare
     * @param off the start offset of the bytes
     * @param len the length of the bytes
     * @return true if the comparison succeeded, false otherwise
     */
    public boolean equals(byte[] b, int off, int len) {
	byte[] b1 = bytes;
	if (b1 == null || len != length) {
	    return false;
	}
	int off1 = offset;
	while (len-- > 0) {
	    if (b[off++] != b1[off1++]) {
		return false;
	    }
	}
	return true;
    }

    /**
     * Compares the message bytes to the specified subarray of bytes.
     * Case is ignored in the comparison.
     * @param b the bytes to compare
     * @param off the start offset of the bytes
     * @param len the length of the bytes
     * @return true if the comparison succeeded, false otherwise
     */
    public boolean equalsIgnoreCase(byte[] b, int off, int len) {
	byte[] b1 = bytes;
	if (b1 == null || len != length) {
	    return false;
	}
	int off1 = offset;
	while (len-- > 0) {
	    if (Ascii.toLower(b[off++]) != Ascii.toLower(b1[off1++])) {
		return false;
	    }
	}
	return true;
    }

    /**
     * Writes the message bytes to the specified output stream.
     * @param out the output stream
     * @exception IOException if an I/O error has occurred
     */
    public void write(OutputStream out) throws IOException {
	if (bytes != null) {
	    out.write(bytes, offset, length);
	}
    }

    /**
     * Returns the length of the message bytes.
     */
    public int length() {
	return bytes != null ? length : 0;
    }

    // --------------------
    /**
     * Returns the message bytes as a String object.
     */
    public String toString() {
        if (null == bytes) {
            return null;
        }

        try {
            return new String(bytes, offset, length, Constants.DEFAULT_CHAR_ENCODING);
        } catch (java.io.UnsupportedEncodingException e) {
            return null;        // could return something - but why?
        }
    }

    /**
     * Compares the message bytes to the specified String object.
     * @param s the String to compare
     * @return true if the comparison succeeded, false otherwise
     */
    public boolean equals(String s) {
	byte[] b = bytes;
	int len = length;
	if (b == null || len != s.length()) {
	    return false;
	}
	int off = offset;
	for (int i = 0; i < len; i++) {
	    if (b[off++] != s.charAt(i)) {
		return false;
	    }
	}
	return true;
    }

    /**
     * Compares the message bytes to the specified String object. Case is
     * ignored in the comparison.
     * @param s the String to compare
     * @return true if the comparison succeeded, false otherwise
     */
    public boolean equalsIgnoreCase(String s) {
	byte[] b = bytes;
	int len = length;
	if (b == null || len != s.length()) {
	    return false;
	}
	int off = offset;
	for (int i = 0; i < len; i++) {
	    if (Ascii.toLower(b[off++]) != Ascii.toLower((byte)s.charAt(i))) {
		return false;
	    }
	}
	return true;
    }

    /**
     * Returns true if the message bytes starts with the specified string.
     * @param s the string
     */
    public boolean startsWith(String s) {
	byte[] b = bytes;
	int len = s.length();
	if (b == null || len > length) {
	    return false;
	}
	int off = offset;
	for (int i = 0; i < len; i++) {
	    if (b[off++] != s.charAt(i)) {
		return false;
	    }
	}
	return true;
    }

}
