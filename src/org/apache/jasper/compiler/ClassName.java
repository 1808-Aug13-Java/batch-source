/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/jasper/compiler/ClassName.java,v 1.2 2000/01/18 02:54:57 rubys Exp $
 * $Revision: 1.2 $
 * $Date: 2000/01/18 02:54:57 $
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

import org.apache.jasper.JasperException;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import org.apache.jasper.Constants;

/**
 * Parse a .class file to figure out the name of the class from which
 * it was generated. 
 *
 * @author Anil Vijendran
 */
public class ClassName {

    static String processClassData(InputStream in) throws JasperException, IOException {
	DataInputStream din = new DataInputStream(in);
	din.readInt(); // magic
	din.readUnsignedShort(); // majorVersion
	din.readUnsignedShort(); // minorVersion
	int count = din.readUnsignedShort(); // #constant pool entries
	ConstantPool[] constantPool = new ConstantPool[count];
	constantPool[0] = new ConstantPool();
	for (int i = 1; i < constantPool.length; i++) {
	    constantPool[i] = new ConstantPool();
	    if (!constantPool[i].read(din))
		throw new JasperException(Constants.getString("jsp.error.classname"));
	    // These two types take up "two" spots in the table
	    if ((constantPool[i].type == ConstantPool.LONG) ||
		(constantPool[i].type == ConstantPool.DOUBLE))
		i++;
	}

	for (int i = 1; i < constantPool.length; i++) {
	    if (constantPool[i] == null)
		continue;
	    if (constantPool[i].index1 > 0)
		constantPool[i].arg1 = constantPool[constantPool[i].index1];
	    if (constantPool[i].index2 > 0)
		constantPool[i].arg2 = constantPool[constantPool[i].index2];
	}
	int accessFlags = din.readUnsignedShort();
	ConstantPool thisClass = constantPool[din.readUnsignedShort()];
        din.close();
	return printClassName(thisClass.arg1.strValue);
    }

    private static String printClassName(String s) {
	StringBuffer x;

	if (s.charAt(0) == '[') {
	    return(typeString(s, ""));
	}

	x = new StringBuffer();
	for (int j = 0; j < s.length(); j++) {
	    if (s.charAt(j) == '/')
		x.append('.');
	    else
		x.append(s.charAt(j));
	}
	return (x.toString());
    }

    private static String typeString(String typeString, String varName) {
	    int isArray = 0;
	    int	ndx = 0;
	    StringBuffer x = new StringBuffer();

	    while (typeString.charAt(ndx) == '[') {
	        isArray++;
	        ndx++;
	    }

	    switch (typeString.charAt(ndx)) {
	        case 'B' :
		        x.append("byte ");
		        break;
	        case 'C' :
		        x.append("char ");
		        break;
	        case 'D' :
		        x.append("double ");
		        break;
	        case 'F' :
		        x.append("float ");
		        break;
	        case 'I' :
		        x.append("int ");
		        break;
	        case 'J' :
		        x.append("long ");
		        break;
	        case 'L' :
		        for (int i = ndx+1; i < typeString.indexOf(';'); i++) {
		            if (typeString.charAt(i) != '/')
			            x.append(typeString.charAt(i));
		            else
			        x.append('.');
		        }
		        x.append(" ");
		        break;
	        case 'V':
		        x.append("void ");
		        break;
	        case 'S' :
		        x.append("short ");
		        break;
	        case 'Z' :
		        x.append("boolean ");
		        break;
	    }
	    x.append(varName);
	    while (isArray > 0) {
	        x.append("[]");
	        isArray--;
	    }
	    return (x.toString());
    }

    public static String getClassName(String classFile) throws JasperException {
	try {
	    //	    System.out.println("Getting class name from class data");
	    FileInputStream fin = new FileInputStream(classFile);
	    return processClassData(fin);
	} catch (IOException ex) {
	    throw new JasperException(Constants.getString("jsp.error.classname"), 
	    					ex);
	}
    }
    
    public static void main(String[] args) {
	try {
	    for(int i = 0; i < args.length; i++)
		System.out.println("Filename: "+ args[i]+" Classname: "+getClassName(args[i]));
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
}

class ConstantPool {
    int	type;			// type of this item
    String name; 		// String for the type
    ConstantPool  arg1;	// index to first argument
    ConstantPool  arg2;	// index to second argument
    int index1, index2;
    String 	strValue; 		// ASCIZ String value
    int		intValue;
    long	longValue;
    float	floatValue;
    double	doubleValue;

    public static final int CLASS = 7;
    public static final int FIELDREF = 9;
    public static final int METHODREF = 10;
    public static final int STRING = 8;
    public static final int INTEGER = 3;
    public static final int FLOAT = 4;
    public static final int LONG = 5;
    public static final int DOUBLE = 6;
    public static final int INTERFACE = 11;
    public static final int NAMEANDTYPE = 12;
    public static final int ASCIZ = 1;
    public static final int UNICODE = 2;


    /**
     * Generic constructor
     */
    public ConstantPool() {
	index1 = -1;
	index2 = -1;
	arg1 = null;
	arg2 = null;
	type = -1;
    }

    public boolean read(DataInputStream din)
	throws IOException {
	int	len;
	char	c;

	type = din.readByte();
	switch (type) {
	    case CLASS:
		name = "Class";
		index1 = din.readUnsignedShort();
		index2 = -1;
		break;
	    case FIELDREF:
		name = "Field Reference";
		index1 = din.readUnsignedShort();
		index2 = din.readUnsignedShort();
		break;
	    case METHODREF:
		name = "Method Reference";
		index1 = din.readUnsignedShort();
		index2 = din.readUnsignedShort();
		break;
	    case INTERFACE:
		name = "Interface Method Reference";
		index1 = din.readUnsignedShort();
		index2 = din.readUnsignedShort();
		break;
	    case NAMEANDTYPE:
		name = "Name and Type";
		index1 = din.readUnsignedShort();
		index2 = din.readUnsignedShort();
		break;
	    case STRING:
		name = "String";
		index1 = din.readUnsignedShort();
		index2 = -1;
		break;
	    case INTEGER:
		name = "Integer";
		intValue = din.readInt();
		break;
	    case FLOAT:
		name = "Float";
		floatValue = din.readFloat();
		break;
	    case LONG:
		name = "Long";
		longValue = din.readLong();
		break;
	    case DOUBLE:
		name = "Double";
		doubleValue = din.readDouble();
		break;
	    case ASCIZ:
	    case UNICODE:
		if (type == ASCIZ)
		    name = "ASCIZ";
		else
		    name = "UNICODE";

		StringBuffer xxBuf = new StringBuffer();

		len = din.readUnsignedShort();
		while (len > 0) {
		    c = (char) (din.readByte());
		    xxBuf.append(c);
		    len--;
		}
		strValue = xxBuf.toString();
		break;
	    default:
		System.err.println(Constants.getString("jsp.warning.bad.type"));
	}
	return (true);
    }
}

