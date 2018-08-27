/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/util/Attic/FileUtil.java,v 1.9.2.8 2001/03/21 22:23:06 arieh Exp $
 * $Revision: 1.9.2.8 $
 * $Date: 2001/03/21 22:23:06 $
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

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/*
 * FileUtil contains utils for dealing with Files. Some of these are 
 * already present in JDK 1.2 but since we can rely on that and need 
 * to run on both JDK 1.1.x and JDK 1.2, we are replicating some of 
 * that code here. 
 *
 * FileUtil also takes care of File.getAbsolutePath() and
 * File.getNamePath() troubles when running on JDK 1.1.x/Windows
 *
 * @author James Todd [gonzo@eng.sun.com]
 * @author Anil K. Vijendran [akv@eng.sun.com]
 * @author Arieh Markel [arieh.markel@sun.com]
 */

public class FileUtil {

    public static File[] listFiles(File dir) {

	String[] ss = dir.list();
	if (ss == null) 
	    return null;
	int n = ss.length;
	File[] fs = new File[n];
	for(int i = 0; i < n; i++) {
	    fs[i] = new File(dir.getPath(), ss[i]);
	}
	return fs;
    }


    /** Will concatenate 2 paths, dealing with ..
     * ( /a/b/c + d = /a/b/d, /a/b/c + ../d = /a/d )
     * Used in Request.getRD
     * @return null if error occurs
     */
    public static String catPath(String lookupPath, String path) {
	// Cut off the last slash and everything beyond
	int index = lookupPath.lastIndexOf("/");
	lookupPath = lookupPath.substring(0, index);
	
	// Deal with .. by chopping dirs off the lookup path
	while (path.startsWith("../")) { 
	    if (lookupPath.length() > 0) {
		index = lookupPath.lastIndexOf("/");
		lookupPath = lookupPath.substring(0, index);
	    } 
	    else {
		// More ..'s than dirs, return null
		return null;
	    }
	    
	    index = path.indexOf("../") + 3;
	    path = path.substring(index);
	}
	
	return lookupPath + "/" + path;
    }

    /** All the safety checks from getRealPath() and
	DefaultServlet.

    */
    public static String safePath( String base, String path ) {
	// Hack for Jsp ( and other servlets ) that use rel. paths 
	// if( ! path.startsWith("/") ) path="/"+ path;

	String normP=path;
	if( path.indexOf('\\') >=0 )
	    normP= path.replace('\\', '/');

	if ( !normP.startsWith("/"))
	    normP = "/" + normP;

	int index = normP.indexOf("/../");
	if (index >= 0) {

	    // Clean out "//" and "/./" so they will not be confused
	    // with real parent directories
	    int index2 = 0;
	    while ((index2 = normP.indexOf("//", index2)) >= 0) {
		normP = normP.substring(0, index2) +
		    normP.substring(index2 + 1);
		if (index2 < index)
		    index--;
	    }
	    index2 = 0;
	    while ((index2 = normP.indexOf("/./", index2)) >= 0) {
		normP = normP.substring(0, index2) +
		    normP.substring(index2 + 2);
		if (index2 < index)
		    index -= 2;
	    }

	    // Remove cases of "/{directory}/../"
	    while (index >= 0) {
		// If no parent directory to remove, return null
		if (index == 0)
		    return (null);	// Trying to leave our context
		index2 = normP.lastIndexOf('/', index-1);
		normP = normP.substring(0, index2) +
		    normP.substring(index + 3);
		index = normP.indexOf("/../", index2);
	    }

	}

	String realPath= base + normP;

	// Probably not needed - it will be used on the local FS
	realPath = FileUtil.patch(realPath);
	String canPath=null;
	
	try {
	    canPath=new File(realPath).getCanonicalPath();
	} catch( IOException ex ) {
	    ex.printStackTrace();
	    return null;
	}

	// This absPath/canPath comparison plugs security holes...
	// On Windows, makes "x.jsp.", "x.Jsp", and "x.jsp%20"
	// return 404 instead of the JSP source
	// On all platforms, makes sure we don't let ../'s through
	// Unfortunately, on Unix, it prevents symlinks from working
	// So, a check for File.separatorChar='\\' ..... It hopefully
	// happens on flavors of Windows.
	if (File.separatorChar  == '\\') {
	    // On Windows check ignore case....
	    if (!realPath.equals(canPath)){
	    int ls=realPath.lastIndexOf('\\');
	    if ( (ls > 0) && !realPath.substring(0,ls).equals(canPath) )
			return null;
	    }
	}

	// The following code on Non Windows disallows ../
	// in the path but also disallows symlinks....
	//
	// if( ! canPath.startsWith(base) ) {
	// 	// no access to files in a different context.
	//		return null;
	//   }
	// if(!absPath.equals(canPath)) {
	// response.sendError(response.SC_NOT_FOUND);
	// return;
	// }
	// instead lets look for ".." in the absolute path
	// and disallow only that.
	// Why should we loose out on symbolic links?
	//
	
	if(realPath.indexOf("..") != -1) {
	    // We have .. in the path...
	    return null;
	}
	// extra-extra safety check, ( but slow )
	return realPath;
    }

