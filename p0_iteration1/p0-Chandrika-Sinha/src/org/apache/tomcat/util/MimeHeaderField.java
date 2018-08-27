/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/util/Attic/MimeHeaderField.java,v 1.10.2.3 2001/07/30 00:57:34 marcsaeg Exp $
 * $Revision: 1.10.2.3 $
 * $Date: 2001/07/30 00:57:34 $
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

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.text.*;

/**
 * This class is used to represent a MIME header field.
 * It uses MessageString, and can be used in 0-GC mode ( no
 * garbage generated unless toString() is called )
 *
 *
 * @author dac@eng.sun.com
 * @author James Todd [gonzo@eng.sun.com]
 */
public class MimeHeaderField {
    public static final byte[] charval = { 
	(byte)'0', (byte)'1', (byte)'2', (byte)'3', (byte)'4',
	(byte)'5', (byte)'6', (byte)'7', (byte)'8', (byte)'9' 
    };

    private static StringManager sm =
        StringManager.getManager("org.apache.tomcat.util");

    /**
     * The header field name.
     */
    protected final MessageBytes nameB = new MessageBytes();
    protected final MessageChars nameC = new MessageChars();
    protected final MessageString name = new MessageString();

    /**
     * The header field value.
     */
    protected final MessageBytes valueB = new MessageBytes();
    protected final MessageChars valueC = new MessageChars();
    protected final MessageString value = new MessageString();

    /** The header field integer value. */
    protected int intValue;

    /** The header field Date value.   */
    protected Date dateValue = null;

    /**
     * The header field value type.
     */
    protected int type = T_NULL;
    protected int nameType=T_NULL;
    
    protected static final int T_NULL = 0;
    protected static final int T_STR  = 1;
    protected static final int T_INT  = 2;
    protected static final int T_DATE = 3;
    protected static final int T_BYTES = 4;
    protected static final int T_CHARS = 5;

    /**
     * Creates a new, uninitialized header field.
     */
    public MimeHeaderField() {
    }

    /**
     * Resets the header field to an uninitialized state.
     */
    public void reset() {
	name.reset();
	value.reset();
	nameB.reset();
	valueB.reset();
	nameC.reset();
	valueC.reset();
	type = T_NULL;
	nameType = T_NULL;
    }

    /**
     * Sets the header field name to the specified string.
     * @param s the header field name String
     */
    public void setName(String s) {
	nameType = T_STR;
	name.setString(s);
    }

    /**
     * Sets the header field name to the specified string.
     * @param s the header field name String
     */
    public void setName(char c[], int off, int len ) {
	nameType = T_CHARS;
	nameC.setChars(c, off, len);
    }

    /**
     * Sets the header field name to the specified subarray of bytes.
     * @param b the header field name bytes
     * @param off the start offset of the bytes
     * @param len the length of the bytes
     */
    public void setName(byte[] b, int off, int len) {
	nameType = T_BYTES;
	nameB.setBytes(b, off, len);
    }

    public int getNameType() {
	return nameType;
    }
    
    /**
     * Returns the header field name as a String.
     * @ deprecated - no encoding support
     */
    public String getName() {
	switch (nameType) {
	case T_STR:
	    return name.toString();
	case T_CHARS:
	    return nameC.toString(); 
	case T_BYTES:
	    return nameB.toString(); // XXX encoding
	default:
	    return null;
	}
    }

    public MessageBytes getNameBytes() {
	return nameB;
    }

    public MessageChars getNameChars() {
	return nameC;
    }

    // -------------------- Value --------------------


    /**
     * Sets the header field value to the specified string.
     * @param s the header field value String
     */
    public void setValue(String s) {
	value.setString(s);
	type = T_STR;
    }

    /**
     * Sets the header field value to the specified subarray of bytes.
     * @param b the header field value bytes
     * @param off the start offset of the bytes
     * @param len the length of the bytes
     */
    public void setValue(byte[] b, int off, int len) {
	valueB.setBytes(b, off, len);
	type = T_BYTES;
    }

    public void setValue(char[] b, int off, int len) {
	valueC.setChars(b, off, len);
	type = T_CHARS;
    }

    /**
     * Sets the header field to the specified integer value.
     * @param i the header field integer value
     */
    public void setIntValue(int i) {
	intValue = i;
	type = T_INT;
    }

    /**
     * Sets the header field date value to the specified time.
     * @param t the time in milliseconds since the epoch
     */
    public void setDateValue(long t) {
	if( dateValue==null)
	    dateValue=new Date(t);
	else
	    dateValue.setTime(t);
	type = T_DATE;
    }

    /**
     * Returns the header field value as a String, or null if not set.
     * @deprecated No encoding support
     */
    public String getValue() {
	switch (type) {
	case T_STR:
	    return value.toString();
	case T_INT:
	    return String.valueOf(intValue);
	case T_DATE:
	    return formatDate(dateValue);
	case T_CHARS:
	    return valueC.toString(); 
	case T_BYTES:
	    return valueB.toString(); // XXX encoding
	default:
	    return null;
	}
    }

    public MessageBytes getValueBytes() {
	return valueB;
    }

    public MessageChars getValueChars() {
	return valueC;
    }

    /**
     * Returns the integer value of the header field.
     * @exception NumberFormatException if the integer format was invalid
     */
    public int getIntValue()
	throws NumberFormatException
    {
	switch (type) {
	case T_INT:
	    return intValue;
	case T_STR:
	    return value.toInteger();
	case T_CHARS:
	    return valueC.toInteger();
	case T_BYTES:
	    return valueB.toInteger();
	default:
            String msg = sm.getString("mimeHeaderField.int.nfe");

	    throw new NumberFormatException(msg);
	}
    }

    /**
     * Returns the date value of the header field.
     * @return the header date value in number of milliseconds since the epoch
     * @exception IllegalArgumentException if the date format was invalid
     */
    public long getDateValue()
	throws IllegalArgumentException
    {
	switch (type) {
	case T_DATE:
	    if( dateValue==null) break;
	    return dateValue.getTime();
	case T_STR:
	    return parseDate( value );
	case T_CHARS:
	    return parseDate( value.toString() );
	case T_BYTES:
	    return parseDate( valueB.toString() ); // XXX Encoding
	}
	String msg = sm.getString("mimeHeaderField.date.iae");
	throw new IllegalArgumentException(msg);
    }

    public int getValueType() {
	return type;
    }
    
    String formatDate( Date value ) {
	return DateTool.format1123(value);
    }

    long parseDate( String s ) {
	// XXX inefficient
	value.setString( s );
	return parseDate( value );
    }

    long parseDate( MessageString value ) {
	String dateString=value.toString();
	Date date=null;
        try {
            date = DateTool.rfc1123Format.parse(dateString);
	    return date.getTime();
	} catch (ParseException e) { }

        try {
	    date = DateTool.rfc1036Format.parse(dateString);
	    return date.getTime();
	} catch (ParseException e) { }
	
        try {
            date = DateTool.asctimeFormat.parse(dateString);
	    return date.getTime();
        } catch (ParseException pe) {
        }
	String msg = sm.getString("httpDate.pe", dateString);
	throw new IllegalArgumentException(msg);
    }

    /**
     * Returns true if the header field has the specified name. Character
     * case is ignored in the comparison.
     * @param s the string to compare
     */
    public boolean nameEquals(String s) {
	switch (nameType) {
	case T_STR:
	    return name.equalsIgnoreCase(s);
	case T_CHARS:
	    return nameC.equalsIgnoreCase(s);
	case T_BYTES:
	    return Ascii.equalsIgnoreCase( s, nameB );
	default:
	    return false;
	}
    }

}
