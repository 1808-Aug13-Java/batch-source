/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/jasper/compiler/DumbParseEventListener.java,v 1.4 2000/06/14 22:51:50 mandar Exp $
 * $Revision: 1.4 $
 * $Date: 2000/06/14 22:51:50 $
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

import javax.servlet.jsp.tagext.TagInfo;
import javax.servlet.jsp.tagext.TagLibraryInfo;

import org.apache.jasper.JasperException;


/**
 * Throwaway class that can be used for debugging during development
 * etc. This probably should go away soon. 
 *
 * @author Anil K. Vijendran
 */
public class DumbParseEventListener extends BaseJspListener {
    
    public DumbParseEventListener(JspReader reader, ServletWriter writer) {
	super(reader, writer);
    }

    public void handleComment(Mark start, Mark stop) 
        throws JasperException 
    {
	System.err.println("\nComment: ");
	System.err.print("\t");
	System.err.println(reader.getChars(start, stop));
    }

    public void handleDirective(String directive, Mark start, Mark stop, Hashtable attrs) 
        throws JasperException 
    {
	System.err.println("\nDirective: "+directive);
	System.err.println("\t"+attrs);
    }
    
    public void handleDeclaration(Mark start, Mark stop, Hashtable attrs) 
        throws JasperException 
    {
	System.err.println("\nDeclaration: ");
	System.err.println(reader.getChars(start, stop));
    }
    
    public void handleScriptlet(Mark start, Mark stop, Hashtable attrs) 
        throws JasperException 
    {
	System.err.println("\nScriptlet: ");
	System.err.println(reader.getChars(start, stop));
    }
    
    public void handleExpression(Mark start, Mark stop, Hashtable attrs) 
        throws JasperException 
    {
	System.err.println("\nExpression: ");
	System.err.println(reader.getChars(start, stop));
    }

    public void handleBean(Mark start, Mark stop, Hashtable attrs)
        throws JasperException 
    {
	System.err.println("\nBean: ");
	System.err.println("\t"+attrs);
    }

    public void handleBeanEnd (Mark start, Mark stop, Hashtable attrs)
	throws JasperException 
    {
        
	System.err.println("\nBean: ");
	System.err.println("\t"+attrs);
    }


    public void handleGetProperty(Mark start, Mark stop, Hashtable attrs)	
        throws JasperException 
    {
	System.err.println("\nGetProperty: ");
	System.err.println("\t"+attrs);
    }
    
    public void handleSetProperty(Mark start, Mark stop, Hashtable attrs)
        throws JasperException 
    {
	System.err.println("\nSetProperty: ");
	System.err.println("\t"+attrs);
    }
    
    public void handlePlugin(Mark start, Mark stop, Hashtable attrs)
        throws JasperException 
    {
	System.err.println("\nPlugin: ");
	System.err.println("\t"+attrs);
    }
    
    public void handleCharData(char[] chars)
        throws JasperException 
    {
	System.err.print(chars);
    }
    
    public void handleForward(Mark start, Mark stop, Hashtable attrs)
        throws JasperException 
    {
	System.err.println("\n Forward: ");
	System.err.println("\t"+attrs);
    }

    public void handleInclude(Mark start, Mark stop, Hashtable attrs)
        throws JasperException 
    {
	System.err.println("\n Include: ");
	System.err.println("\t"+attrs);
    }

    public TagLibraries getTagLibraries() {
	return null;
    }

    public void handleTagBegin(Mark start, Hashtable attrs, String prefix, 
			       String shortTagName, TagLibraryInfo tli, 
			       TagInfo ti)
	throws JasperException
    {
	System.err.println("\nUser-defined Tag Start "+prefix+":"+shortTagName+" --> " );
	System.err.println("\tAttrs"+attrs);
    }
    
    public void handleTagEnd(Mark start, Mark stop, String prefix, 
			     String shortTagName, Hashtable attrs, 
                             TagLibraryInfo tli, TagInfo ti)
	throws JasperException
    {
	System.err.println("\nUser-defined Tag End "+prefix+":"+shortTagName+" --> ");
	System.err.println("\tBody "+reader.getChars(start, stop));
    }

}
