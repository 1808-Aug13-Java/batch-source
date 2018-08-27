/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/core/Attic/ResponseImpl.java,v 1.33.2.5 2001/01/12 04:39:04 larryi Exp $
 * $Revision: 1.33.2.5 $
 * $Date: 2001/01/12 04:39:04 $
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
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.tomcat.util.*;
import org.apache.tomcat.facade.*;
/**
 *
 * @author James Duncan Davidson [duncan@eng.sun.com]
 * @author Jason Hunter [jch@eng.sun.com]
 * @author James Todd [gonzo@eng.sun.com]
 * @author Harish Prabandham
 * @author Hans Bergsten <hans@gefionsoftware.com>
 */
public class ResponseImpl implements Response {
    protected static StringManager sm =
        StringManager.getManager("org.apache.tomcat.core");
    static final Locale DEFAULT_LOCALE=new Locale(Constants.LOCALE_DEFAULT, "");

    protected Request request;
    protected HttpServletResponse responseFacade;

    protected Vector userCookies = new Vector();
    protected String contentType = Constants.DEFAULT_CONTENT_TYPE;
    protected String contentLanguage = null;
    protected String characterEncoding = Constants.DEFAULT_CHAR_ENCODING;
    protected String sessionId;
    protected int contentLength = -1;
    protected int status = 200;
    private Locale locale = DEFAULT_LOCALE;

    protected MimeHeaders headers = new MimeHeaders();

    protected BufferedServletOutputStream out;
    protected PrintWriter writer;
    protected ByteBuffer bBuffer;

    protected boolean usingStream = false;
    protected boolean usingWriter = false;
    protected boolean started = false;
    protected boolean commited = false;
    
    boolean notIncluded=true;
    Exception errorException=null;
    String errorURI=null;

    // default implementation will just append everything here
    StringBuffer body=null;

    public ResponseImpl() {
	out=new BufferedServletOutputStream();
	out.setResponse(this);
    }

    public HttpServletResponse getFacade() {
        if( responseFacade==null ) {
	    Context ctx= request.getContext();
	    if( ctx == null ) {
		ctx=request.getContextManager().getContext("");
	    }
	    responseFacade = ctx.getFacadeManager().createHttpServletResponseFacade(this);
	}
	return responseFacade;
    }

    public void setRequest(Request request) {
	this.request = request;
    }

    public Request getRequest() {
	return request;
    }

    /* -------------------- */

    // Included response behavior
    public boolean isIncluded() {
	return ! notIncluded;
    }

    public void setIncluded( boolean incl ) {
	notIncluded= ! incl;
	if( incl ) {
	    // included behavior, no header output,
	    // no status change on errors.
	    // XXX we can optimize a bit - replace headers with
	    // an new Hashtable we can throw away. 
	} else {
	    // move back to normal behavior.

	}
    }

    public void setErrorException(Exception ex) {
	errorException = ex;
    }

    public Exception getErrorException() {
	return errorException;
    }

    public void setErrorURI(String uri) {
	errorURI = uri;
    }

    public String getErrorURI() {
	return errorURI;
    }
    
    public boolean isStarted() {
	return started;
    }
    
    public void recycle() {
	userCookies.removeAllElements(); // XXX reuse !!!
	contentType = Constants.DEFAULT_CONTENT_TYPE;
	contentLanguage = null;
        locale = DEFAULT_LOCALE;
	characterEncoding = Constants.DEFAULT_CHAR_ENCODING;
	contentLength = -1;
	status = 200;
	usingWriter = false;
	usingStream = false;
	sessionId=null;
	writer=null;
	started = false;
	commited = false;
	notIncluded=true;
	errorURI=null;
	// adapter
	body=null;
	if( out != null ) out.recycle();
	if( bBuffer != null ) bBuffer.recycle();
	headers.clear();
    }

    public void finish() throws IOException {
	if (usingWriter && (writer != null)) {
	    writer.flush();
	    writer.close();
	}
	if( bBuffer != null) {
	    bBuffer.flush();
	    request.getContextManager().doAfterBody(request, this);
	    return;
	}
	out.flush();
	out.reallyFlush();
	request.getContextManager().doAfterBody(request, this);
	out.close();
    }

    public boolean containsHeader(String name) {
	return headers.containsHeader(name);
    }

    // XXX
    // mark whether or not we are being used as a stream our writer

    public boolean isUsingStream() {
	return usingStream;
    }

    public void setUsingStream( boolean stream ) {
	usingStream=stream;
    }
    
    public boolean isUsingWriter() {
	return usingWriter;
    }

    public void setUsingWriter( boolean writer ) {
	usingWriter=writer;
	out.setUsingWriter (true);
    }

    public PrintWriter getWriter() throws IOException {
	return getWriter( out );
    }

