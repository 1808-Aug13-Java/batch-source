/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/util/Attic/MimeHeaders.java,v 1.9.2.1 2000/11/10 06:42:52 craigmcc Exp $
 * $Revision: 1.9.2.1 $
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

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.NoSuchElementException;

/* XXX XXX XXX Need a major rewrite  !!!!
 */

/**
 * This class is used to contain standard internet message headers,
 * used for SMTP (RFC822) and HTTP (RFC2068) messages as well as for
 * MIME (RFC 2045) applications such as transferring typed data and
 * grouping related items in multipart message bodies.
 *
 * <P> Message headers, as specified in RFC822, include a field name
 * and a field body.  Order has no semantic significance, and several
 * fields with the same name may exist.  However, most fields do not
 * (and should not) exist more than once in a header.
 *
 * <P> Many kinds of field body must conform to a specified syntax,
 * including the standard parenthesized comment syntax.  This class
 * supports only two simple syntaxes, for dates and integers.
 *
 * <P> When processing headers, care must be taken to handle the case of
 * multiple same-name fields correctly.  The values of such fields are
 * only available as strings.  They may be accessed by index (treating
 * the header as an array of fields), or by name (returning an array
 * of string values).
 */

/**
 *  Memory-efficient repository for Mime Headers. When the object is recycled, it
 *  will keep the allocated headers[] and all the MimeHeaderField - no GC is generated.
 *
 *  For input headers it is possible to use the MessageByte for Fileds - so no GC
 *  will be generated.
 *
 *  The only garbage is generated when using the String for header names/values -
 *  this can't be avoided when the servlet calls header methods, but is easy
 *  to avoid inside tomcat. The goal is to use _only_ MessageByte-based Fields,
 *  and reduce to 0 the memory overhead of tomcat.
 *
 *  TODO:
 *  XXX one-buffer parsing - for http ( other protocols don't need that )
 *  XXX remove unused methods
 *  XXX External enumerations, with 0 GC.
 *  XXX use HeaderName ID
 *  
 * 
 * @author dac@eng.sun.com
 * @author James Todd [gonzo@eng.sun.com]
 * @author Costin Manolache
 */
public class MimeHeaders {
    /** Initial size - should be == average number of headers per request
     *  XXX  make it configurable ( fine-tuning of web-apps )
     */
    public static final int DEFAULT_HEADER_SIZE=8;
    
    /**
     * The header fields.
     */
    private MimeHeaderField[] headers = new MimeHeaderField[DEFAULT_HEADER_SIZE];

    /**
     * The current number of header fields.
     */
    private int count;

    /**
     * Creates a new MimeHeaders object using a default buffer size.
     */
    public MimeHeaders() {
    }

    /**
     * Clears all header fields.
     */
    public void clear() {
	for (int i = 0; i < count; i++) {
	    headers[i].reset();
	}
	count = 0;
    }

    /**
     * Returns the current number of header fields.
     */
    public int size() {
	return count;
    }

    /**
     * Returns the Nth header field, or null if there is no such header.
     * This may be used to iterate through all header fields.
     */
    public MimeHeaderField getField(int n) {
	return n >= 0 && n < count ? headers[n] : null;
    }

    
    /**
     * Finds and returns a header field with the given name.  If no such
     * field exists, null is returned.  If more than one such field is
     * in the header, an arbitrary one is returned.
     */
    public MimeHeaderField find(String name) {
        for (int i = 0; i < count; i++) {
	    if (headers[i].nameEquals(name)) {
                return headers[i];
            }
        }
        return null;
    }

    /**
     * Adds a partially constructed field to the header.  This
     * field has not had its name or value initialized.
     */
    public MimeHeaderField putHeader() {
	MimeHeaderField mh;
	int len = headers.length;
	if (count >= len) {
	    // expand header list array
	    MimeHeaderField tmp[] = new MimeHeaderField[count * 2];
	    System.arraycopy(headers, 0, tmp, 0, len);
	    headers = tmp;
	}
	if ((mh = headers[count]) == null) {
	    headers[count] = mh = new MimeHeaderField();
	}
	count++;
	return mh;
    }


    
    
