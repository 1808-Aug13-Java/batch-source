/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/core/Attic/ContextInterceptor.java,v 1.11 2000/06/15 19:34:27 jon Exp $
 * $Revision: 1.11 $
 * $Date: 2000/06/15 19:34:27 $
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
import javax.servlet.Servlet;

/**
 * Notifications for all context events. 
 * 
 * Example: expand WAR, move files in the right directories ( Apache ?),
 * read web.xml or check for a serialized form for faster init, etc.
 *
 * @author costin@dnt.ro
 */
public interface ContextInterceptor {

    /** Called when the ContextManger is started. 
     */
    public void engineInit(ContextManager cm) throws TomcatException;
    

    /** Called before the ContextManager is stoped.
     *  You need to stop any threads and remove any resources.
     */
    public void engineShutdown(ContextManager cm) throws TomcatException;

    
    /** Called when a context is added to a CM. The context is probably not
     *  initialized yet, only path and docRoot are probably set.
     *
     *  If you need informations that are available in web.xml use contextInit()
     *  ( a WebXmlReader needs to be the first interceptor in the contextInit chain ).
     * 
     *  We do that to support ( eventualy ) a "lazy" init, where you have many contexts,
     *  most of them not in active use, and you'll init them at first request. ( for
     *  example an ISP with many users )
     *
     */
    public void addContext( ContextManager cm, Context ctx ) throws TomcatException;


    /** Notify when a context is initialized.
     *  The first interceptor in the chain for contextInit must read web.xml and set
     *  the context. When this method is called you can expect the context to be filled
     *  in with all the informations from web.xml.
     */
    public void contextInit(Context ctx) throws TomcatException;

    /** Reload notification - called whenever a reload is done.
	This can be used to serialize sessions, log the event,
	remove any resource that was class-loader dependent.
     */
    public void reload( Request req, Context ctx) throws TomcatException;


    /** Called when a context is stoped, before removeContext. You must free all resources.
     * XXX  - do we need this or removeContext is enough ?? ( will be removed from 3.1 if
     * nobody asks for it)
     */
    public void contextShutdown(Context ctx) throws TomcatException;
    

    /** Called when a context is removed from a CM. A context is removed either as
     *  a result of admin ( remove or update), to support "clean" servlet reloading
     *  or at shutdown.
     */
    public void removeContext( ContextManager cm, Context ctx ) throws TomcatException;


    /** A new location was added to the server. A location is defined as a set of
     *  URL patterns with common properties. All servlet mappings and security
     *  constraints are in this category - with a common handler and a common set
     *  of authorized roles.
     *
     *  An interceptor interested in mapping  must implement this method
     *  and construct it's internal representation. The mapper is _required_
     *  to find the Container associated with a request using the mapping
     *  rules defined in the Servlet API.
     *
     *  The interceptor must also take care of "merging" parent with child containers.
     *  It is possible that this method will be called several times for the same
     *  url pattern ( for example to define a handler and then security constraints),
     *  the interceptor needs to merge the 2 containers.
     * 
     *  XXX  define "merging" of containers 
     */
    public void addContainer( Container container ) throws TomcatException;

    /** A rule was removed, update the internal strucures. You can also clean up
     * and reload everything using Context.getContainers()
     */
    public void removeContainer( Container container ) throws TomcatException;

    
    /** Servlet Init notification.
     *  XXX do we need "pre/post" for init/destroy ? transactions? 
     */
    public void preServletInit( Context ctx, ServletWrapper sw ) throws TomcatException;
    
    public void postServletInit( Context ctx, ServletWrapper sw ) throws TomcatException;


    /** Servlet Destroy  notification
     */
    public void preServletDestroy( Context ctx, ServletWrapper sw ) throws TomcatException;
    
    public void postServletDestroy( Context ctx, ServletWrapper sw ) throws TomcatException;

}