    public PrintWriter getWriter(ServletOutputStream outs) throws IOException {
	if(writer!=null) return writer;
	// it already did all the checkings
	
	started = true;
	usingWriter = true;
	
	writer = new ServletWriterFacade( getConverter(outs), this);
	return writer;
    }

    public Writer getConverter( ServletOutputStream outs ) throws IOException {
	String encoding = getCharacterEncoding();

	if (encoding == null) {
	    // use default platform encoding - is this correct ? 
	    return  new OutputStreamWriter(outs);
        }  else {
	    try {
		return  new OutputStreamWriter(outs, encoding);
	    } catch (java.io.UnsupportedEncodingException ex) {
		// XXX log it
		System.out.println("Unsuported encoding: " + encoding );

		return new OutputStreamWriter(outs);
	    }
	}
    }

    public ByteBuffer getOutputBuffer() {
	return bBuffer;
    }

    public void setOutputBuffer(ByteBuffer buf) {
	bBuffer=buf;
	if( buf!= null) buf.setParent( this );
    }
    
    /** Either implement ServletOutputStream or return BufferedServletOutputStream(this)
	and implement doWrite();
	@deprecated 
     */
    public ServletOutputStream getOutputStream() {
	started = true;
	return out; // out.getServletOutputStreamFacade();
    }

    /** Either implement ServletOutputStream or return BufferedServletOutputStream(this)
	and implement doWrite();
     */
    public BufferedServletOutputStream getBufferedOutputStream() {
	return out;
    }
    

    // -------------------- Headers --------------------
    public MimeHeaders getMimeHeaders() {
	return headers;
    }


    public void setHeader(String name, String value) {
        if( isIncluded() ) return;      // We are in an included sub-request
	if( ! checkSpecialHeader(name, value) ) 
	    headers.putHeader(name, value);
    }

    public void addHeader(String name, String value) {
        if( isIncluded() ) return;      // We are in an included sub-request
	if( ! checkSpecialHeader(name, value) ) 
	    headers.addHeader(name, value);
    }

    
    /** Set internal fields for special header names. Called from set/addHeader.
	Return true if the header is special, no need to set the header.
     */
    private boolean checkSpecialHeader( String name, String value) {
	// XXX Eliminate redundant fields !!!
	// ( both header and in special fields )
	if( name.equalsIgnoreCase( "Content-Type" ) ) {
	    setContentType( value );
	    return true;
	}
	if( name.equalsIgnoreCase( "Content-Length" ) ) {
	    try {
		int cL=Integer.parseInt( value );
		setContentLength( cL );
		return true;
	    } catch( NumberFormatException ex ) {
		// We shouldn't set the header 
	    }
	}
	if( name.equalsIgnoreCase( "Content-Language" ) ) {
	    // XXX XXX Need to construct Locale or something else
	}
	return false;
    }

    public int getBufferSize() {
	if( bBuffer != null ) return bBuffer.getBufferSize();
	return out.getBufferSize();
    }

    public void setBufferSize(int size) throws IllegalStateException {
	// Force the PrintWriter to flush the data to the OutputStream.
	if (usingWriter == true && writer != null ) writer.flush();

	if( bBuffer != null ) {
	    if( bBuffer.isContentWritten() ) {
		throw new IllegalStateException ( sm.getString("servletOutputStreamImpl.setbuffer.ise"));
	    }
	    bBuffer.setBufferSize(size);
	    return;
	}
	
	if (out.isContentWritten() == true) {
	    throw new IllegalStateException ( sm.getString("servletOutputStreamImpl.setbuffer.ise"));
	}
	out.setBufferSize(size);
    }

    /*
     * Methodname "isCommitted" already taken by Response class.
     */
    public boolean isBufferCommitted() {
	return commited;
	//	return out.isCommitted();
    }

    public void setBufferCommitted( boolean v ) {
	this.commited=v;
	// 	System.out.println("Buffer commited " );
	// 	/*DEBUG*/ try {throw new Exception(); }
	//catch(Exception ex) {ex.printStackTrace();}
    }
    
    public void reset() throws IllegalStateException {

        if( isIncluded() ) return;      // We are in an included sub-request

	// Force the PrintWriter to flush its data to the output
        // stream before resetting the output stream
        //
        userCookies.removeAllElements();  // keep system (session) cookies
        contentType = Constants.DEFAULT_CONTENT_TYPE;
        locale = DEFAULT_LOCALE;
        characterEncoding = Constants.DEFAULT_CHAR_ENCODING;
        contentLength = -1;
        status = 200;

	if (usingWriter == true && writer != null)
	    writer.flush();

	body=null;
	// Reset the stream
	out.reset();

        // Clear the cookies and such

        // Clear the headers
        headers.clear();
    }

