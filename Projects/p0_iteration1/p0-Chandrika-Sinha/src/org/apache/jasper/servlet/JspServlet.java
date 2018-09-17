/*
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
 */ 

package org.apache.jasper.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.HttpJspPage;
import javax.servlet.jsp.JspFactory;

import java.util.Hashtable;
import java.util.Enumeration;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import org.apache.jasper.JasperException;
import org.apache.jasper.Constants;
import org.apache.jasper.Options;
import org.apache.jasper.EmbededServletOptions;
import org.apache.jasper.JspCompilationContext;
import org.apache.jasper.JspEngineContext;
import org.apache.jasper.compiler.Mangler;
import org.apache.jasper.runtime.*;

import org.apache.jasper.compiler.Compiler;

import org.apache.tomcat.logging.Logger;

/**
 * The JSP engine (a.k.a Jasper)! 
 *
 * @author Anil K. Vijendran
 * @author Harish Prabandham
 * @author Marc A. Saegesser
 */
public class JspServlet extends HttpServlet {

    /**
     * Adds reference counting to the JSP implementation servlet.  This
     * is required to handle the case where a JSP implementation servlet
     * is executing requests on several threads when a new implementation
     * arrives (the JSP source was updated).  We need to wait until all
     * the requests complete before calling the implementation servlet's
     * destroy() method.
     */
    class JspCountedServlet extends HttpServlet
    {
        private Servlet servlet = null;
        private int threadCount = 0;
        private boolean destroyed = false;

        public JspCountedServlet(Servlet servlet)
        {
            this.servlet = servlet;
        }

        public void init(ServletConfig config) throws ServletException, JasperException
        {
            try{
                servlet.init(config);
            }catch(NullPointerException e){
                throw new JasperException(e);
            }
        }

        public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, JasperException
        {
            try{
                incrementCount();
                servlet.service(req, res);
            }catch(NullPointerException e){
                throw new JasperException(e);
            }finally{
                decrementCount();
            }
        }

        /*
         * Flags this servlet for destrction once all active requests have completed.
         * After calling this method it is invalid to call service().
         */
        public void destroy()
        {
            destroyed = true;
            if(getCount() == 0)
                doDestroy();
        }

        private void doDestroy()
        {
            try{
                servlet.destroy();
                servlet = null;
            }catch(NullPointerException e){
            }
        }

        private synchronized void incrementCount()
        {
            threadCount++;
        }

        private synchronized void decrementCount()
        {
            if(threadCount <= 0){
                Constants.message("jsp.error.badcount", Logger.ERROR);
                return;
            }

            --threadCount;
            if(threadCount == 0 && destroyed)
                doDestroy();
        }

        private synchronized int getCount()
        {
            return threadCount;
        }
    }

    class JspServletWrapper {
        JspCountedServlet theServlet;
        String jspUri;
        boolean isErrorPage;
        Class servletClass;

        JspServletWrapper(String jspUri, boolean isErrorPage) {
            this.jspUri = jspUri;
            this.isErrorPage = isErrorPage;
            this.theServlet = null;
        }

        public synchronized void instantiateServlet(Class servletClass) throws JasperException, ServletException
        {
            try {
                this.servletClass = servletClass;

                // If we're replacing an existing JSP Implementation class, then
                // schedule it for destruction
                if(theServlet != null)
                    theServlet.destroy();

                // Create an instance of the JSP implementation class
                Servlet servlet = (Servlet) servletClass.newInstance();
                // Set the class loader
                if(servlet instanceof HttpJspBase) {
                    ((HttpJspBase)servlet).setClassLoader(JspServlet.this.parentClassLoader);
                }

                // Wrap this servlet in a counted servlet
                theServlet = new JspCountedServlet(servlet);

                // Call the JSP Implementation servlet's init() method.  This
                // will cause the page's jspInit() method to be invoked if one exists.
                theServlet.init(JspServlet.this.config);

            } catch(Exception ex) {
                throw new JasperException(ex);
            }

        }

        public synchronized Servlet getServlet()
        {
            return theServlet;
        }

        public synchronized boolean isInstantiated()
        {
            return theServlet != null;
        }

