/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/util/Attic/SessionUtil.java,v 1.5.2.4 2001/05/02 17:49:37 costin Exp $
 * $Revision: 1.5.2.4 $
 * $Date: 2001/05/02 17:49:37 $
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


import javax.servlet.http.Cookie;
import org.apache.tomcat.core.*;
import org.apache.tomcat.util.compat.*;

import java.security.*;

/**
 * General purpose utilities useful to <code>Manager</code> and
 * <code>Session</code> implementations.
 *
 * @author Craig R. McClanahan
 * @author Shai Fultheim [shai@brm.com]
 * @version $Revision: 1.5.2.4 $ $Date: 2001/05/02 17:49:37 $
 */

public final class SessionUtil {


    // ------------------------------------------------------- Static Variables


    /**
     * Private counter used in generating session identifiers.
     */
    private static int counter = 1010;


    // --------------------------------------------------------- Public Methods


    /**
     * Construct and return an HTTP Cookie object that contains the specified
     * session id.  If a cookie cannot be created for any reason, return
     * <code>null</code>.
     *
     * @param req The request asking for this cookie to be created
     * @param id The session id for which a cookie should be constructed
     */
    public static Cookie createCookie(Request req, String id) {

	Cookie cookie = new Cookie(Constants.SESSION_COOKIE_NAME, id);
	String serverName = req.getServerName();
	if (serverName != null)
	    cookie.setDomain(serverName);
	String contextPath = req.getContext().getPath();
	if ((contextPath != null) && (contextPath.length() > 0))
	    cookie.setPath(contextPath);
	else
	    cookie.setPath("/");
	cookie.setMaxAge(-1);
	cookie.setVersion(1);
	return (cookie);

    }


    /**
     * Encode the specified session id into the specified redirect URL,
     * if it is an absolute URL that returns to the specified host name
     * (presumably the host name on which this request was received).
     * If URL rewriting is disabled or unnecessary, the specified URL
     * will be returned unchanged.
     *
     * @param req The request for which we are encoding the session id
     * @param id The session id to be encoded
     * @param url The URL to be encoded with the session id
     *
     * @exception IllegalArgumentException if the specified URL is
     *	not absolute
     */
    public static String encodeRedirectURL(Request req, String id,
					   String url) {

	// FIXME: Are the rules really the same?
	return (encodeURL(req, id, url));

    }


    /**
     * Encode the specified session id into the specified URL,
     * if it is a relative URL or an absolute URL that returns to the
     * specified host name (presumably the host name on which this request
     * was received).  If URL rewriting is disabled or unnecessary,
     * the specified URL will be returned unchanged.
     *
     * @param req The request for which we are encoding the session id
     * @param id The session id to be encoded
     * @param url The URL to be encoded with the session id
     */
    public static String encodeURL(Request req, String id, String url) {
        String sch=req.getScheme();
        // Encode all relative URLs unless they start with a hash
	if (!url.startsWith(sch+":")) {
	    if (!url.startsWith("#"))
		return (encode(id, url));
	    else
		return (url);
	}

	// Encode all absolute URLs that return to this hostname
	String serverName = req.getServerName();
	String match = sch+"://" + serverName;
	if (url.startsWith(sch+"://" + serverName))
	    return (encode(id, url));
	else
	    return (url);

    }

    static Jdk11Compat jdk11Compat=Jdk11Compat.getJdkCompat();

    /**
     * Generate and return a new session identifier.
     */
    public static String generateSessionId(String jsIdent) {
        /**
         * When using a SecurityManager and a JSP page or servlet triggers
         * creation of a new session id it must be performed with the 
         * Permissions of this class using doPriviledged because the parent
         * JSP or servlet may not have sufficient Permissions.
         */
        if( System.getSecurityManager() != null ) {
            PriviledgedIdGenerator di=new PriviledgedIdGenerator(jsIdent);
	    try {
		return (String)jdk11Compat.doPrivileged(di);
	    } catch( Exception ex) {
		ex.printStackTrace();
		return null;
	    }
        } else {            
            return SessionIdGenerator.generateId(jsIdent);
	}
    }

    static class PriviledgedIdGenerator extends Action {
	// SessionIdGenerator sg;
	String jsIdent;
	public PriviledgedIdGenerator(String jsIdent ) {
	    //this.sg=sg;
	    this.jsIdent=jsIdent;
	}           
	public Object run() {
	    //	    return sg.getIdentifier(jsIdent);
	    return SessionIdGenerator.generateId(jsIdent);
	}           
    }    

    /**
     * Return the session id from the specified array of cookies,
     * where the session id cookie was presumably created by the
     * <code>createCookie()</code> method of this Manager.
     * If there is no session id cookie included, return <code>null</code>.
     *
     * @param cookies Array of cookies from which to extract the session id
     */
    public static String parseSessionId(Cookie cookies[]) {

	if (cookies == null)
	    return (null);
	for (int i = 0; i < cookies.length; i++) {
	    if (Constants.SESSION_COOKIE_NAME.equals(cookies[i].getName()))
		return (cookies[i].getValue());
	}
	return (null);

    }


    /**
     * Return the session id from the specified request URI, where
     * it was presumably encoded via the <code>encodeRedirectURL()</code> or
     * <code>encodeURL()</code> method of this Manager.
     * If there is no session id included, return <code>null</code>.
     *
     * @param uri The request URI from which to extract the session id
     */
    public static String parseSessionId(String uri) {

	// Search for the required match string in the URI
	String match = ";" + Constants.SESSION_PARAMETER_NAME + "=";
	int m = uri.indexOf(match);
	if (m < 0)
	    return (null);

	// Parse the session identifier
	String temp = uri.substring(m + match.length());
	int s = temp.indexOf(';');
	int q = temp.indexOf('?');
	if (s < 0) {
	    if (q < 0)
		return (temp);
	    else
		return (temp.substring(0, q));
	} else {
	    if (q < 0)
		return (temp.substring(0, s));
	    else if (q < s)
		return (temp.substring(0, q));
	    else
		return (temp.substring(0, s));
	}

    }


    // -------------------------------------------------------- Private Methods


    /**
     * Encode the specified session identifier into the specified URL,
     * and return the resulting string.
     *
     * @param id The session id to be encoded
     * @param url The URL to be encoded with the session id
     */
    private static String encode(String id, String url) {

	if ((id == null) || (url == null))
	    return (url);

	// Locate the beginning of the query string (if any)
	int question = url.indexOf('?');

	// Encode the session identifier appropriately (before any query)
	StringBuffer buf = new StringBuffer();
	if (question < 0)
	    buf.append(url);
	else
	    buf.append(url.substring(0, question));
	buf.append(';');
	buf.append(Constants.SESSION_PARAMETER_NAME);
	buf.append('=');
	buf.append(id);
	if (question >= 0)
	    buf.append(url.substring(question));
	return (buf.toString());

    }


}



