/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/jasper/compiler/JspUtil.java,v 1.17.2.2 2000/11/06 04:08:14 larryi Exp $
 * $Revision: 1.17.2.2 $
 * $Date: 2000/11/06 04:08:14 $
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

import java.net.URL;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Enumeration;

import org.apache.jasper.Constants;
import org.apache.jasper.JasperException;


import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.EntityResolver;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;

/** 
 * This class has all the utility method(s).
 * Ideally should move all the bean containers here.
 *
 * @author Mandar Raje.
 * @author Rajiv Mordani.
 */
public class JspUtil {

    private static final String OPEN_EXPR  = "<%=";
    private static final String CLOSE_EXPR = "%>";

    public static char[] removeQuotes(char []chars) {
	CharArrayWriter caw = new CharArrayWriter();
	for (int i = 0; i < chars.length; i++) {
	    if (chars[i] == '%' && chars[i+1] == '\\' &&
		chars[i+2] == '\\' && chars[i+3] == '>') {
		caw.write('%');
		caw.write('>');
		i = i + 3;
	    }
	    else caw.write(chars[i]);
	}
	return caw.toCharArray();
    }

    // Checks if the token is a runtime expression.
    public static boolean isExpression (String token) {
	
	if (token.startsWith(OPEN_EXPR) && token.endsWith(CLOSE_EXPR)) {
	    return true;
	}

	return false;
    }

    // Returns the "expression" part -- takin <%= and %> out.
    public static String getExpr (String expression) {
	String returnString;
	int length = expression.length();
	
	if (expression.startsWith(OPEN_EXPR) && expression.endsWith(CLOSE_EXPR)) {
	    returnString = expression.substring (OPEN_EXPR.length(), length - CLOSE_EXPR.length());
	} else {
	    returnString = "";
	}

	return returnString;
    }

    // Parses the XML document contained in the InputStream.
    public static Document parseXMLDoc(InputStream in, String dtdResource, 
    					  String dtdId) throws JasperException 
    {
	return parseXMLDocJaxp(in, dtdResource, dtdId );
    }

    // Parses the XML document contained in the InputStream.
    public static Document parseXMLDocJaxp(InputStream in, String dtdResource, 
					   String dtdId)
	throws JasperException
    {
	try {
	    Document tld;
	    DocumentBuilderFactory docFactory = DocumentBuilderFactory.
		newInstance();
	    docFactory.setValidating(true);
	    docFactory.setNamespaceAware(true);
	    DocumentBuilder builder = docFactory.newDocumentBuilder();
	    
	    /***
	     * These lines make sure that we have an internal catalog entry for
	     * the taglib.dtdfile; this is so that jasper can run standalone 
	     * without running out to the net to pick up the taglib.dtd file.
	     */
	    MyEntityResolver resolver =
		new MyEntityResolver(dtdId, dtdResource);
	    builder.setEntityResolver(resolver);
	    tld = builder.parse(in);
	    return tld;
	} catch( ParserConfigurationException ex ) {
            throw new JasperException(Constants.
				      getString("jsp.error.parse.error.in.TLD",
						new Object[] {
						    ex.getMessage()
						}));
	} catch ( SAXException sx ) {
            throw new JasperException(Constants.
				      getString("jsp.error.parse.error.in.TLD",
						new Object[] {
						    sx.getMessage()
						}));
        } catch (IOException io) {
            throw new JasperException(Constants.
				      getString("jsp.error.unable.to.open.TLD",
						new Object[] {
						    io.getMessage() }));
	}
    }

    public static void checkAttributes (String typeOfTag, Hashtable attrs,
    					ValidAttribute[] validAttributes, Mark start)
					throws JasperException
    {
	boolean valid = true;
	Hashtable temp = (Hashtable)attrs.clone ();

	/**
	 * First check to see if all the mandatory attributes are present.
	 * If so only then proceed to see if the other attributes are valid
	 * for the particular tag.
	 */
	String missingAttribute = null;

	for (int i = 0; i < validAttributes.length; i++) {
	        
	    if (validAttributes[i].mandatory) {
	        if (temp.get (validAttributes[i].name) != null) {
	            temp.remove (validAttributes[i].name);
		    valid = true;
		} else {
		    valid = false;
		    missingAttribute = validAttributes[i].name;
		    break;
		}
	    }
	}

	/**
	 * If mandatory attribute is missing then the exception is thrown.
	 */
	if (!valid)
	    throw new ParseException(start, Constants.getString(
			"jsp.error.mandatory.attribute", 
                                 new Object[] { typeOfTag, missingAttribute}));

	/**
	 * Check to see if there are any more attributes for the specified
	 * tag.
	 */
	if (temp.size() == 0)
	    return;

	/**
	 * Now check to see if the rest of the attributes are valid too.
	 */
   	Enumeration enum = temp.keys ();
	String attribute = null;

	while (enum.hasMoreElements ()) {
	    valid = false;
	    attribute = (String) enum.nextElement ();
	    for (int i = 0; i < validAttributes.length; i++) {
	        if (attribute.equals(validAttributes[i].name)) {
		    valid = true;
		    break;
		}
	    }
	    if (!valid)
	        throw new ParseException(start, Constants.getString(
			"jsp.error.invalid.attribute", 
                                 new Object[] { typeOfTag, attribute }));
	}
    }
    
    public static String escapeQueryString(String unescString) {
	if ( unescString == null )
	    return null;
	
	String escString    = "";
	String shellSpChars = "\\\"";
	
	for(int index=0; index<unescString.length(); index++) {
	    char nextChar = unescString.charAt(index);
	    
	    if( shellSpChars.indexOf(nextChar) != -1 )
		escString += "\\";
	    
	    escString += nextChar;
	}
	return escString;
    }
    
    /**
     *  Escape the 5 entities defined by XML.
     */
    public static String escapeXml(String s) {
        if (s == null) return null;
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c == '<') {
                sb.append("&lt;");
            } else if (c == '>') {
                sb.append("&gt;");
            } else if (c == '\'') {
                sb.append("&apos;");
            } else if (c == '&') {
                sb.append("&amp;");
            } else if (c == '"') {
                sb.append("&quote;");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static class ValidAttribute {
   	String name;
	boolean mandatory;

	public ValidAttribute (String name, boolean mandatory) {
	    this.name = name;
	    this.mandatory = mandatory;
	}

	public ValidAttribute (String name) {
	    this (name, false);
	}
    }
}

class MyEntityResolver implements EntityResolver {

    String dtdId;
    String dtdResource;
    
    public MyEntityResolver(String id, String resource) {
	this.dtdId = id;
	this.dtdResource = resource;
    }
    
    public InputSource resolveEntity(String publicId, String systemId)
	throws SAXException, IOException
    {
	//System.out.println ("publicId = " + publicId);
	//System.out.println ("systemId is " + systemId);
	//System.out.println ("resource is " + dtdResource);
	if (publicId.equals(dtdId)) {
	    InputStream input =
		this.getClass().getResourceAsStream(dtdResource);
	    InputSource isrc =
		new InputSource(input);
	    return isrc;
	}
	else {
	    //System.out.println ("returning null though dtdURL is " + dtdURL);
	    return null;
	}
    }
}




