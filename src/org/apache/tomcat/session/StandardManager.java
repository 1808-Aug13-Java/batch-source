/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/session/Attic/StandardManager.java,v 1.11.2.4 2001/10/26 13:51:23 marcsaeg Exp $
 * $Revision: 1.11.2.4 $
 * $Date: 2001/10/26 13:51:23 $
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


package org.apache.tomcat.session;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.*;
import org.apache.tomcat.core.Request;

/**
 * Standard implementation of the <b>Manager</b> interface that provides
 * no session persistence or distributable capabilities, but does support
 * an optional, configurable, maximum number of active sessions allowed.
 * <p>
 * Lifecycle configuration of this component assumes an XML node
 * in the following format:
 * <code>
 *     &lt;Manager className="org.apache.tomcat.session.StandardManager"
 *              checkInterval="60" maxActiveSessions="-1"
 *              maxInactiveInterval="-1" />
 * </code>
 * where you can adjust the following parameters, with default values
 * in square brackets:
 * <ul>
 * <li><b>checkInterval</b> - The interval (in seconds) between background
 *     thread checks for expired sessions.  [60]
 * <li><b>maxActiveSessions</b> - The maximum number of sessions allowed to
 *     be active at once, or -1 for no limit.  [-1]
 * <li><b>maxInactiveInterval</b> - The default maximum number of seconds of
 *     inactivity before which the servlet container is allowed to time out
 *     a session, or -1 for no limit.  This value should be overridden from
 *     the default session timeout specified in the web application deployment
 *     descriptor, if any.  [-1]
 * </ul>
 *
 * @author Craig R. McClanahan
 * @author costin@eng.sun.com
 * @author <a href="mailto:jon@latchkey.com">Jon S. Stevens</a>
 * @author Shai Fultheim [shai@brm.com]
 * @version $Revision: 1.11.2.4 $ $Date: 2001/10/26 13:51:23 $
 */
public final class StandardManager implements Runnable  {
    // ----------------------------------------------------- Instance Variables
    /**
     * The distributable flag for Sessions created by this Manager.  If this
     * flag is set to <code>true</code>, any user attributes added to a
     * session controlled by this Manager must be Serializable.
     */
    protected boolean distributable;

    /**
     * The default maximum inactive interval for Sessions created by
     * this Manager.
     */
    protected int maxInactiveInterval = 60;

    /**
     * The set of previously recycled Sessions for this Manager.
     */
    protected Vector recycled = new Vector();

    /**
     * The set of currently active Sessions for this Manager, keyed by
     * session identifier.
     */
    protected Hashtable sessions = new Hashtable();

    /**
     * The interval (in seconds) between checks for expired sessions.
     */
    private int checkInterval = 60;

    /**
     * The maximum number of active Sessions allowed, or -1 for no limit.
     */
    protected int maxActiveSessions = -1;

    /**
     * The string manager for this package.
     */
    private static StringManager sm =
        StringManager.getManager("org.apache.tomcat.session");

    /**
     * The background thread.
     */
    private Thread thread = null;

    /**
     * The background thread completion semaphore.
     */
    private boolean threadDone = false;

    /**
     * Name to register for the background thread.
     */
    private String threadName = "StandardManager";

    // ------------------------------------------------------------- Constructor

    public StandardManager() {
    }

    // ------------------------------------------------------------- Properties
    /**
     * Return the distributable flag for the sessions supported by
     * this Manager.
     */
    public boolean getDistributable() {
	return (this.distributable);
    }

    /**
     * Set the distributable flag for the sessions supported by this
     * Manager.  If this flag is set, all user data objects added to
     * sessions associated with this manager must implement Serializable.
     *
     * @param distributable The new distributable flag
     */
    public void setDistributable(boolean distributable) {
	this.distributable = distributable;
    }

    /**
     * Return the default maximum inactive interval (in seconds)
     * for Sessions created by this Manager.
     */
    public int getMaxInactiveInterval() {
	return (this.maxInactiveInterval);
    }

    /**
     * Set the default maximum inactive interval (in seconds)
     * for Sessions created by this Manager.
     *
     * @param interval The new default value
     */
    public void setMaxInactiveInterval(int interval) {
	this.maxInactiveInterval = interval;
    }