    // -------------------- 
    // Please avoid using any of the methods following this line. 
    // ( most of them will generate GC, or are http sepecific )
    // ------------------------------------------------------------
    private static StringManager sm =
        StringManager.getManager("org.apache.tomcat.util");
    int bufSize=512; // default
    /**
     * A buffer used when parsing headers.
     */
    private byte[] buf=null;

    
    /**
     * Creates a new MimeHeaders object using the specified buffer size.
     * @param len the buffer size initially used for parsing headers
     */
    public MimeHeaders(int len) {
	bufSize=len;
	// buf = new byte[len];
    }

    /**
     * Returns an enumeration of strings representing the header field names.
     * Field names may appear multiple times in this enumeration, indicating
     * that multiple fields with that name exist in this header.
     */
    public Enumeration names() {
	return new MimeHeadersEnumerator(this);
    }

    // NOTE:  All of these put/get "Header" calls should
    // be renamed to put/get "field" !!!  This object is
    // the header, and its components are called fields.

    /**
     * Creates a new header field whose value is the specified string.
     * @param name the header name
     * @param s the header field string value
     */
    public void putHeader(String name, String s) {
	putHeader(name).setValue(s);
    }

    public void addHeader(String name, String s) {
        addHeader(name).setValue(s);
    }

    /**
     * Creates a new header field whose value is the specified integer.
     * @param name the header name
     * @param i the header field integer value
     */
    public void putIntHeader(String name, int i) {
	putHeader(name).setIntValue(i);
    }

    public void addIntHeader(String name, int i) {
        addHeader(name).setIntValue(i);
    }

    /**
     * Creates a new header field whose value is the specified time.
     * The encoding uses RFC 822 date format, as updated by RFC 1123.
     * @param name the header name
     * @param t the time in number of milliseconds since the epoch
     */
    public void putDateHeader(String name, long t) {
	putHeader(name).setDateValue(t);
    }

    public void addDateHeader(String name, long t) {
        addHeader(name).setDateValue(t);
    }

    /**
     * Returns the string value of one of the headers with the
     * specified name.
     * @see getHeaders
     * @param name the header field name
     * @return the string value of the field, or null if none found
     */
    public String getHeader(String name) {
	MimeHeaderField mh = find(name);

	return mh != null ? mh.getValue() : null;
    }

    /**
     * Returns the string value of all of the headers with the
     * specified name.
     * @see getHeader
     * @param name the header field name
     * @return array values of the fields, or null if none found
     */
    public String[] getHeaders(String name) {
	Vector values = getHeadersVector(name);

	if (values.size() > 0) {
	    String retval[] = new String[values.size()];

	    for (int i = 0; i < retval.length; i++)
		retval[i] = (String)values.elementAt(i);
	    return retval;
	}
	return null;
    }

    /** Same as getHeaders, return a Vector - avoid Vector-[]-Vector conversion
     */
    public Vector getHeadersVector(String name) {
	Vector values = new Vector();

	for (int i = 0; i < count; i++) {
	    if (headers[i].nameEquals(name))
		values.addElement(headers[i].getValue());
	}

	return values;
    }

    /**
     * Returns the integer value of a header with the specified name.
     * @param name the header field name
     * @return the integer value of the header field, or -1 if the header
     *	       was not found
     * @exception NumberFormatException if the integer format was invalid
     */

    public int getIntHeader(String name) throws NumberFormatException {
	MimeHeaderField mh = find(name);

	return mh != null ? mh.getIntValue() : -1;
    }

    /**
     * Returns the date value of a header with the specified name.
     * @param name the header field name
     * @return the date value of the header field in number of milliseconds
     *	       since the epoch, or -1 if the header was not found
     * @exception IllegalArgumentException if the date format was invalid
     */
    public long getDateHeader(String name) throws IllegalArgumentException {
	MimeHeaderField mh = find(name);

	return mh != null ? mh.getDateValue() : -1;
    }

