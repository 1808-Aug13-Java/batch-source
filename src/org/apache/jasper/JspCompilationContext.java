/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/jasper/JspCompilationContext.java,v 1.3 2000/06/27 20:59:39 costin Exp $
 * $Revision: 1.3 $
 * $Date: 2000/06/27 20:59:39 $
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
 */ 





package org.apache.jasper;

import org.apache.jasper.compiler.JspReader;
import org.apache.jasper.compiler.ServletWriter;
//import org.apache.jasper.runtime.JspLoader;
import org.apache.jasper.compiler.TagLibraries;
import java.io.IOException;
import org.apache.jasper.compiler.Compiler;

/**
 * A place holder for various things that are used through out the JSP
 * engine. This is a per-request/per-context data structure. Some of
 * the instance variables are set at different points.
 *
 * JspLoader creates this object and passes this off to the "compiler"
 * subsystem, which then initializes the rest of the variables. 
 *
 * @author Anil K. Vijendran
 * @author Harish Prabandham
 */
public interface JspCompilationContext {

    /**
     * The classpath that is passed off to the Java compiler. 
     */
    public String getClassPath();
    
    /**
     * Get the input reader for the JSP text. 
     */
    public JspReader getReader();
    
    /**
     * Where is the servlet being generated?
     */
    public ServletWriter getWriter();
    
    /**
     * What class loader to use for loading classes while compiling
     * this JSP? I don't think this is used right now -- akv. 
     */
    public ClassLoader getClassLoader();

    /** Add a jar to the classpath used by the loader
     */
    public void addJar( String jar ) throws IOException ;

    /**
     * Are we processing something that has been declared as an
     * errorpage? 
     */
    public boolean isErrorPage();
    
    /**
     * What is the scratch directory we are generating code into?
     * FIXME: In some places this is called scratchDir and in some
     * other places it is called outputDir.
     */
    public String getOutputDir();
    
    /**
     * Path of the JSP URI. Note that this is not a file name. This is
     * the context rooted URI of the JSP file. 
     */
    public String getJspFile();
    
    /**
     * Just the class name (does not include package name) of the
     * generated class. 
     */
    public String getServletClassName();
    
    /**
     * The package name into which the servlet class is generated. 
     */
    public String getServletPackageName();

    /**
     * Utility method to get the full class name from the package and
     * class name. 
     */
    public String getFullClassName();

    /**
     * Full path name of the Java file into which the servlet is being
     * generated. 
     */
    public String getServletJavaFileName();

    /**
     * Are we keeping generated code around?
     */
    public boolean keepGenerated();

    /**
     * What's the content type of this JSP? Content type includes
     * content type and encoding. 
     */
    public String getContentType();

    /**
     * Get hold of the Options object for this context. 
     */
    public Options getOptions();

    public void setContentType(String contentType);

    public void setReader(JspReader reader);
    
    public void setWriter(ServletWriter writer);
    
    public void setServletClassName(String servletClassName);
    
    public void setServletPackageName(String servletPackageName);
    
    public void setServletJavaFileName(String servletJavaFileName);
    
    public void setErrorPage(boolean isErrPage);

    /**
     * Create a "Compiler" object based on some init param data. This
     * is not done yet. Right now we're just hardcoding the actual
     * compilers that are created. 
     */
    public Compiler createCompiler() throws JasperException;


    /** 
     * Get the full value of a URI relative to this compilations context
     */
    public String resolveRelativeUri(String uri);

    /**
     * Gets a resource as a stream, relative to the meanings of this
     * context's implementation.
     *@returns a null if the resource cannot be found or represented 
     *         as an InputStream.
     */
    public java.io.InputStream getResourceAsStream(String res);

    /** 
     * Gets the actual path of a URI relative to the context of
     * the compilation.
     */
    public String getRealPath(String path);

}