    /**
     * Used by context to configure the session manager's inactivity timeout.
     *
     * The SessionManager may have some default session time out, the
     * Context on the other hand has it's timeout set by the deployment
     * descriptor (web.xml). This method lets the Context conforgure the
     * session manager according to this value.
     *
     * @param minutes The session inactivity timeout in minutes.  A negative time indicates the session should never timeout.
     */
    public void setSessionTimeOut(int minutes) {
        // The manager works with seconds...
        setMaxInactiveInterval(minutes * 60);
    }

    /**
     * Return the check interval (in seconds) for this Manager.
     */
    public int getCheckInterval() {
	return (this.checkInterval);
    }

    /**
     * Set the check interval (in seconds) for this Manager.
     *
     * @param checkInterval The new check interval
     */
    public void setCheckInterval(int checkInterval) {
	this.checkInterval = checkInterval;
    }

    /**
     * Return the maximum number of active Sessions allowed, or -1 for
     * no limit.
     */
    public int getMaxActiveSessions() {
	return (this.maxActiveSessions);
    }

    /**
     * Set the maximum number of actives Sessions allowed, or -1 for
     * no limit.
     *
     * @param max The new maximum number of sessions
     */
    public void setMaxActiveSessions(int max) {
	this.maxActiveSessions = max;
    }

    // --------------------------------------------------------- Public Methods

    /**
     * Mark the specified session's last accessed time.  This should be
     * called for each request by a RequestInterceptor.
     *
     * @param session The session to be marked
     */
    public void access( HttpSession session ) {
	((StandardSession) session).access();
    }

    /** Notify that the servlet that acccessed the session is done
     */
    public void release( HttpSession session ) {
    }

    /**
     * Return the active Session, associated with this Manager, with the
     * specified session id (if any); otherwise return <code>null</code>.
     *
     * @param id The session id for the session to be returned
     *
     * @exception ClassNotFoundException if a deserialization error occurs
     *  while processing this request
     * @exception IllegalStateException if a new session cannot be
     *  instantiated for any reason
     * @exception IOException if an input/output error occurs while
     *  processing this request
     */
    public HttpSession findSession(String id) {
	if (id == null)
	    return (null);
	HttpSession s=((HttpSession) sessions.get(id));
	if( s==null ) return s;
	access( s );
	return s;
    }

    /**
     * Return the set of active Sessions associated with this Manager.
     * If this Manager has no active Sessions, a zero-length array is returned.
     */
    public HttpSession[] findSessions() {
	synchronized (sessions) {
	    Vector keys = new Vector();
	    Enumeration ids = sessions.keys();
	    while (ids.hasMoreElements()) {
		String id = (String) ids.nextElement();
		keys.addElement(id);
	    }
	    HttpSession results[] = new HttpSession[keys.size()];
	    for (int i = 0; i < results.length; i++) {
		String key = (String) keys.elementAt(i);
		results[i] = (HttpSession) sessions.get(key);
	    }
	    return (results);
	}
    }

	/**
		This method will return a Hashtable of HttpSession
		objects.
	*/
	public Hashtable getSessions() {
		return this.sessions;
	}

        /**
                This method will replace the sessions Hashtable with
                that given.
        */
        public void setSessions(Hashtable sessions) {
            
                // make sure all the sessions know they belong to us
                Enumeration e = sessions.keys();
                while (e.hasMoreElements())
                {
                    StandardSession sess 
                        = (StandardSession)sessions.get(e.nextElement());
                    sess.setManager(this);
                }
            
                this.sessions = sessions;
        }
	
    /**
     * Construct and return a new session object, based on the default
     * settings specified by this Manager's properties.  The session
     * id will be assigned by this method, and available via the getId()
     * method of the returned session.  If a new session cannot be created
     * for any reason, return <code>null</code>.
     *
     * @exception IllegalStateException if a new session cannot be
     *  instantiated for any reason
     */
    public HttpSession getNewSession(String jsIdent) {

	if ((maxActiveSessions >= 0) &&
	  (sessions.size() >= maxActiveSessions))
	    throw new IllegalStateException
		(sm.getString("standardManager.createSession.ise"));

	// Recycle or create a Session instance
	StandardSession session = null;
	synchronized (recycled) {
	    int size = recycled.size();
	    if (size > 0) {
		session = (StandardSession) recycled.elementAt(size - 1);
		recycled.removeElementAt(size - 1);
	    }
	}
	if (session == null)
	    session = new StandardSession(this);

	// Initialize the properties of the new session and return it
	session.setNew(true);
	session.setValid(true);
	session.setCreationTime(System.currentTimeMillis());
	session.setMaxInactiveInterval(this.maxInactiveInterval);
	session.setId(SessionUtil.generateSessionId(jsIdent));

	return (session);
    }