    // Reset the response buffer but not headers and cookies
    public void resetBuffer() throws IllegalStateException {
	if( usingWriter && writer != null )
	    writer.flush();

	out.reset();	// May throw IllegalStateException

    }

    public void flushBuffer() throws IOException {
	//	if( notIncluded) {
	    if (usingWriter == true && writer != null)
		writer.flush();
	    
	    out.reallyFlush();
	    //} 
    }


    /** Signal that we're done with the headers, and body will follow.
     *  Any implementation needs to notify ContextManager, to allow
     *  interceptors to fix headers.
     */
    public void endHeaders() throws IOException {
	notifyEndHeaders();
    }

    /** Signal that we're done with the headers, and body will follow.
     *  Any implementation needs to notify ContextManager, to allow
     *  interceptors to fix headers.
     */
    public void notifyEndHeaders() throws IOException {
	//	System.out.println("End headers " + request.getProtocol());
	if(request.getProtocol()==null) // HTTP/0.9 
	    return;

	// let CM notify interceptors and give a chance to fix
	// the headers
	if(request.getContext() != null) 
	    request.getContext().getContextManager().doBeforeBody(request, this);

	// No action.. 
    }

    public void addCookie(Cookie cookie) {
        if( isIncluded() ) return;      // We are in an included sub-request
	addHeader( CookieTools.getCookieHeaderName(cookie),
			    CookieTools.getCookieHeaderValue(cookie));
	if( cookie.getVersion() == 1 ) {
	    // add a version 0 header too.
	    // XXX what if the user set both headers??
	    Cookie c0 = (Cookie)cookie.clone();
	    c0.setVersion(0);
	    addHeader( CookieTools.getCookieHeaderName(c0),
				CookieTools.getCookieHeaderValue(c0));
	}
	userCookies.addElement(cookie);
    }

    public Enumeration getCookies() {
	return userCookies.elements();
    }

    public void setSessionId( String id ) {
	if( notIncluded ) sessionId=id;
    }

    public String getSessionId() {
	return sessionId;
    }
    
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        if (locale == null || isIncluded() ) {
            return;  // throw an exception?
        }

        // Save the locale for use by getLocale()
        this.locale = locale;

        // Set the contentLanguage for header output
        contentLanguage = locale.getLanguage();

        // Set the contentType for header output
        // Use the setContentType() method so encoding is set properly
        String newType = constructLocalizedContentType(contentType, locale);
        setContentType(newType);

	// only one header !
	headers.putHeader("Content-Language", contentLanguage);
    }

    /** Utility method for parsing the mime type and setting
     *  the encoding to locale. Also, convert from java Locale to mime encodings
    */
    private static String constructLocalizedContentType(String type, Locale loc) {
        // Cut off everything after the semicolon
        int semi = type.indexOf(";");
        if (semi != -1) {
            type = type.substring(0, semi);
        }

        // Append the appropriate charset, based on the locale
        String charset = LocaleToCharsetMap.getCharset(loc);
        if (charset != null) {
            type = type + "; charset=" + charset;
        }

        return type;
    }

    public String getCharacterEncoding() {
	return characterEncoding;
    }

    public void setContentType(String contentType) {
        if( isIncluded() ) return;      // We are in an included sub-request
	this.contentType = contentType;
	String encoding = RequestUtil.getCharsetFromContentType(contentType);
        if (encoding != null) {
	    characterEncoding = encoding;
        }
	headers.putHeader("Content-Type", contentType);
    }

    public String getContentType() {
	return contentType;
    }
    
    public void setContentLength(int contentLength) {
        if( isIncluded() ) return;      // We are in an included sub-request
	this.contentLength = contentLength;
	headers.putHeader("Content-Length", (new Integer(contentLength)).toString());
    }

    public int getContentLength() {
	return contentLength;
    }

    public int getStatus() {
        return status;
    }

    
    /** Set the response status 
     */ 
    public void setStatus( int status ) {
        if( isIncluded() ) return;      // We are in an included sub-request
	this.status=status;
    }

    /** Write a chunk of bytes. Should be called only from ServletOutputStream implementations,
     *	No need to implement it if your adapter implements ServletOutputStream.
     *  Headers and status will be written before this method is exceuted.
     */
    public void doWrite( byte buffer[], int pos, int count) throws IOException {
        // XXX fix if charset is other than default.
        if( body==null)
	    body=new StringBuffer();
	body.append(new String(buffer, pos, count, 
			       Constants.DEFAULT_CHAR_ENCODING) );
    }

    public StringBuffer getBody() {
	return body;
    }

    // utility method - should be in a different class
    public static String getMessage( int status ) {
	return sm.getString("sc."+ status);
    }

}