        private void loadIfNecessary(HttpServletRequest req, HttpServletResponse res) 
        throws JasperException, ServletException, FileNotFoundException 
        {
            // First try context attribute; if that fails then use the 
            // classpath init parameter. 

            // Should I try to concatenate them if both are non-null?

            String cp = (String) context.getAttribute(Constants.SERVLET_CLASSPATH);

            String accordingto;

            if(cp == null || cp.equals("")) {
                accordingto = "according to the init parameter";
                cp = options.getClassPath();
            } else
                accordingto = "according to the Servlet Engine";

            Constants.message("jsp.message.cp_is", 
                              new Object[] { 
                                  accordingto,
                                  cp == null ? "" : cp
                              }, 
                              Logger.INFORMATION);

            loadJSP(jspUri, cp, isErrorPage, req, res);
        }

        public void service(HttpServletRequest request, 
                            HttpServletResponse response,
                            boolean precompile)
        throws ServletException, IOException, FileNotFoundException 
        {
            Servlet servlet = null;
            try {
                loadIfNecessary(request, response);
                servlet = getServlet();

                // If a page is to only to be precompiled return.
                if(precompile)
                    return;

                if(servlet instanceof SingleThreadModel) {
                    // sync on the wrapper so that the freshness
                    // of the page is determined right before servicing
                    synchronized (this) {
                        servlet.service(request, response);
                    }
                } else {
                    servlet.service(request, response);
                }

            } catch(FileNotFoundException ex) {
                try {
                    if(insecure_TMI) {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, 
                                           Constants.getString
                                           ("jsp.error.file.not.found.TMI", 
                                            new Object[] {
                                                ex.getMessage()
                                            }));
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, 
                                           Constants.getString
                                           ("jsp.error.file.not.found", 
                                            new Object[] {
                                                // Too Much Information -- ex.getMessage()
                                            }));
                    }
                } catch(IllegalStateException ise) {
                    // logs are presumed to be secure, thus the TMI info can be logged
                    Constants.jasperLog.log(Constants.getString
                                            ("jsp.error.file.not.found.TMI",
                                             new Object[] {
                                                 ex.getMessage()
                                             }), ex,
                                            Logger.ERROR);
                    // rethrow FileNotFoundException so someone higher up can handle
                    if(insecure_TMI)
                        throw ex;
                    else
                        throw new FileNotFoundException(Constants.getString
                                                        ("jsp.error.file.not.found", 
                                                         new Object[] {
                                                             // Too Much Information -- ex.getMessage()
                                                         }));
                }
                return;
            }
        }

        public void destroy()
        {
            if(theServlet != null)
                theServlet.destroy();
        }
    }


    protected ServletContext context = null;
    protected Hashtable jsps = new Hashtable();
    //    protected Hashtable loadedJSPs = new Hashtable();
    protected ServletConfig config;
    protected JasperLoader loader;
    protected Options options;
    protected ClassLoader parentClassLoader;
    protected ServletEngine engine;
    protected String serverInfo;

    /** Set to true to provide Too Much Information on errors */
    private final boolean insecure_TMI = false;

    static boolean firstTime = true;
    static boolean jdk12=false;
    static {
        try {
            Class.forName( "java.security.PrivilegedAction" );
            jdk12=true;
        } catch(Throwable ex ) {
        }
    }

    public void init(ServletConfig config)
    throws ServletException
    {
        super.init(config);
        this.config = config;
        this.context = config.getServletContext();
        this.serverInfo = context.getServerInfo();

        options = new EmbededServletOptions(config, context);

        parentClassLoader = (ClassLoader) context.getAttribute(Constants.SERVLET_CLASS_LOADER);
        if(parentClassLoader == null)
            parentClassLoader = this.getClass().getClassLoader();

        // getClass().getClassLoader() returns null in JDK 1.1.6/1.1.8
        if(parentClassLoader != null) {
            Constants.message("jsp.message.parent_class_loader_is", 
                              new Object[] {
                                  parentClassLoader.toString()
                              }, Logger.DEBUG);
        } else {
            Constants.message("jsp.message.parent_class_loader_is", 
                              new Object[] {
                                  "<none>"
                              }, Logger.DEBUG);
        }
        if( loader==null ) {
            if( jdk12 ) {
                try {
                    Class ld=Class.forName("org.apache.jasper.servlet.JasperLoader12");
                    loader=(JasperLoader)ld.newInstance();
                } catch(Throwable t ) {
                    t.printStackTrace();
                }
            }
            if( loader==null )
                loader = new org.apache.jasper.servlet.JasperLoader();

            loader.setParentClassLoader(parentClassLoader);
            loader.setOptions(options);
            Object pd=context.getAttribute("org.apache.tomcat.protection_domain");
            loader.setProtectionDomain( pd );
        }
        if(firstTime) {
            firstTime = false;
            Constants.message("jsp.message.scratch.dir.is", 
                              new Object[] { 
                                  options.getScratchDir().toString() 
                              }, Logger.INFORMATION );
            Constants.message("jsp.message.dont.modify.servlets", Logger.INFORMATION);
            JspFactory.setDefaultFactory(new JspFactoryImpl());
        }
    }

    private void serviceJspFile(HttpServletRequest request, 
                                HttpServletResponse response, String jspUri, 
                                Throwable exception, boolean precompile) 
    throws ServletException, IOException
    {
        boolean isErrorPage = exception != null;
        JspServletWrapper wrapper = null;

        /*
         * Several threads may be handling requests for the same jspUri.
         * Only one of them is allowed to create the JspServletWrapper.
         */
        synchronized(jsps){
            wrapper = (JspServletWrapper) jsps.get(jspUri);
            if(wrapper == null) {
                wrapper = new JspServletWrapper(jspUri, isErrorPage);
                jsps.put(jspUri, wrapper);
            }
        }

        wrapper.service(request, response, precompile);
    }


    boolean preCompile(HttpServletRequest request) 
    throws ServletException 
    {
        boolean precompile = false;
        String precom = request.getParameter(Constants.PRECOMPILE);
        String qString = request.getQueryString();

        if(precom != null) {
            if(precom.equals("true"))
                precompile = true;
            else if(precom.equals("false"))
                precompile = false;
            else {
                // This is illegal.
                throw new ServletException("Can't have request parameter " +
                                           " jsp_precomile set to " + precom);
            }
        } else if(qString != null && (qString.startsWith(Constants.PRECOMPILE) ||
                                      qString.indexOf("&" + Constants.PRECOMPILE)
                                      != -1))
            precompile = true;

        return precompile;
    }



    public void service (HttpServletRequest request, 
                         HttpServletResponse response)
    throws ServletException, IOException
    {
        try {
            String includeUri 
            = (String) request.getAttribute(Constants.INC_SERVLET_PATH);

            String jspUri;

            if(includeUri == null)
                jspUri = request.getServletPath();
            else
                jspUri = includeUri;

//	    System.out.println("JspServletWrapper: " + includeUri + " " +
//                            jspUri + 
// 			       (String) request.getAttribute(
//                                  Constants.INC_REQUEST_URI));

            boolean precompile = preCompile(request);

            Logger jasperLog = Constants.jasperLog;

            if(jasperLog != null &&
               jasperLog.matchVerbosityLevel(Logger.INFORMATION)) {
                jasperLog.log("JspEngine --> "+jspUri);
                jasperLog.log("\t     ServletPath: "+request.getServletPath());
                jasperLog.log("\t        PathInfo: "+request.getPathInfo());
                jasperLog.log("\t        RealPath: "
                              +getServletConfig().getServletContext().getRealPath(jspUri));
                jasperLog.log("\t      RequestURI: "+request.getRequestURI());
                jasperLog.log("\t     QueryString: "+request.getQueryString());
                jasperLog.log("\t  Request Params: ");
                Enumeration e = request.getParameterNames();
                while(e.hasMoreElements()) {
                    String name = (String) e.nextElement();
                    jasperLog.log("\t\t "+name+" = "+request.getParameter(name));
                }
            }
            serviceJspFile(request, response, jspUri, null, precompile);
        } catch(RuntimeException e) {
            throw e;
        } catch(ServletException e) {
            throw e;
        } catch(IOException e) {
            throw e;
        } catch(Throwable e) {
            throw new ServletException(e);
        }

        // It's better to throw the exception - we don't
        // know if we can deal with sendError ( buffer may be
        // commited )
        // catch (Throwable t) {
        // 	    unknownException(response, t);
        // 	}
    }

    public void destroy() {
        if(Constants.jasperLog != null)
            Constants.jasperLog.log("JspServlet.destroy()", Logger.INFORMATION);

        Enumeration servlets = jsps.elements();
        while(servlets.hasMoreElements())
            ((JspServletWrapper) servlets.nextElement()).destroy();
    }


    /*  Check if we need to reload a JSP page.
     *
     *  Side-effect: re-compile the JSP page.
     *
     *  @param classpath explicitly set the JSP compilation path.
     *  @return true if JSP files is newer
     */
    boolean loadJSP(String jspUri, String classpath, 
                    boolean isErrorPage, HttpServletRequest req, HttpServletResponse res) 
    throws JasperException, FileNotFoundException 
    {
        // Loader knows how to set the right priviledges, and call
        // doLoadeJsp
        return loader.loadJSP( this,jspUri, classpath, isErrorPage, req, res );
    }

    /*  Check if we need to reload a JSP page.
     *
     *  Side-effect: re-compile the JSP page.
     *
     *  @param classpath explicitly set the JSP compilation path.
     *  @return true if JSP files is newer
     */

    /*
     * A word about the thread synchronization below.  The call to 
     * compiler.isOutDated() is outside the synchronization block on purpose.  
     * The expectation is that for the vast majority of cases the JSP source file 
     * will not have changed and there will be no need to recompile the 
     * implementation class.  For those cases when a compile is required, we 
     * enter a block that is synchronized on the JspServletWrapper object for 
     * this JSP page.  Because the initial out dated check is unsynchronized, it 
     * is possible for more than one request to attempt to enter the synchronized 
     * compile block.  The compile() method contains performs an outdated check 
     * of its own.  The first thread into the block will cause a compile, the 
     * subsequent threads will essentially skip the compilation and instantiation 
     * steps.  
     * 
     * One other thing to note is that there is a window of time between the 
     * compiler.compile() call and the end of the synchronized block where a new 
     * thread entering doLoadJSP() will be told that that implementation class is 
     * up to date even though the code in the synchronized block has not 
     * completed loading and instantiating the class.  In this case doLoadJSP() 
     * will return false without attempting to compile the class.  This is OK 
     * because the JspServletWrapper.getServlet() method used by the 
     * JspServletWrapper.service() method is synchronized.  Thus, the service 
     * method will not receive the servlet class until it has been completely loaded and 
     * instantiated.  
     * 
     * The bottom line is that we avoid synchronizing a fairly expensive 
     * operation (isOutDated) but pay a small price of some unnecessary 
     * compilation attempts in the atypical case of a modified JSP file.  
     */
    protected boolean doLoadJSP(String jspUri, String classpath, 
                                boolean isErrorPage, HttpServletRequest req, HttpServletResponse res) 
    throws JasperException, FileNotFoundException 
    {
        JspServletWrapper jsw=(JspServletWrapper) jsps.get(jspUri);
        if( jsw==null ) {
            throw new JasperException("Can't happen - JspServletWrapper=null");
        }
        JspCompilationContext ctxt = new JspEngineContext(loader, classpath,
                                                          context, jspUri, 
                                                          isErrorPage, options,
                                                          req, res);
        boolean outDated = false; 

        Compiler compiler = null;
        synchronized(jsw){
            /*
             * Creating a compiler opens the associated .class file (if it exists)
             * and reads the actual class name.  If we allow a compiler to be
             * created while a compile is going on then bad things can happen.
             */
            compiler = ctxt.createCompiler();
        }

        try {


            outDated = compiler.isOutDated();
            if(!jsw.isInstantiated() || outDated ) {
                synchronized(jsw){
                    outDated = compiler.compile();
                    if(!jsw.isInstantiated() || outDated) {
                        if( null ==ctxt.getServletClassName() ) {
                            compiler.computeServletClassName();
                        }
                        jsw.instantiateServlet(loader.loadClass(ctxt.getFullClassName()));
                    }
                }
            }
        } catch(FileNotFoundException ex) {
            compiler.removeGeneratedFiles();
            throw ex;
        } catch(JasperException ex) {
            throw ex;
        } catch(ClassNotFoundException cex) {
                throw new JasperException(Constants.getString("jsp.error.unable.load"), 
                                          cex);
        } catch(Exception ex) {
            throw new JasperException(Constants.getString("jsp.error.unable.compile"),
                                      ex);
        }

        return outDated;
    }

    /**
 * Determines whether the current JSP class is older than the JSP file
 * from whence it came
 */
    public boolean isOutDated(File jsp, JspCompilationContext ctxt,
                              Mangler mangler ) {
        File jspReal = null;
        boolean outDated;

        jspReal = new File(ctxt.getRealPath(jsp.getPath()));

        File classFile = new File(mangler.getClassFileName());
        if(classFile.exists()) {
            outDated = classFile.lastModified() < jspReal.lastModified();
        } else {
            outDated = true;
        }

        return outDated;
    }

}
