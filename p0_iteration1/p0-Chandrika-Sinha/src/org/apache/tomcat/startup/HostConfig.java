package org.apache.tomcat.startup;

import java.beans.*;
import java.io.*;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.Hashtable;
import java.util.*;
import java.net.*;
import org.apache.tomcat.util.*;
import org.apache.tomcat.util.xml.*;
import org.apache.tomcat.core.*;
import org.xml.sax.*;

/** Used by Tomcat.java, need to be public for Reflection in JDK11
 */
public class HostConfig {
    ContextManager cm;
    String hostName;
    
    public HostConfig(ContextManager cm) {
	this.cm=cm;
    }

    public void setName( String name ) {
	hostName=name;
    }
    
    public void addContext( Context ctx ) {
	try {
	    ctx.setContextManager( cm );
	    ctx.setHost( hostName );
	    cm.addContext( ctx );
	} catch(Exception ex ) {
	    ex.printStackTrace();
	}
    }
}
    