    public static String patch(String path) {
	String patchPath = path;

	// Move drive spec to the front of the path
	if (patchPath.length() >= 3 &&
	    patchPath.charAt(0) == '/'  &&
	    Character.isLetter(patchPath.charAt(1)) &&
	    patchPath.charAt(2) == ':') {
	    patchPath=patchPath.substring(1,3)+"/"+patchPath.substring(3);
	}

	// Eliminate consecutive slashes after the drive spec
	if (patchPath.length() >= 2 &&
	    Character.isLetter(patchPath.charAt(0)) &&
	    patchPath.charAt(1) == ':') {
	    char[] ca = patchPath.replace('/', '\\').toCharArray();
	    char c;
	    StringBuffer sb = new StringBuffer();

	    for (int i = 0; i < ca.length; i++) {
		if ((ca[i] != '\\') ||
		    (ca[i] == '\\' &&
		        i > 0 &&
		        ca[i - 1] != '\\')) {
		    if (i == 0 &&
		        Character.isLetter(ca[i]) &&
		        i < ca.length - 1 &&
		        ca[i + 1] == ':') {
		        c = Character.toUpperCase(ca[i]);
		    } else {
		        c = ca[i];
		    }

		    sb.append(c);
		}
	    }

	    patchPath = sb.toString();
	}

	// fix path on NetWare - all '/' become '\\' and remove duplicate '\\'
	if (System.getProperty("os.name").startsWith("NetWare") &&
	    path.length() >=3 &&
	    path.indexOf(':') > 0) {
            char ca[] = patchPath.replace('/', '\\').toCharArray();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < ca.length; i++) {
                if ((ca[i] != '\\') ||
                    (ca[i] == '\\' && i > 0 && ca[i-1] != '\\')) {
                    sb.append(ca[i]);
                }
            }
            patchPath = sb.toString();
        }
	return patchPath;
    }

    public static boolean isAbsolute( String path ) {
	// normal file
	if( path.startsWith("/" ) ) return true;

	if( path.startsWith(File.separator ) ) return true;

	// win c:
	if (path.length() >= 3 &&
	    Character.isLetter(path.charAt(0)) &&
	    path.charAt(1) == ':')
	    return true;

	// NetWare volume:
	if (System.getProperty("os.name").startsWith("NetWare") &&
	    path.length() >=3 &&
	    path.indexOf(':') > 0)
	    return true;

	return false;
    }

    // Used in few places.
    public static String getCanonicalPath(String name ) {
	if( name==null ) return null;
	File f = new File(name);
	try {
	    return  f.getCanonicalPath();
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	    return name; // oh well, we tried...
	}
    }

    /**  method to return the Localized version of the file whose
     *   name is passed as an argument.
     *
     *  The method performs a resource lookup in a manner similar to the
     *  one specified by java.util.ResourceBundle.
     *
     *  In the case of 'typed' files (files whose name is [file].[ftype])
     *  search for localized versions of the file are looked for:
     *
     *   file + "_" + language1 + "_" + country1 + "_" + variant1 + "." + ftype
     *   file + "_" + language1 + "_" + country1 + "." + ftype
     *   file + "_" + language1 + "." + ftype
     *   file + "_" + language2 + "_" + country2 + "_" + variant2 "." + ftype
     *   file + "_" + language2 + "_" + country2 "." + ftype
     *   file + "_" + language2 + "." + ftype
     *   file + "." + ftype
     *
     *  Where language1, country1, variant1 are associated with the Locale
     *  passed as an argument and language2, country2, variant2 are associated
     *  with the default Locale passed as argument.
     *
     *  For example, if the preferred Locale is <CODE>es_AR_POSIX</CODE> and
     *  the default Locale passed is <CODE>fr_CA_WIN</CODE>, and the requested
     *  pathname is <CODE>/foo/bar/index.html</CODE>, then a search for
     *  the following localized versions of that file will be done, in order:
     *<UL>
     *<LI>/foo/bar/index_es_AR_POSIX.html</LI>
     *<LI>/foo/bar/index_es_AR.html</LI>
     *<LI>/foo/bar/index_es_AR.html</LI>
     *<LI>/foo/bar/index_es.html</LI>
     *<LI>/foo/bar/index_fr_CA_WIN.html</LI>
     *<LI>/foo/bar/index_fr_CA.html</LI>
     *<LI>/foo/bar/index_fr.html</LI>
     *<LI>/foo/bar/index.html</LI>
     *</UL>
     *
     *  If the resource passed has no 'ftype' component, then the same
     *  rules above apply, with the exception that '.' + ftype are not
     *  concatenated.
     *
     *  @param path the pathname for the resource whose localized version
     *          we are seeking
     *  @param loc the Locale we are interested in.
     *  @param fbLoc the fallback Locale to use if unsuccessful
     *
     *  @return a String with the path of the "best localized match" for
     *          the file whose path has been passed as argument.
     */
    public static String getLocalizedResource (String path,
		                               Locale loc,
		                               Locale fbLoc)
    {
	String locRes = null;

	if (null != loc)
	    locRes = getLocalizedResource (path, loc);

	if (null != locRes)
	    return locRes;

	if (null != fbLoc)
	    locRes= getLocalizedResource (path, fbLoc);

	if (null != locRes)
	    return locRes;

	return path;
    } 

    /**  utility method to return the name of the localized file whose
     *   name best-matches the Locale passed as an argument
     *
     *  @param base the directory that is the Document Base for the context
     *			in which the 'path' passed is expected to be located
     *  @param path the pathname for the file that is being searched for its
     *			best-matched Localized version
     *  @param loc the requested Locale to match
     *  @param fbloc the fallback Locale if the requested one not found
     *
     *  @return the name of the file that best-matched the Locale requested
     */
    public static String getLocalizedFile (String base,
					   String path,
					   Locale loc,
					   Locale fbloc) {
	
	String locRes = null;

	if (null != loc)
	    locRes = getLocalizedFile (base, path, loc);

	if (null != locRes)
	    return locRes;

	if (null != fbloc)
	    locRes= getLocalizedFile (base, path, fbloc);

	if (null != locRes)
	    return locRes;

	return safePath (base, path);
    }

    /**  utility method to return the name of the localized file whose
     *   name best-matches the Locale passed as an argument using the
     *   'file-based' lookup mechanism.
     *
     *  @param base the directory that is the Document Base for the context
     *			in which the 'path' passed is expected to be located
     *  @param path the pathname for the file that is being searched for its
     *			best-matched Localized version
     *  @param loc the requested Locale to match
     *
     *  @return the name of the file that best-matched the Locale requested
     */
    public static String getLocalizedFile (String base,
					   String path,
					   Locale loc) {
	
	return getLocalizedResource (base, path, loc, true);
    }

    /**  utility method to get the best-match for getting the Localized
     *   version of the file whose 'path' is passed as an argument, using
     *   the 'loc' Locale, and performing 'safePath'checks
     *   The algorithm used matches 'file-based' lookup mechanism.
     *
     *  The method performs a resource lookup in a manner similar to the
     *  one specified by java.util.ResourceBundle.
     *
     *  In the case of 'typed' files (files whose name is [file].[ftype])
     *  search for localized versions of the file are looked for:
     *
     *   file + "_" + language1 + "_" + country1 + "_" + variant1 + "." + ftype
     *   file + "_" + language1 + "_" + country1 + "." + ftype
     *   file + "_" + language1 + "." + ftype
     *   file + "." + ftype
     *
     *  Where language1, country1, variant1 are associated with the Locale
     *  passed as an argument. 
     *
     *  For example, if the preferred Locale is <CODE>es_AR_POSIX</CODE>
     *  pathname is <CODE>/foo/bar/index.html</CODE>, then a search for
     *  the following localized versions of that file will be done, in order:
     *<UL>
     *<LI>/foo/bar/index_es_AR_POSIX.html</LI>
     *<LI>/foo/bar/index_es_AR.html</LI>
     *<LI>/foo/bar/index_es_AR.html</LI>
     *<LI>/foo/bar/index_es.html</LI>
     *</UL>
     *
     *  @param base the document base for the path passed
     *  @param rPath the path to the file relative to the base
     *  @param loc the Locale preferred
     *  @param safechek perform safePath checks
     *
     *  @return the name of the file that best-matched the Locale requested
     *		null, if no localization matching could be done.
     */
    public static String getLocalizedResource (String base, String rPath,
					       Locale loc, boolean safechek) {

	if (null == rPath)
	    return null;

	String path = (safechek ? safePath (base, rPath) : rPath);

	return getLocalizedResource (path, loc);
    }

    /**  utility method to get the best-match for getting the Localized
     *   version of the file whose 'path' is passed as an argument, using
     *   the 'loc' Locale, and performing 'safePath'checks
     *
     *  The method performs a resource lookup in a manner similar to the
     *  one specified by java.util.ResourceBundle.
     * 
     *  See above explanation of lookup order.
     *
     *  @param path the path to the file 
     *  @param loc Locale with which to match.
     *
     *  @return the name of the file that best-matched the Locale requested
     *		null if no Locale match existed. Invokers of the method
     *  	need to test whether null is returned, and then decide
     *		what to do (whether to default to the original path
     *		or not).
     */
    public static String getLocalizedResource (String path, Locale loc) {

	if (null == path)
	    return null;

	String wPath = null;
	String pPath = null;    // parent path
	String gpPath = null;   // grand-parent path

	String rLang = loc.getLanguage();
	String rCoun = loc.getCountry();
	String rVar  = loc.getVariant();

	int    pathLen   = path.length();
	int    lastParen = path.lastIndexOf (File.separator);
	int    lastDot   = path.lastIndexOf ('.');

	//  evaluate the following conditions
	//
	//  a. path passed includes the File.separator
	//	  the path specified is a 'dotted' file if:
	//		lastParen < lastDot
	//
	//  b. path passed has not File.separator
	//	  the path specified is a 'dotted' file if:
	//		-1 < lastDot
	//
	//  c. path is not a dotted file if:
	//		-1 == lastDot
	//
	//
	//  'pathBase' is anything to the left of the 'lastDot' position
	//  (if 'lastDot' is -1, 'pathBase' is 'path')
	//
	String pathBase = null;
	String dotTail  = null;

	if (lastParen < lastDot && lastDot > 0)
	{
	    pathBase = path.substring (0, lastDot);
	    dotTail = path.substring (lastDot);
	}
	else
	    pathBase = path;
	    
	File file = null;

	gpPath = pathBase + '_' + rLang;

	//  first, try the language+country+variant match
	//
	if (null != rCoun && ! ("".equals (rCoun)) ) {

	    pPath = gpPath + '_' + rCoun;
	    if (null != rVar && ! ("".equals (rVar)) ) {

		wPath = pPath + '_' + rVar;

		//  append the dotTail if necessary
		//
		if (null != dotTail)
		    wPath += dotTail;

		// perform existence test
		//
		if ((new File(wPath).exists()))
		    return wPath;
	    }

	    //  append the dotTail if necessary
	    //
	    if (null != dotTail)
		pPath += dotTail;

	    if ((new File(pPath)).exists())
		return pPath;
	}

	//  append the dotTail if necessary
	//
	if (null != dotTail)
	    gpPath += dotTail;

	if ((new File(gpPath)).exists())
	    return gpPath;

	return null;
    }

    /**  utility method to return the name of the localized file whose
     *   name best-matches the Locale passed as an argument using the
     *   'docbase-based' lookup mechanism.
     *
     *  @param base the directory that is the Document Base for the context
     *			in which the 'path' passed is expected to be located
     *  @param path the pathname for the file that is being searched for its
     *			best-matched Localized version
     *  @param loc the requested Locale to match
     *  @param fbloc the requested fall-back Locale to match
     *
     *  @return the name of the file that best-matched the Locale requested
     */
    public static String getDocBaseLocalizedFile (String base,
					   String path,
					   Locale loc,
					   Locale fbloc) {
	return getDocBaseLocalizedResource (base, path, loc, fbloc);
    }

    /**  utility method to get the best-match for getting the Localized
     *   version of the file whose 'path' is passed as an argument, using
     *   the 'loc' Locale, and performing 'safePath'checks
     *   The algorithm used matches 'docBase-based' lookup mechanism.
     *
     *  In the case of 'typed' files (files whose name is [file].[ftype])
     *  search for localized versions of the file are looked for:
     *
     *   docbase + "/" + language1 + "_" + country1 + "_" + variant1 + filepath 
     *   docbase + "/" + language1 + "_" + country1 + filepath 
     *   docbase + "/" + language1 + filepath 
     *
     *  Where language1, country1, variant1 are associated with the Locale
     *  passed as an argument. 
     *
     *  For example, if the preferred Locale is <CODE>es_AR_POSIX</CODE>
     *  the docBase is '/' and  pathname is <CODE>/foo/bar/index.html</CODE>,
     *  then a search for the following localized versions of that file will
     *  be done, in order:
     *<UL>
     *<LI>/es_AR_POSIX/foo/bar/index.html</LI>
     *<LI>/es_AR/foo/bar/index.html</LI>
     *<LI>/es/foo/bar/index.html</LI>
     *<LI>/foo/bar/index_es.html</LI>
     *</UL>
     *
     *  @param base the directory that is the Document Base for the context
     *			in which the 'path' passed is expected to be located
     *  @param path the pathname for the file that is being searched for its
     *			best-matched Localized version
     *  @param loc the requested Locale to match
     *  @param fbloc the fallback Locale if the requested one not found
     *
     *  @return the name of the file that best-matched the Locale requested
     */
    public static String getDocBaseLocalizedResource (String base,
					              String path,
					              Locale loc,
					              Locale fbloc) {
	
	String locRes = null;

	//  test first for safePath
	//
	locRes = safePath (base, path);

	//  if it was not safe as requested, it will not be safe after
	//  localization lookup. Localization name transformations do not
	//  affect the safety of the file.
	//
	if (null == locRes)
	    return null;

	if (null != loc)
	    locRes = getDocBaseLocalizedPath (base, path, loc);

	if (null != locRes)
	    return locRes;

	if (null != fbloc)
	    locRes= getDocBaseLocalizedPath (base, path, fbloc);

	return locRes;
    }

    /**  internal method to check the existence of a file
     *
     *  @param base the docbase where the file is
     *  @param info the pathinfo component under the docbase
     *
     *  @return a TestedFile object for that base+info
     */
    private static TestedFile checkFile (String base, String info)
    {
	return new TestedFile (base, info);
    }

    /**  method to perform docBase localization
     *
     *  The method performs a resource lookup in a manner similar to the
     *  one performed by JavaHelp.
     * 
     *  See above explanation of lookup order.
     *
     *  @param dBase the document base for the context of the document
     *  @param path the path to the document
     *  @param loc the Locale from the request
     *
     *  @return the String for the localized path
     */
    private static String getDocBaseLocalizedPath (String dBase, String path,
						   Locale loc)
    {
	String rLang = loc.getLanguage();
	String rCoun = loc.getCountry();
	String rVar  = loc.getVariant();
	String ldBase = dBase.endsWith(File.separator)
			    		? dBase
			    		: (dBase + File.separatorChar);

	TestedFile tf = null;

	//  first, try with the provided language+country+variant
	//
	String base = null;

	if (null != rVar && ! ("".equals (rVar)) )
	{
	    base = ldBase + rLang + '_' + rCoun + '_' + rVar;

	    if ((tf = checkFile(base, path)).getFile().exists())
		return tf.getPath();
	}

	//  then, try with the provided language+country
	//
	if (null != rCoun && ! ("".equals (rCoun)) )
	{
	    base = ldBase + rLang + '_' + rCoun;

	    if ((tf = checkFile(base, path)).getFile().exists())
		return tf.getPath();
	}

	//  now, try with the provided language only
	//
	base = ldBase + rLang;

	if ((tf = checkFile(base, path)).getFile().exists())
	    return tf.getPath();

	return checkFile(dBase, path).getPath();
    }

    static String concatPath( String s1, String s2 )
    {
        if( s1.endsWith( File.separator ) ) {
            if( s2.startsWith( File.separator ))
                return s1 + s2.substring(1);
            else
                return s1 + s2;
        } else {
            if( s2.startsWith(File.separator))
                return s1 + s2;
            else
                return s1 + File.separatorChar + s2;
        }
    }

    /**  inner class to perform file tests
     */
    public static class TestedFile {

	File	file = null;
	String	path = null;

	/**  class to return the combination of File and pathname
	 *
	 *  @param base the directory name for the document base 
	 *  @param path the pathname for the file under the document base
	 */
	public TestedFile (String base, String path)
	{
	    if ((path == null) || (path.length() < 1))
		this.path = base;
	    else
		this.path = concatPath (base, path);

	    file = new File (this.path);
	}

	/**  method to obtain the path for the file being tested
	 *
	 *  @return a String with the path for the file associated
	 */
	public String getPath ()
	{
	    return path;
	}

	/**  method to obtain the File for the path being tested
	 *
	 *  @return a File corresponding to the path passed.
	 */
	public File getFile ()
	{
	    return file;
	}
    }
}
