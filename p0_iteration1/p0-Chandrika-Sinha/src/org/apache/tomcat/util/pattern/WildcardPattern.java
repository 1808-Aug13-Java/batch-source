/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/util/pattern/Attic/WildcardPattern.java,v 1.1 1999/11/27 21:45:46 harishp Exp $
 * $Revision: 1.1 $
 * $Date: 1999/11/27 21:45:46 $
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

package org.apache.tomcat.util.pattern;

/**
 *
 * This class implements a single Wildcard pattern behavior.
 * It can be used for checking/matching patterns such as:
 * *.foo, boo*foo, /foo/*, /foo*, /*, *
 *
 * @author Harish Prabandham
 */
public class WildcardPattern implements Implication {
    public static String WILDCARD = "*";
    private String pattern;

    public WildcardPattern(String pattern) {
        this.pattern = pattern;
        // System.out.println("created: " + pattern);
    }

    public boolean equals(Object other) {
        if(other instanceof WildcardPattern) {
            WildcardPattern o = (WildcardPattern) other;
            return this.pattern.equals(o.pattern);
        } else
            return this.pattern.equals(other.toString());
    }

    public int hashCode() {
        return pattern.hashCode();
    }

    public boolean implies(Object other) {
        if(other == null)
            return false;
        
        if(other instanceof WildcardPattern) {
            WildcardPattern o = (WildcardPattern) other;
            return implies(o.pattern);
        } else
            return impliesCheck(other.toString());
    }

    private boolean impliesCheck(String str) {
        if(pattern.equals(str))
            return true;

        int indx = pattern.lastIndexOf(WILDCARD);

        if(indx != -1) {
            String prematch = pattern.substring(0, indx);
            String postmatch = pattern.substring(indx+ WILDCARD.length());

            // checks for patterns such as:
            // *.foo, boo*foo, /foo/*, /foo*, /*, *
            // This cannot check for multiple *'s
            if(str.startsWith(prematch) && str.endsWith(postmatch)) {
                return true;
            }
        }

        return false;
    }

    public String toString() {
        return pattern;
    }
}







