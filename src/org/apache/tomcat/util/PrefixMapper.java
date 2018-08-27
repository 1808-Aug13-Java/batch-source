/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/util/PrefixMapper.java,v 1.3.2.4 2001/05/02 17:44:11 costin Exp $
 * $Revision: 1.3.2.4 $
 * $Date: 2001/05/02 17:44:11 $
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


package org.apache.tomcat.util;

import java.net.URL;
import java.io.File;
import java.net.MalformedURLException;
import java.io.IOException;
import java.util.*;

/** Prefix and exact mapping alghoritm.
 *XXX finish factoring out the creation of the map ( right now direct field access is
 *  used, since the code was just cut out from SimpleMapper).
 *  XXX make sure the code is useable as a general path mapper - or at least a bridge
 *  can be created between SimpleMapper and a patern matcher like the one in XPath
 *
 * @author Costin Manolache
 */
public class PrefixMapper {
    // host -> PrefixMapper for virtual hosts
    Hashtable vhostMaps=new Hashtable();


    Hashtable prefixMappedServlets;
    Hashtable exactMappedServlets;

        // Cache the most recent mappings
    // Disabled by default ( since we haven't implemented
    // capacity and remove ). 
    Hashtable mapCache;
    // By using TreeMap instead of SimpleMap you go from 143 to 161 RPS
    // ( at least on my machine )
    // Interesting - even if SimpleHashtable is faster than Hashtable
    // most of the time, the average is very close for both - it seems
    // that while the synchronization in Hashtable is locking, GC have
    // a chance to work, while in SimpleHashtable case GC creates big
    // peeks. That will go away with more reuse, so we should use SH.

    // An alternative to explore after everything works is to use specialized
    // mappers ( extending this one for example ) using 1.2 collections
    // TreeMap mapCache;
    boolean mapCacheEnabled=false;

    
    public PrefixMapper() {
	prefixMappedServlets=new Hashtable();
	exactMappedServlets=new Hashtable();
	mapCache=new Hashtable();
    }

    public void setMapCache( boolean v ) {
	mapCacheEnabled=v;
    }
    
//     public void removeMapping( String host, String path ) {
// 	// XXX not implemented
//     }

    /** Remove all mappings matching path
     */
    public void removeAllMappings( String host, String path ) {
	PrefixMapper vmap=this;
	if( host!=null ) {
	    host=host.toLowerCase();
	    vmap=(PrefixMapper)vhostMaps.get(host);
	}
	
	// remove all paths starting with path
	Enumeration en=vmap.prefixMappedServlets.keys();
	while( en.hasMoreElements() ) {
	    String s=(String)en.nextElement();
	    if( s.startsWith( path ))
		vmap.prefixMappedServlets.remove( s );
	}
	
	en=vmap.exactMappedServlets.keys();
	while( en.hasMoreElements() ) {
	    String s=(String)en.nextElement();
	    if( s.startsWith( path ))
		vmap.exactMappedServlets.remove( s );
	}
	// reset the cache
	mapCache=new Hashtable();
	
    }

    /**
     */
    void addMapping( String path, Object target ) {
	prefixMappedServlets.put( path, target);
    }

    /**
     */
    void addExactMapping( String path, Object target ) {
	exactMappedServlets.put( path, target);
    }
    
    /**
     */
    public void addMapping( String host, String path, Object target ) {
	if( host == null )
	    prefixMappedServlets.put( path, target);
	else {
	    host=host.toLowerCase();
	    PrefixMapper vmap=(PrefixMapper)vhostMaps.get( host );
	    if( vmap == null ) {
		vmap=new PrefixMapper();
		vhostMaps.put( host, vmap );
		vmap.setMapCache( mapCacheEnabled );
	    }
	    vmap.addMapping( path, target );
	}
    }

    /**
     */
    public void addExactMapping( String host, String path, Object target ) {
	if( host==null )
	    exactMappedServlets.put( path, target);
	else {
	    host=host.toLowerCase();
	    PrefixMapper vmap=(PrefixMapper)vhostMaps.get( host );
	    if( vmap == null ) {
		vmap=new PrefixMapper();
		vhostMaps.put( host, vmap );
	    }
	    vmap.addExactMapping( path, target );
	}
    }
    
    
    // -------------------- Implementation --------------------

    /** Match a prefix rule - /foo/bar/index.html/abc
     */
    public Object getLongestPrefixMatch( String host, String path ) {
	Object container = null;
        String s = path;

	PrefixMapper myMap=null;
	if( host!=null ) {
	    myMap=(PrefixMapper)vhostMaps.get( host );
	    if( myMap==null ) {
		myMap=(PrefixMapper)vhostMaps.get( host.toLowerCase() );
	    }
	}
	if( myMap==null ) myMap = this; // default server

	
	container=myMap.exactMappedServlets.get( path );
	if( container != null ) return container; // and we're done!

	/** Cache for request results - exploit the fact that few
	 *  request are more "popular" than other.
	 *  Disable it if you want to benchmark the mapper !!!
	 */
	if( myMap.mapCacheEnabled ) {
	    container=myMap.mapCache.get(path);
	    if( container!=null ) return container;
	}
		
	while (s.length() >= 0) {
	    //if(debug>8) context.log( "Prefix: " + s  );
	    container = myMap.prefixMappedServlets.get(s);
	    
	    if (container == null) {
		// if empty string didn't map, time to give up
		if ( s.length() == 0 )
                    break;
		s=URLUtil.removeLast( s );
	    }  else {
		if( myMap.mapCacheEnabled ) {
		    // XXX implement LRU or another replacement alghoritm
		    myMap.mapCache.put( path, container );
		}
		return container;
	    }
	}
	return container;
    }

}

 
