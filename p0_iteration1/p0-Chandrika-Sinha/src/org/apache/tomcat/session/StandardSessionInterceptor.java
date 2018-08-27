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


package org.apache.tomcat.session;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.*;
import org.apache.tomcat.core.*;

/**
 * This is the adapter between tomcat and a StandardManager.
 * A session manager should not depend on tomcat internals - so you can
 * use it in other engines and back. All you need to do is
 * create an adapter ( tomcat Interceptor).
 *
 * You can even have multiple session managers per context - the first that
 * recognize the "requestedSessionId" will create it's own HttpSession object.
 * By using standard tomcat interceptor mechanisms you can plug in one or
 * many session managers per context or context manager ( or even per
 * URL - but that's not standard API feature ).
 *
 * It must be inserted after SessionInterceptor, which does common
 * session stuff ( cookie, rewrite, etc)
 *
 * @author costin@eng.sun.com
 * @author hans@gefionsoftware.com (fixed it so that URL session ID is used)
 * @author Shai Fultheim [shai@brm.com]
 */
public final class StandardSessionInterceptor  extends BaseInterceptor {
    int manager_note;
    static final char SESSIONID_ROUTE_SEP = '.';

    public StandardSessionInterceptor() {
    }

    // -------------------- Internal methods --------------------
    private StandardManager getManager( Context ctx ) {
	return (StandardManager)ctx.getContainer().getNote(manager_note);
    }

    private void setManager( Context ctx, StandardManager sm ) {
	ctx.getContainer().setNote( manager_note, sm );
    }

    // -------------------- Tomcat request events --------------------
    public void engineInit( ContextManager cm ) throws TomcatException {
	// set-up a per/container note for StandardManager
	manager_note = cm.getNoteId( ContextManager.CONTAINER_NOTE, "tomcat.standardManager");
    }

    /**
     *  StandardManager will set the HttpSession if one is found.
     *
     */
    public int requestMap(Request request ) {
	String sessionId = null;
	HttpSession sess = null;
	Context ctx=request.getContext();
	StandardManager sM = getManager( ctx );
	if( ctx==null ) {
	    log( "Configuration error in StandardSessionInterceptor - no context " + request );
	}

	// Added by Hans:
	// First check if we have a valid session ID from the URL, set by the SessionInterceptor,
	// and if so, set it as the request session. If we have also received a valid session ID
	// as a cookie, the next section of code will reset the session to the one matching the
	// ID found in the cookie.
	String requestedSessionID = request.getRequestedSessionId();
	if (requestedSessionID != null) {
	    if (debug > 0) 
		log("Found URL session ID: " + requestedSessionID);
	    sess = sM.findSession(requestedSessionID);
	    if (sess != null) {
		if (debug > 0) log("The URL session ID is valid");
		request.setSession(sess);
	    }
	}

	// PF, loop across all cookies named JSESSIONID checking to see if any of them are valid.
	// There should in most cases be a maximum of 2, and normally there will only be one. The
	// first valid session cookie is set as the session ID in the request.
	int count=request.getCookieCount();
	for( int i=0; i<count; i++ ) {
		Cookie cookie = request.getCookie(i);

		if (cookie.getName().equals("JSESSIONID")) {
			sessionId = cookie.getValue();
                        if (debug > 0) log("Found session id cookie " + sessionId);
                        request.setRequestedSessionId( sessionId );
                        request.setSessionIdSource( Request.SESSIONID_FROM_COOKIE );
                        sess = sM.findSession(sessionId);
                        if (sess != null)
                            request.setSession(sess);
                        break;
                }

	}

	return 0;
    }

    public void reload( Request req, Context ctx ) {
	ClassLoader newLoader = ctx.getServletLoader().getClassLoader();
	StandardManager sM = getManager( ctx );
	sM.handleReload(req, newLoader);
    }

    public int newSessionRequest( Request request, Response response) {
	Context ctx=request.getContext();
	if( ctx==null ) return 0;

	StandardManager sM = getManager( ctx );

	if( request.getSession( false ) != null )
	    return 0; // somebody already set the session
	HttpSession newS=sM.getNewSession(request.getJvmRoute());
	request.setSession( newS );
	return 0;
    }

    /** Called after request - we need to release the session object.
     *  This is used to prevent removal of session objects during execution,
     *	and may be used by interceptors that want to limit or count the
     *	sessions.
     */
    public int postService(  Request rrequest, Response response ) {
	Context ctx=rrequest.getContext();
	if( ctx==null ) return 0;

	StandardManager sm= getManager( ctx );
	HttpSession sess=rrequest.getSession(false);
	if( sess == null ) return 0;

	sm.release( sess );
	return 0;
    }



    //--------------------  Tomcat context events --------------------

    /** Init session management stuff for this context.
     */
    public void contextInit(Context ctx) throws TomcatException {
	// Defaults !!
	StandardManager sm= getManager( ctx );

	if( sm == null ) {
	    sm=new StandardManager();
	    setManager(ctx, sm);
	}

	// init is called after all context properties are set.
	sm.setSessionTimeOut( ctx.getSessionTimeOut() );
	sm.setDistributable( ctx.isDistributable() );

	try {
	    sm.start();
	} catch(IllegalStateException ex ) {
	    throw new TomcatException( ex );
	}
    }

    /** Notification of context shutdown.
     *  We should clean up any resources that are used by our
     *  session management code.
     */
    public void contextShutdown( Context ctx )
	throws TomcatException
    {
	if( ctx.getDebug() > 0 ) ctx.log("Removing sessions from " + ctx );
	StandardManager sm=getManager(ctx);
	try {
	    if( sm != null )
		sm.stop();
	} catch(IllegalStateException ex ) {
	    throw new TomcatException( ex );
	}
    }

}