    /**
     * Returns the name of the nth header field where n >= 0. Returns null
     * if there were fewer than (n + 1) fields. This can be used to iterate
     * through all the fields in the header.
     */
    public String getHeaderName(int n) {
	return n >= 0 && n < count ? headers[n].getName() : null;
    }

    /**
     * Returns the body of the nth header field where n >= 0. Returns null
     * if there were fewer than (n + 1) fields. This can be used along
     * with getHeaderName to iterate through all the fields in the header.
     */
    public String getHeader(int n) {
	return n >= 0 && n < count ? headers[n].getValue() : null;
    }

    /**
     * Returns the number of fields using a given field name.
     */
    public int getFieldCount (String name) {
	int retval = 0;

	for (int i = 0; i < count; i++)
	    if (headers [i].nameEquals (name))
		retval++;

	return retval;
    }

    /**
     * Removes a header field with the specified name.  Does nothing
     * if such a field could not be found.
     * @param name the name of the header field to be removed
     */
    public void removeHeader(String name) {
        // XXX
        // warning: rather sticky code; heavily tuned

        for (int i = 0; i < count; i++) {
	    if (headers[i].nameEquals(name)) {
	        // reset and swap with last header
	        MimeHeaderField mh = headers[i];

		mh.reset();
		headers[i] = headers[count - 1];
		headers[count - 1] = mh;

		count--;
		i--;
	    }
	}
    }

    /**
     * Returns true if the specified field is contained in the header,
     * otherwise returns false.
     * @param name the field name
     */
    public boolean containsHeader(String name) {
	return find(name) != null;
    }


    /**
     * Finds a header field given name.  If the header doesn't exist,
     * it will create a new one.
     * @param name the header field name
     * @return the new field
     */
    protected MimeHeaderField putHeader(String name) {
        if (containsHeader(name)) {
	    removeHeader(name);
	}

	return addHeader(name);
    }

    protected MimeHeaderField addHeader(String name) {
 	MimeHeaderField mh = putHeader();

	mh.setName(name);

	return mh;
    }
    
    /**
     * Creates a new header with given name, and add it to the headers.
     * @param name the header field name
     * @param s the header value
     * @return the new field
     */
    public void appendHeader(String name, String s) {
	MimeHeaderField mh = putHeader();

	mh.setName(name);
	mh.setValue(s);
    }
    
    
    /**
     * Returns a lengthly string representation of the current header fields.
     */
    public String toString() {
	StringBuffer sb = new StringBuffer();

	sb.append("{");

	for (int i = 0; i < count; i++) {
	    sb.append("{");
	    sb.append(headers[i].toString());
	    sb.append("}");

	    if (i < count - 1) {
		sb.append(",");
	    }
	}

	sb.append("}");

	return sb.toString();
    }

    /**
     * Dumps current headers to specified PrintStream for debugging.
     */

    public void dump(PrintStream out) {
	for (int i = 0; i < count; i++) {
	    out.println(headers[i]);
	}
    }
}

class MimeHeadersEnumerator implements Enumeration {
    private static StringManager sm =
        StringManager.getManager("org.apache.tomcat.util");
    private Hashtable hash;
    private Enumeration delegate;

    MimeHeadersEnumerator(MimeHeaders headers) {
        // Store header names in a Hashtable to guarantee uniqueness
        // This has the side benefit of letting us use Hashtable's enumerator
        hash = new Hashtable();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            hash.put(headers.getHeaderName(i), "");
        }
        delegate = hash.keys();
    }

    public boolean hasMoreElements() {
	return delegate.hasMoreElements();
    }

    public Object nextElement() {
        try {
            return delegate.nextElement();
        }
        catch (NoSuchElementException e) {
            String msg = sm.getString("mimeHeaderEnumerator.next.nse");
	    throw new NoSuchElementException(msg);
	}
    }
}

