/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/session/Attic/StandardSession.java,v 1.15.2.1 2001/03/17 00:08:42 marcsaeg Exp $
 * $Revision: 1.15.2.1 $
 * $Date: 2001/03/17 00:08:42 $
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionContext;
import org.apache.tomcat.util.StringManager;


/**
 * Standard implementation of the <b>Session</b> interface.  This object is
 * serializable, so that it can be stored in persistent storage or transferred
 * to a different JVM for distributable session support.
 * <p>
 * <b>IMPLEMENTATION NOTE</b>:  An instance of this class represents both the
 * internal (Session) and application level (HttpSession) view of the session.
 * However, because the class itself is not declared public, Java logic outside
 * of the <code>org.apache.tomcat.session</code> package cannot cast an
 * HttpSession view of this instance back to a Session view.
 * <p>
 * <b>IMPLEMENTATION NOTE</b>: If you add fields to this class, you must
 * make sure that you carry them over in the read/writeObject methods so
 * that this class is properly serialized.
 *
 * @author Craig R. McClanahan
 * @author <a href="mailto:jon@latchkey.com">Jon S. Stevens</a>
 * @version $Revision: 1.15.2.1 $ $Date: 2001/03/17 00:08:42 $
 */

final class StandardSession
    implements HttpSession, Serializable {


    // ----------------------------------------------------------- Constructors


    /**
     * Construct a new Session associated with the specified Manager.
     *
     * @param manager The manager with which this Session is associated
     */
    public StandardSession(StandardManager manager) {

	super();
	this.manager = manager;

    }


    // ----------------------------------------------------- Instance Variables


    /**
     * The collection of user data attributes associated with this Session.
     */
    private Hashtable attributes = new Hashtable();


    /**
     * The time this session was created, in milliseconds since midnight,
     * January 1, 1970 GMT.
     */
    private long creationTime = 0L;


    /**
     * The session identifier of this Session.
     */
    private String id = null;


    /**
     * Descriptive information describing this Session implementation.
     */
    private static final String info = "StandardSession/1.0";


    /**
     * The last accessed time for this Session.
     */
    private long lastAccessedTime = creationTime;


    /**
     * The Manager with which this Session is associated.
     */
    private StandardManager manager = null;


    /**
     * The maximum time interval, in seconds, between client requests before
     * the servlet container may invalidate this session.  A negative time
     * indicates that the session should never time out.
     */
    private int maxInactiveInterval = -1;


    /**
     * Flag indicating whether this session is new or not.
     */
    private boolean isNew = true;


    /**
     * Flag indicating whether this session is valid or not.
     */
    private boolean isValid = false;


    /**
     * The string manager for this package.
     */
    private static StringManager sm =
        StringManager.getManager("org.apache.tomcat.session");


    /**
     * The HTTP session context associated with this session.
     */
    private static HttpSessionContext sessionContext = null;


    /**
     * The current accessed time for this session.
     */
    private long thisAccessedTime = creationTime;


    // ----------------------------------------------------- Session Properties


    /**
     * Set the creation time for this session.  This method is called by the
     * Manager when an existing Session instance is reused.
     *
     * @param time The new creation time
     */
    public void setCreationTime(long time) {

	this.creationTime = time;
	this.lastAccessedTime = time;
	this.thisAccessedTime = time;

    }


    /**
     * Return the session identifier for this session.
     */
    public String getId() {

	return (this.id);

    }


    /**
     * Set the session identifier for this session.
     *
     * @param id The new session identifier
     */
    public void setId(String id) {

	if ((this.id != null) && (manager != null))
	    manager.remove(this);

	this.id = id;

	if ((manager != null) )
	    manager.add(this);

    }


    /**
     * Return descriptive information about this Session implementation and
     * the corresponding version number, in the format
     * <code>&lt;description&gt;/&lt;version&gt;</code>.
     */
    public String getInfo() {

	return (this.info);

    }


    /**
     * Return the last time the client sent a request associated with this
     * session, as the number of milliseconds since midnight, January 1, 1970
     * GMT.  Actions that your application takes, such as getting or setting
     * a value associated with the session, do not affect the access time.
     */
    public long getLastAccessedTime() {

	return (this.lastAccessedTime);

    }


    /**
     *
     */
    public long getLatestAccessedTime()
    {
        return this.thisAccessedTime;
    }

    /**
     * Return the Manager within which this Session is valid.
     */
    public StandardManager getManager() {

	return (this.manager);

    }


    /**
     * Set the Manager within which this Session is valid.
     *
     * @param manager The new Manager
     */
    public void setManager(StandardManager manager) {

	this.manager = manager;

    }


    /**
     * Return the maximum time interval, in seconds, between client requests
     * before the servlet container will invalidate the session.  A negative
     * time indicates that the session should never time out.
     *
     * @exception IllegalStateException if this method is called on
     *  an invalidated session
     */
    public int getMaxInactiveInterval() {

	if (!isValid())
	    throw new IllegalStateException
		(sm.getString("standardSession.getMaxInactiveInterval.ise"));

	return (this.maxInactiveInterval);

    }


    /**
     * Set the maximum time interval, in seconds, between client requests
     * before the servlet container will invalidate the session.  A negative
     * time indicates that the session should never time out.
     *
     * @param interval The new maximum interval
     */
    public void setMaxInactiveInterval(int interval) {

	this.maxInactiveInterval = interval;

    }


    /**
     * Return the <code>HttpSession</code> for which this object
     * is the facade.
     */
    public HttpSession getSession() {

	return ((HttpSession) this);

    }


    // ------------------------------------------------- Session Public Methods


    /**
     * Update the accessed time information for this session.  This method
     * should be called by the context when a request comes in for a particular
     * session, even if the application does not reference it.
     */
    public void access() {

	this.lastAccessedTime = this.thisAccessedTime;
	this.thisAccessedTime = System.currentTimeMillis();
	this.isNew=false;
    }


    /**
     * Perform the internal processing required to invalidate this session,
     * without triggering an exception if the session has already expired.
     */
    public void expire() {

	// Remove this session from our manager's active sessions
	if (manager != null) 
	    manager.remove(this);

	// Unbind any objects associated with this session
	Vector results = new Vector();
	Enumeration attrs = getAttributeNames();
	while (attrs.hasMoreElements()) {
	    String attr = (String) attrs.nextElement();
	    results.addElement(attr);
	}
	Enumeration names = results.elements();
	while (names.hasMoreElements()) {
	    String name = (String) names.nextElement();
	    removeAttribute(name);
	}

	// Mark this session as invalid
	setValid(false);

    }


    /**
     * Release all object references, and initialize instance variables, in
     * preparation for reuse of this object.
     */
    public void recycle() {

	// Reset the instance variables associated with this Session
	attributes.clear();
	creationTime = 0L;
	id = null;
	lastAccessedTime = 0L;
	manager = null;
	maxInactiveInterval = -1;
	isNew = true;
	isValid = false;

	// Tell our Manager that this Session has been recycled
	if (manager != null)
	    manager.recycle(this);

    }


    // ------------------------------------------------ Session Package Methods


    /**
     * Return the <code>isValid</code> flag for this session.
     */
    boolean isValid() {

	return (this.isValid);

    }


    /**
     * Set the <code>isNew</code> flag for this session.
     *
     * @param isNew The new value for the <code>isNew</code> flag
     */
    void setNew(boolean isNew) {

	this.isNew = isNew;

    }


    /**
     * Set the <code>isValid</code> flag for this session.
     *
     * @param isValid The new value for the <code>isValid</code> flag
     */
    void setValid(boolean isValid) {

	this.isValid = isValid;
    }


    // ------------------------------------------------- HttpSession Properties


    /**
     * Return the time when this session was created, in milliseconds since
     * midnight, January 1, 1970 GMT.
     *
     * @exception IllegalStateException if this method is called on an
     *  invalidated session
     */
    public long getCreationTime() {

	if (!isValid())
	    throw new IllegalStateException
		(sm.getString("standardSession.getCreationTime.ise"));

	return (this.creationTime);

    }


    /**
     * Return the session context with which this session is associated.
     *
     * @deprecated As of Version 2.1, this method is deprecated and has no
     *  replacement.  It will be removed in a future version of the
     *  Java Servlet API.
     */
    public HttpSessionContext getSessionContext() {

	if (sessionContext == null)
	    sessionContext = new SessionContextImpl();
	return (sessionContext);

    }


    // ----------------------------------------------HttpSession Public Methods


    /**
     * Return the object bound with the specified name in this session, or
     * <code>null</code> if no object is bound with that name.
     *
     * @param name Name of the attribute to be returned
     *
     * @exception IllegalStateException if this method is called on an
     *  invalidated session
     */
    public Object getAttribute(String name) {

	if (!isValid())
	    throw new IllegalStateException
		(sm.getString("standardSession.getAttribute.ise"));

	return (attributes.get(name));

    }


    /**
     * Return an <code>Enumeration</code> of <code>String</code> objects
     * containing the names of the objects bound to this session.
     *
     * @exception IllegalStateException if this method is called on an
     *  invalidated session
     */
    public Enumeration getAttributeNames() {

	if (!isValid())
	    throw new IllegalStateException
		(sm.getString("standardSession.getAttributeNames.ise"));

	return (attributes.keys());

    }


    /**
     * Return the object bound with the specified name in this session, or
     * <code>null</code> if no object is bound with that name.
     *
     * @param name Name of the value to be returned
     *
     * @exception IllegalStateException if this method is called on an
     *  invalidated session
     *
     * @deprecated As of Version 2.2, this method is replaced by
     *  <code>getAttribute()</code>
     */
    public Object getValue(String name) {

	return (getAttribute(name));

    }


    /**
     * Return the set of names of objects bound to this session.  If there
     * are no such objects, a zero-length array is returned.
     *
     * @exception IllegalStateException if this method is called on an
     *  invalidated session
     *
     * @deprecated As of Version 2.2, this method is replaced by
     *  <code>getAttributeNames()</code>
     */
    public String[] getValueNames() {

	if (!isValid())
	    throw new IllegalStateException
		(sm.getString("standardSession.getValueNames.ise"));

	Vector results = new Vector();
	Enumeration attrs = getAttributeNames();
	while (attrs.hasMoreElements()) {
	    String attr = (String) attrs.nextElement();
	    results.addElement(attr);
	}
	String names[] = new String[results.size()];
	for (int i = 0; i < names.length; i++)
	    names[i] = (String) results.elementAt(i);
	return (names);

    }


    /**
     * Invalidates this session and unbinds any objects bound to it.
     *
     * @exception IllegalStateException if this method is called on
     *  an invalidated session
     */
    public void invalidate() {

	if (!isValid())
	    throw new IllegalStateException
		(sm.getString("standardSession.invalidate.ise"));

	// Cause this session to expire
	expire();

    }


    /**
     * Return <code>true</code> if the client does not yet know about the
     * session, or if the client chooses not to join the session.  For
     * example, if the server used only cookie-based sessions, and the client
     * has disabled the use of cookies, then a session would be new on each
     * request.
     *
     * @exception IllegalStateException if this method is called on an
     *  invalidated session
     */
    public boolean isNew() {

	if (!isValid())
	    throw new IllegalStateException
		(sm.getString("standardSession.isNew.ise"));

	return (this.isNew);

    }


    /**
     * Bind an object to this session, using the specified name.  If an object
     * of the same name is already bound to this session, the object is
     * replaced.
     * <p>
     * After this method executes, and if the object implements
     * <code>HttpSessionBindingListener</code>, the container calls
     * <code>valueBound()</code> on the object.
     *
     * @param name Name to which the object is bound, cannot be null
     * @param value Object to be bound, cannot be null
     *
     * @exception IllegalStateException if this method is called on an
     *  invalidated session
     *
     * @deprecated As of Version 2.2, this method is replaced by
     *  <code>setAttribute()</code>
     */
    public void putValue(String name, Object value) {

	setAttribute(name, value);

    }


    /**
     * Remove the object bound with the specified name from this session.  If
     * the session does not have an object bound with this name, this method
     * does nothing.
     * <p>
     * After this method executes, and if the object implements
     * <code>HttpSessionBindingListener</code>, the container calls
     * <code>valueUnbound()</code> on the object.
     *
     * @param name Name of the object to remove from this session.
     *
     * @exception IllegalStateException if this method is called on an
     *  invalidated session
     */
    public void removeAttribute(String name) {

	if (!isValid())
	    throw new IllegalStateException
		(sm.getString("standardSession.removeAttribute.ise"));

	synchronized (attributes) {
	    Object object = attributes.get(name);
	    if (object == null)
		return;
	    attributes.remove(name);
	    //	    System.out.println( "Removing attribute " + name );
	    if (object instanceof HttpSessionBindingListener) {
		((HttpSessionBindingListener) object).valueUnbound
		    (new HttpSessionBindingEvent((HttpSession) this, name));
	    }
	}

    }


    /**
     * Remove the object bound with the specified name from this session.  If
     * the session does not have an object bound with this name, this method
     * does nothing.
     * <p>
     * After this method executes, and if the object implements
     * <code>HttpSessionBindingListener</code>, the container calls
     * <code>valueUnbound()</code> on the object.
     *
     * @param name Name of the object to remove from this session.
     *
     * @exception IllegalStateException if this method is called on an
     *  invalidated session
     *
     * @deprecated As of Version 2.2, this method is replaced by
     *  <code>removeAttribute()</code>
     */
    public void removeValue(String name) {

	removeAttribute(name);

    }


    /**
     * Bind an object to this session, using the specified name.  If an object
     * of the same name is already bound to this session, the object is
     * replaced.
     * <p>
     * After this method executes, and if the object implements
     * <code>HttpSessionBindingListener</code>, the container calls
     * <code>valueBound()</code> on the object.
     *
     * @param name Name to which the object is bound, cannot be null
     * @param value Object to be bound, cannot be null
     *
     * @exception IllegalArgumentException if an attempt is made to add a
     *  non-serializable object in an environment marked distributable.
     * @exception IllegalStateException if this method is called on an
     *  invalidated session
     */
    public void setAttribute(String name, Object value) {

	if (!isValid())
	    throw new IllegalStateException
		(sm.getString("standardSession.setAttribute.ise"));

	if ((manager != null) && manager.getDistributable() &&
	  !(value instanceof Serializable))
	    throw new IllegalArgumentException
		(sm.getString("standardSession.setAttribute.iae"));

	synchronized (attributes) {
	    removeAttribute(name);
	    attributes.put(name, value);
	    if (value instanceof HttpSessionBindingListener)
		((HttpSessionBindingListener) value).valueBound
		    (new HttpSessionBindingEvent((HttpSession) this, name));
	}

    }


    // -------------------------------------------- HttpSession Private Methods


    /**
     * Read a serialized version of this session object from the specified
     * object input stream.
     * <p>
     * <b>IMPLEMENTATION NOTE</b>:  The reference to the owning Manager
     * is not restored by this method, and must be set explicitly.
     *
     * @param stream The input stream to read from
     *
     * @exception ClassNotFoundException if an unknown class is specified
     * @exception IOException if an input/output error occurs
     */
    private void readObject(ObjectInputStream stream)
		throws ClassNotFoundException, IOException {

		// Deserialize the scalar instance variables (except Manager)
		creationTime = ((Long) stream.readObject()).longValue();
		id = (String) stream.readObject();
		lastAccessedTime = ((Long) stream.readObject()).longValue();
                thisAccessedTime = ((Long) stream.readObject()).longValue();
		maxInactiveInterval = ((Integer) stream.readObject()).intValue();
		isNew = ((Boolean) stream.readObject()).booleanValue();
		isValid = ((Boolean) stream.readObject()).booleanValue();

		attributes = (Hashtable) stream.readObject();
    }


    /**
     * Write a serialized version of this session object to the specified
     * object output stream.
     * <p>
     * <b>IMPLEMENTATION NOTE</b>:  The owning Manager will not be stored
     * in the serialized representation of this Session.  After calling
     * <code>readObject()</code>, you must set the associated Manager
     * explicitly.
     * <p>
     * <b>IMPLEMENTATION NOTE</b>:  Any attribute that is not Serializable
     * will be silently ignored.  If you do not want any such attributes,
     * be sure the <code>distributable</code> property of our associated
     * Manager is set to <code>true</code>. 
	 * <p>
     * <b>IMPLEMENTATION NOTE</b>: If we can't serialize the object stored in 
     * the session, then check to see if it implements 
     * HttpSessionBindingListener and then call its 
     * valueUnbound method, allowing it to save its state
     * correctly instead of just being lost into the etherworld
     *
     * @param stream The output stream to write to
     *
     * @exception IOException if an input/output error occurs
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {

		// Write the scalar instance variables (except Manager)
		stream.writeObject(new Long(creationTime));
		stream.writeObject(id);
		stream.writeObject(new Long(lastAccessedTime));
                stream.writeObject(new Long(thisAccessedTime));
		stream.writeObject(new Integer(maxInactiveInterval));
		stream.writeObject(new Boolean(isNew));
		stream.writeObject(new Boolean(isValid));

        if (attributes.size() > 0) {
			// Accumulate the names of serializable attributes
			Hashtable results = new Hashtable(attributes.size());
        
			for (Enumeration e = attributes.keys(); e.hasMoreElements() ; ) {
				String key = (String) e.nextElement();
				Object value = attributes.get(key);
				if (value instanceof Serializable) {
					results.put(key, value);
				}
				// if we can't serialize the object stored in 
				// the session, then check to see if it implements 
				// HttpSessionBindingListener and then call its 
				// valueUnbound method, allowing it to save its state
				// correctly instead of just being lost into the etherworld
				else if (value instanceof HttpSessionBindingListener ) {
					try {
						((HttpSessionBindingListener)value)
						.valueUnbound(new HttpSessionBindingEvent(this, key));
					} catch (Exception f) {
						// ignored
					}
				}
			}
			stream.writeObject(results);
		} else {
			stream.writeObject(new Hashtable());
		}
	}
}

/**
 * 
 * @author duncan@eng.sun.com
 */

 class SessionContextImpl implements HttpSessionContext {

    /**
     *
     * @deprecated
     */
    
    public HttpSession getSession(String sessionId) {
        return null;
    }

    /**
     *
     * @deprecated
     */

    public Enumeration getIds() {
        // cheap hack to get an empty enum
        Vector v = new Vector();

        return v.elements();
    }
}
