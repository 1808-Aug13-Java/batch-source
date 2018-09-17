/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/util/pattern/Attic/ImplicationTable.java,v 1.1 1999/11/27 21:45:41 harishp Exp $
 * $Revision: 1.1 $
 * $Date: 1999/11/27 21:45:41 $
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

import java.util.Vector;
import java.util.Dictionary;
import java.util.Enumeration;

/**
 *
 * This is a variant of a Dictionary in which the get() decision is
 * based on "implication of key" rather than equality of key. If the
 * key does not implement Implication interface, the usual equality
 * checks would apply => hashcode()  && equals(), so the behavior is
 * similar to a hashtable.
 * <BR>
 * Note: <BR>
 * In this implementation, when an association is made (put()), we make 
 * sure that there is no such key (equality check). But on lookup,
 * (get()) implies() is checked.
 * 
 * @author Harish Prabandham
 */
public class ImplicationTable extends Dictionary {
    private Vector table = new Vector();

    /**
     * Constructs a new Implication table
     */
    public ImplicationTable() {
    }

    /**
     * Returns the size of this table.
     * @return The size (# of keys) within this table.
     */
    public int size() {
        return table.size();
    }

    /**
     * Checks whether this table is empty or not.
     * @return true if the table is empty. false otherwise.
     */
    public boolean isEmpty() {
        return size() <= 0 ? true:false;
    }

    /**
     * Returns an enumeration of all the values in the table.
     * @return Enumeration of values.
     */
    public Enumeration elements() {
        Vector tmp = new Vector();

        for(int i=0; i < table.size(); ++i) {
            Entry e = (Entry) table.elementAt(i);
            tmp.addElement(e.value);
        }

        return tmp.elements();
    }

    /**
     * Returns an enumeration of all the keys in the table.
     * @return Enumeration of keys.
     */
    public Enumeration keys() {
        return keys(null);
    }

    /**
     * Returns an enumeration of keys in the table that imply
     * the given key. If the key in the table implements the
     * Implication interface, then we check for implies().
     * Otherwise we check for exact for equality.
     * @return Enumeration of keys.
     */
    public Enumeration keys(Object key) {
        Vector tmp = new Vector();

        for(int i=0; i < table.size(); ++i) {
            Entry e = (Entry) table.elementAt(i);
            if(key != null) {
                if(e.key instanceof Implication) {
                    if(((Implication)e.key).implies(key))
                        tmp.addElement(e.key);
                } else {
                    if( (e.key.hashCode() == key.hashCode()) &&
                        e.key.equals(key))
                        tmp.addElement(e.key);
                }
            } else 
                tmp.addElement(e.key);
        }

        return tmp.elements();        
    }
    
    
    /**
     * Adds the key to value association in the dictionary. A
     * check is performed to see if such a key exists (equality
     * comparison).
     * @param The key (may implement Implication interface)
     * @param The value (any object, including null)
     * @return The previous value corresponding to the key if it
     * exists, null otherwise.
     */
    public synchronized Object put(Object key, Object value) {
        // System.out.println("associate " + key + " = " + value);
        Object val = remove(key);
        table.addElement(new Entry(key, value));
        return val;
    }

    /**
     * Adds the key to value association in the dictionary. A
     * check is performed to see if such a key exists. (equality
     * comparison)
     * @param The key (may implement Implication interface)
     * @param The value (any object, including null)
     * @return The previous value corresponding to the key if it
     * exists, null otherwise.
     */
    public synchronized Object putValue(Object key, Object value) {
        return put(key,value);
    }
    

    /**
     * Returns the value corresponding to the key in the table that satifies
     * the implication condition if the corresponding key implemented the
     * Implication interface, otherwise equality comparison is attempted. The
     * first value corresponding to the key match is returned. The
     * order of the key search is not guaranteed.
     * @param The key
     * @return the value corresponding to the key that was implied by
     * the given key.
     */
    public Object get(Object key) {
        for(int i=0; i < table.size(); ++i) {
            Entry e = (Entry) table.elementAt(i);

            // check for implication.
            if(e.key instanceof Implication) {
                if(((Implication)e.key).implies(key)) 
                    return e.value;
            }

            // check for equality.
            if((e.key.hashCode() == key.hashCode()) && e.key.equals(key))
                return e.value;
        }

        return null;
    }

    /**
     * Returns the value corresponding to the key in the table that
     * matches the given key exactly (equality comparison)
     * @param The key
     * @return the value corresponding to the key. (exact match)
     */
    public Object getValue(Object key) {
        int indx = table.indexOf(key);

        // System.out.println("index = " + indx);
       
        if(indx != -1) {
            Entry e = (Entry) table.elementAt(indx);
            return e.value;
        } else {
            return null;
        }
    }

    /**
     * Removes the key if one exists. This method uses equality comparison
     * to look for the key to remove.
     * @param The key
     * @return The previous value corresponding to the key if it
     * exists, null otherwise.
     */
    public synchronized Object remove(Object key) {
        Object val = getValue(key);

        if(val != null) {
            table.removeElement(key);
        }

        return val;
    }
}

class Entry {
    Object key;
    Object value;

    Entry(Object key, Object value) {
        if(key == null)
            throw new NullPointerException();
        this.key = key;
        this.value = value;
    }

    public boolean equals(Object other) {
        if(other instanceof Entry)
            return key.equals(((Entry) other).key);
        else
            return key.equals(other.toString());
    }

    public int hashCode() {
        return key.hashCode();
    }

    public String toString() {
        return key.toString();
    }
}









