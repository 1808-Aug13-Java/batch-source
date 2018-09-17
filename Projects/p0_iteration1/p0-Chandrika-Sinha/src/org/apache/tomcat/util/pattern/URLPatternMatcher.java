/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/util/pattern/Attic/URLPatternMatcher.java,v 1.1 1999/11/27 21:45:42 harishp Exp $
 * $Revision: 1.1 $
 * $Date: 1999/11/27 21:45:42 $
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

import java.util.Enumeration;

/**
 * A form of pattern matching for URLs in which the object corresponding
 * to the longest matching pattern is returned.
 *
 * @author Harish Prabandham
 */
public class URLPatternMatcher implements PatternMatcher {
    private ImplicationTable itable = new ImplicationTable();
    
    public URLPatternMatcher() {
    }

    public void set(String pattern, Object value) {
        itable.put(new WildcardPattern(pattern), value);
    }

    public void remove(String pattern) {
        itable.remove(pattern);
    }

    public Object match(String key) {
        // Since we want the longest pattern match, we cannot use the
        // itable.get(key)...
        int len =0;
        Object val = null;
        
        for(Enumeration e = itable.keys(key); e.hasMoreElements(); ){
            Object thiskey = e.nextElement();
            String pattern = thiskey.toString();
            if(pattern.length() > len)
                val = itable.getValue(thiskey);
        }

        return val;
    }

    public static void main(String[] args) {
        PatternMatcher p = new URLPatternMatcher();
        try {
            p.set("*", "default");
            p.set(args[0], "works");
            System.out.println("Match: " + p.match(args[1]));
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}