    public void handleReload(Request req, ClassLoader newLoader) {
	SessionSerializer.doSerialization(req, newLoader, this);
    }

    /**
     * Prepare for the beginning of active use of the public methods of this
     * component.  This method should be called after <code>configure()</code>,
     * and before any of the public methods of the component are utilized.
     *
     * @exception IllegalStateException if this component has not yet been
     *  configured (if required for this component)
     * @exception IllegalStateException if this component has already been
     *  started
     */
    public void start() {

	// Cause the context's PRNG to be initialized
	 String sDummy = SessionUtil.generateSessionId(null);

	// Start the background reaper thread
	threadStart();

    }


    /**
     * Gracefully terminate the active use of the public methods of this
     * component.  This method should be the last one called on a given
     * instance of this component.
     *
     * @exception IllegalStateException if this component has not been started
     * @exception IllegalStateException if this component has already
     *  been stopped
     */
    public void stop() {
	// Stop the background reaper thread
	threadStop();

	// Expire all active sessions
	HttpSession sessions[] = findSessions();
	for (int i = 0; i < sessions.length; i++) {
	    StandardSession session = (StandardSession) sessions[i];
	    if (!session.isValid())
		continue;
	    session.expire();
	}

    }
    // -------------------------------------------------------- Package Methods

    /**
     * Add this Session to the set of active Sessions for this Manager.
     *
     * @param session Session to be added
     */
    void add(StandardSession session) {
	sessions.put(session.getId(), session);
    }

    /**
     * Add this Session to the recycle collection for this Manager.
     *
     * @param session Session to be recycled
     */
    void recycle(StandardSession session) {
	recycled.addElement(session);
    }

    /**
     * Remove this Session from the active Sessions for this Manager.
     *
     * @param session Session to be removed
     */
    void remove(StandardSession session) {
	sessions.remove(session.getId());
    }


    // -------------------------------------------------------- Private Methods


    /**
     * Invalidate all sessions that have expired.
     */
    private void processExpires() {

	long timeNow = System.currentTimeMillis();
	HttpSession sessions[] = findSessions();

	for (int i = 0; i < sessions.length; i++) {
	    StandardSession session = (StandardSession) sessions[i];
	    if (!session.isValid())
		continue;
	    int maxInactiveInterval = session.getMaxInactiveInterval();
	    if (maxInactiveInterval < 0)
		continue;
	    int timeIdle = // Truncate, do not round up
		(int) ((timeNow - session.getLatestAccessedTime()) / 1000L);
	    if (timeIdle >= maxInactiveInterval)
		session.expire();
	}
    }


    /**
     * Sleep for the duration specified by the <code>checkInterval</code>
     * property.
     */
    private void threadSleep() {

	try {
	    Thread.sleep(checkInterval * 1000L);
	} catch (InterruptedException e) {
	    ;
	}

    }


    /**
     * Start the background thread that will periodically check for
     * session timeouts.
     */
    private void threadStart() {

	if (thread != null)
	    return;

	threadDone = false;
	thread = new Thread(this, threadName);
	thread.setDaemon(true);
	thread.start();

    }


    /**
     * Stop the background thread that is periodically checking for
     * session timeouts.
     */
    private void threadStop() {

	if (thread == null)
	    return;

	threadDone = true;
	thread.interrupt();
	try {
	    thread.join();
	} catch (InterruptedException e) {
	    ;
	}

	thread = null;

    }


    // ------------------------------------------------------ Background Thread


    /**
     * The background thread that checks for session timeouts and shutdown.
     */
    public void run() {

	// Loop until the termination semaphore is set
	while (!threadDone) {
	    threadSleep();
	    processExpires();
	}

    }


}
