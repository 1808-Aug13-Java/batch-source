/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/facade/Attic/HttpServletResponseFacade.java,v 1.6.2.5 2001/04/30 03:06:47 marcsaeg Exp $
 * $Revision: 1.6.2.5 $
 * $Date: 2001/04/30 03:06:47 $
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


package org.apache.tomcat.facade;

import org.apache.tomcat.util.*;
import org.apache.tomcat.core.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.IllegalArgumentException;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author James Duncan Davidson [duncan@eng.sun.com]
 * @author Jason Hunter [jch@eng.sun.com]
 * @author James Todd [gonzo@eng.sun.com]
 * @author Costin Manolache
 * @author Hans Bergsten [hans@gefionsoftware.com]
 */
final class HttpServletResponseFacade  implements HttpServletResponse
{
    // Use the strings from core
    private static StringManager sm =  StringManager.getManager("org.apache.tomcat.core");

    private Response response;
    private boolean usingStream = false;
    private boolean usingWriter = false;
    ServletOutputStreamFacade osFacade=null;
    PrintWriter writer = null; // XXX will go away when we add the convertor

    /** Package
     */
    HttpServletResponseFacade(Response response) {
        this.response = response;
    }

    void recycle() {
	usingStream = false;
	usingWriter= false;
	writer=null;
	if( osFacade != null ) osFacade.recycle();
    }

    // -------------------- Public methods --------------------

    public void addCookie(Cookie cookie) {
        response.addCookie(cookie);
    }

    public boolean containsHeader(String name) {
	return response.containsHeader(name);
    }

    /** Delegate to various components of tomcat. This is not
     *  part of response, but session code.
     */
    public String encodeRedirectURL(String location) {
	if (isEncodeable(toAbsolute(location)))
	    return (toEncoded(location, response.getRequest().getSession(false)));
	else
	    return (location);
    }

    /**
     * @deprecated
     */
    public String encodeRedirectUrl(String location) {
	return encodeRedirectURL(location);
    }

    public String encodeURL(String url) {
	if (isEncodeable(toAbsolute(url)))
		return (toEncoded(url, response.getRequest().getSession(false)));
	else
		  return (url);
	 }

    /**
     * @deprecated
     */
    public String encodeUrl(String url) {
	return encodeURL(url);
    }

    public String getCharacterEncoding() {
	return response.getCharacterEncoding();
    }

    public ServletOutputStream getOutputStream() {
	if ( usingWriter ) {
	    String msg = sm.getString("serverResponse.outputStream.ise");
	    throw new IllegalStateException(msg);
	}
	usingStream=true;
	response.setUsingStream( true );

	if( osFacade!=null) return osFacade;
	if( response.getOutputBuffer() != null ) {
	    osFacade=new ServletOutputStreamFacade(response);
	    return osFacade;
	}

	// old mechanism
	return response.getOutputStream();
	// response.getBufferedOutputStream().getServletOutputStreamFacade();
    }

    public PrintWriter getWriter() throws IOException {
	if (usingStream) {
	    String msg = sm.getString("serverResponse.writer.ise");
	    throw new IllegalStateException(msg);
	}
	usingWriter= true ;
	response.setUsingWriter( true );

	// old mechanism
	if( osFacade==null && response.getOutputBuffer() == null )
	    return response.getWriter();

	if(  osFacade == null ) {
	    osFacade=new ServletOutputStreamFacade(response);
	}

	if( writer != null ) return writer;
	writer=((ResponseImpl)response).getWriter( osFacade );
	return writer;
    }

    public void sendError(int sc) throws IOException {
	sendError(sc, "No detailed message");
    }

    public void sendError(int sc, String msg) throws IOException {
	if (isCommitted()) {
	    Context ctx=response.getRequest().getContext();
	    ctx.log( "Servlet API error: sendError with commited buffer ");
	    // 	    /*DEBUG*/ try {throw new Exception(); } catch(Exception ex) {ex.printStackTrace();}
	    throw new IllegalStateException(sm.
					    getString("hsrf.error.ise"));
	}

	// 	if (sc != HttpServletResponse.SC_UNAUTHORIZED)	// CRM: FIXME
	// 	    response.resetBuffer();
	// Keep headers and cookies that are set

	setStatus( sc );
	Request request=response.getRequest();
	request.setAttribute("javax.servlet.error.message", msg);
	ContextManager cm=request.getContextManager();
	cm.handleStatus( request, response, sc );
    }

    public void sendRedirect(String location)
	throws IOException, IllegalArgumentException
    {
        if (location == null) {
            String msg = sm.getString("hsrf.redirect.iae");
            throw new IllegalArgumentException(msg);
	}

	// Even though DefaultErrorServlet will convert this
	// location to absolute (if required) we should do so
	// here in case the app has a non-default handler
	sendError(HttpServletResponse.SC_MOVED_TEMPORARILY,
		  toAbsolute(location));
    }

    public void setContentLength(int len) {
	response.setContentLength(len);
    }

    public void setContentType(String type) {
	response.setContentType(type);
    }

