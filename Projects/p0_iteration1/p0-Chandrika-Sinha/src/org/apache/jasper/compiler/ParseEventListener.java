/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/jasper/compiler/ParseEventListener.java,v 1.6 2000/06/14 22:51:51 mandar Exp $
 * $Revision: 1.6 $
 * $Date: 2000/06/14 22:51:51 $
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
package org.apache.jasper.compiler;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.jsp.tagext.TagInfo;
import javax.servlet.jsp.tagext.TagLibraryInfo;

import org.apache.jasper.JasperException;

/**
 * Interface for the JSP code generation backend. At some point should
 * probably try and make this a SAX (XML) listener. 
 *
 * @author Anil K. Vijendran
 */
public interface ParseEventListener {
    void setTemplateInfo(Mark start, Mark stop);
    void beginPageProcessing() throws JasperException;

    void handleComment(Mark start, Mark stop) throws JasperException;
    void handleDirective(String directive, 
			 Mark start, Mark stop, 
			 Hashtable attrs) throws JasperException;
    void handleDeclaration(Mark start, Mark stop, Hashtable attrs) throws JasperException;
    void handleScriptlet(Mark start, Mark stop, Hashtable attrs) throws JasperException;
    void handleExpression(Mark start, Mark stop, Hashtable attrs) throws JasperException;
    void handleBean(Mark start, Mark stop, Hashtable attrs) 
	throws JasperException;
    void handleBeanEnd (Mark start, Mark stop, Hashtable attrs)
	throws JasperException;
    void handleGetProperty(Mark start, Mark stop, Hashtable attrs) throws JasperException;
    void handleSetProperty(Mark start, Mark stop, Hashtable attrs) throws JasperException;
    void handlePlugin(Mark start, Mark stop, Hashtable attrs, Hashtable param, 
    			String fallback) throws JasperException;
    void handleCharData(Mark start, Mark stop, char[] chars) throws JasperException;


    /*
     * Custom tag support
     */
    TagLibraries getTagLibraries();

    /*
     * start: is either the start position at "<" if content type is JSP or empty, or
     *        is the start of the body after the "/>" if content type is tag dependent
     * stop: can be null if the body contained JSP tags... 
     */
    void handleTagBegin(Mark start, Mark stop, Hashtable attrs, String prefix, String shortTagName,
			TagLibraryInfo tli, TagInfo ti) 
	throws JasperException;

    void handleTagEnd(Mark start, Mark stop, String prefix, String shortTagName,
		      Hashtable attrs, TagLibraryInfo tli, TagInfo ti)
	throws JasperException;

    void handleForward(Mark start, Mark stop, Hashtable attrs, Hashtable param)
	throws JasperException;
    void handleInclude(Mark start, Mark stop, Hashtable attrs, Hashtable param)
	throws JasperException;

    void endPageProcessing() throws JasperException;
}

