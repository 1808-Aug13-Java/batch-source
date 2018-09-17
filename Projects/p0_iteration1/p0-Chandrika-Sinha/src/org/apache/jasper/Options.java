/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/jasper/Options.java,v 1.11.2.2 2001/01/12 04:46:58 larryi Exp $
 * $Revision: 1.11.2.2 $
 * $Date: 2001/01/12 04:46:58 $
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

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * A class to hold all init parameters specific to the JSP engine. 
 *
 * @author Anil K. Vijendran
 * @author Hans Bergsten
 */
public interface Options {
    /**
     * Are we keeping generated code around?
     */
    public boolean getKeepGenerated();
    
    /**
     * Are we supporting large files?
     */
    public boolean getLargeFile();

    /**
     * Are we supporting HTML mapped servlets?
     */
    public boolean getMappedFile();
    
    
    /**
     * Should errors be sent to client or thrown into stderr?
     */
    public boolean getSendErrorToClient();
 
    /**
     * Should we include debug information in compiled class?
     */
    public boolean getClassDebugInfo();

    /**
     * Class ID for use in the plugin tag when the browser is IE. 
     */
    public String getIeClassId();
    
    /**
     * What is my scratch dir?
     */
    public File getScratchDir();

    /**
     * What classpath should I use while compiling the servlets
     * generated from JSP files?
     */
    public String getClassPath();

    /**
     * What compiler plugin should I use to compile the servlets
     * generated from JSP files?
     */
    public Class getJspCompilerPlugin();

    /**
     * Path of the compiler to use for compiling JSP pages.
     */
    public String getJspCompilerPath();
    
    /**
     * ProtectionDomain for this JSP Context when using a SecurityManager
     */
    public Object getProtectionDomain();

    /**
     * Java platform encoding to generate the JSP
     * page servlet.
     */
    public String getJavaEncoding();
}