    public void setDateHeader(String name, long date) {
	MimeHeaders headers=response.getMimeHeaders();
	MimeHeaderField headerF=headers.find( name );
	if( headerF == null )
	    headerF=headers.putHeader();
	headerF.setName( name );
	headerF.setDateValue( date );
    }

    public void addDateHeader(String name, long value) {
	MimeHeaders headers=response.getMimeHeaders();
	MimeHeaderField headerF=headers.putHeader();
	headerF.setName( name );
	headerF.setDateValue( value );
    }

    public void setHeader(String name, String value) {
	response.setHeader(name, value);
    }

    public void addHeader(String name, String value) {
	response.addHeader(name, value);
    }

    public void setIntHeader(String name, int value) {
	response.setHeader(name, Integer.toString(value));
    }

    public void addIntHeader(String name, int value) {
        response.addHeader(name, Integer.toString(value));
    }

    public void setStatus(int sc) {
	response.setStatus(sc);
    }

    public void setBufferSize(int size) throws IllegalStateException {
	response.setBufferSize(size);
    }

    public int getBufferSize() {
	return response.getBufferSize();
    }

    public void reset() throws IllegalStateException {
	response.reset();
    }

    public boolean isCommitted() {
	return response.isBufferCommitted();
    }

    public void flushBuffer() throws IOException {
	response.flushBuffer();
    }

    public void setLocale(Locale loc) {
        response.setLocale(loc);
    }

    public Locale getLocale() {
	return response.getLocale();
    }

    /**
     *
     * @deprecated
     */
    public void setStatus(int sc, String msg) {
	response.setStatus(sc);
    }


    // -------------------- Private methods --------------------

    /**
     * Return <code>true</code> if the specified URL should be encoded with
     * a session identifier.  This will be true if all of the following
     * conditions are met:
     * <ul>
     * <li>The request we are responding to asked for a valid session
     * <li>The requested session ID was not received via a cookie
     * <li>The specified URL points back to somewhere within the web
     *     application that is responding to this request
     * </ul>
     *
     * @param location Absolute URL to be validated
     **/
    private boolean isEncodeable(String location) {

        // Is this an intra-document reference?
        if (location.startsWith("#"))
            return (false);

        // Are we in a valid session that is not using cookies?
        Request request = response.getRequest();
        HttpSession session = request.getSession(false);
        if(session == null)
            return false;
		  // If the session is new, encode the URL
		  if(!session.isNew() && request.getFacade().isRequestedSessionIdFromCookie())
            return false;

	// Is this a valid absolute URL?
	URL url = null;
	try {
	    url = new URL(location);
	} catch (MalformedURLException e) {
	    return (false);
	}

	// Does this URL match down to (and including) the context path?
	if (!request.getScheme().equalsIgnoreCase(url.getProtocol()))
	    return (false);
	if (!request.getServerName().equalsIgnoreCase(url.getHost()))
	    return (false);
    // Set the URL port to the HTTP(S) default if not available before comparing
    int urlPort = url.getPort();
    if (urlPort == -1) {
        if(request.isSecure())
            urlPort = 443;
        else
            urlPort = 80;
    }
	if (request.getServerPort() != urlPort)
	    return (false);
	String contextPath = request.getContext().getPath();
	if ((contextPath != null) && (contextPath.length() > 0)) {
	    String file = url.getFile();
	    if ((file == null) || !file.startsWith(contextPath))
		return (false);
	}

	// This URL belongs to our web application, so it is encodeable
	return (true);

    }


    /**
     * Convert (if necessary) and return the absolute URL that represents the
     * resource referenced by this possibly relative URL.  If this URL is
     * already absolute, return it unchanged.
     *
     * @param location URL to be (possibly) converted and then returned
     */
    private String toAbsolute(String location) {

	if (location == null)
	    return (location);

	// Construct a new absolute URL if possible (cribbed from
	// the DefaultErrorPage servlet)
	URL url = null;
	try {
	    url = new URL(location);
	} catch (MalformedURLException e1) {
	    Request request = response.getRequest();
	    String requrl =
		HttpUtils.getRequestURL(request.getFacade()).toString();
	    try {
		url = new URL(new URL(requrl), location);
	    } catch (MalformedURLException e2) {
		return (location);	// Give up
	    }
	}
	return (url.toString());

    }


    /**
     * Return the specified URL with the specified session identifier
     * suitably encoded.
     *
     * @param url URL to be encoded with the session id
     * @param sessionId Session id to be included in the encoded URL
     */
    private String toEncoded(String url, HttpSession session) {

	if ((url == null) || (session == null))
	    return (url);

   String sessionId = session.getId();
   String path = null;
   String everythingElse = null;
   // First look for a query string
   int pathEnd = url.indexOf("?");
   if(pathEnd < 0){
       // If no query string see if there's an anchor
       pathEnd = url.indexOf("#");
   }

	if(pathEnd < 0)
	    path = url;
	else {
	    path = url.substring(0, pathEnd);
	    everythingElse = url.substring(pathEnd);
	}
	StringBuffer sb = new StringBuffer(path);
	sb.append(";jsessionid=");
	sb.append(sessionId);
	if(everythingElse != null)
	    sb.append(everythingElse);

	return (sb.toString());

    }


}
