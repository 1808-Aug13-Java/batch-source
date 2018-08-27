/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/util/SessionIdGenerator.java,v 1.3.2.4 2001/09/05 02:42:14 marcsaeg Exp $
 * $Revision: 1.3.2.4 $
 * $Date: 2001/09/05 02:42:14 $
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

/**
 * This class generates a unique 10+ character id. This is good
 * for authenticating users or tracking users around.
 * <p>
 * This code was borrowed from Apache JServ.JServServletManager.java.
 * It is what Apache JServ uses to generate session ids for users.
 * Unfortunately, it was not included in Apache JServ as a class
 * so I had to create one here in order to use it.
 *
 * @author James Duncan Davidson [duncan@eng.sun.com]
 * @author Jason Hunter [jhunter@acm.org]
 * @author Jon S. Stevens <a href="mailto:jon@latchkey.com">jon@latchkey.com</a>
 * @author Shai Fultheim [shai@brm.com]
 */
public class SessionIdGenerator {

    /*
     * Create a suitable string for session identification
     * Use synchronized count and time to ensure uniqueness.
     * Use random string to ensure timestamp cannot be guessed
     * by programmed attack.
     *
     * format of id is <6 chars random><3 chars time><1+ char count>
     */
    static private int session_count = 0;
    static private long lastTimeVal = 0;
    static private java.util.Random randomSource;

    // MAX_RADIX is 36
    /*
     * we want to have a random string with a length of
     * 6 characters. Since we encode it BASE 36, we've to
     * modulo it with the following value:
     */
    public final static long maxRandomLen = 2176782336L; // 36 ** 6

    /*
     * The session identifier must be unique within the typical lifespan
     * of a Session, the value can roll over after that. 3 characters:
     * (this means a roll over after over an day which is much larger
     *  than a typical lifespan)
     */
    public final static long maxSessionLifespanTics = 46656; // 36 ** 3

    /*
     *  millisecons between different tics. So this means that the
     *  3-character time string has a new value every 2 seconds:
     */
    public final static long ticDifference = 2000;

    /**
     * A String initialization parameter used to increase the entropy of
     * the initialization of our random number generator.
     */
    private static String entropy = null;


    /**
     * Return the entropy increaser value, or compute a semi-useful value
     * if this String has not yet been set.
     */
    public static String getEntropy()
    {
        return (entropy);
    }

    /**
     * Set the entropy increaser value.
     *
     * @param entropy The new entropy increaser value
     */
    public static void setEntropy(String newEntropy)
    {
        entropy = newEntropy;
    }

    public static void initialize()
    {
        if (randomSource == null) {
            String className = System.getProperty("tomcat.sessionid.randomclass");
            if (className != null) {
                try {
                    Class randomClass = Class.forName(className);
                    randomSource = (java.util.Random)randomClass.newInstance();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (randomSource == null)
                randomSource = new java.security.SecureRandom();

            String entropyValue = getEntropy();
            if(entropyValue != null){
                /*
                 *  We only do the manual seed generation if there is a user
                 * supplied entropy value.  This is only for backwards 
                 * compatibility.  It is expected that very few people
                 * ever took advantage of this feature and defaulting
                 * to the normal PRNG seed generator is more secure than this 
                 * calculation.
                 */
                long seed = System.currentTimeMillis();
                char entropy[] = entropyValue.toCharArray();
                for (int i = 0; i < entropy.length; i++) {
                    long update = ((byte) entropy[i]) << ((i % 8) * 8);
                    seed ^= update;		    
                }
                randomSource.setSeed(seed);
            }else{
                randomSource.nextInt();
            }
        }
    }

	 // ** NOTE that this must work together with get_jserv_session_balance()
    // ** in jserv_balance.c
    static synchronized public String getIdentifier (String jsIdent)
    {
        StringBuffer sessionId = new StringBuffer();
        
        initialize();

        // random value ..
        long n = randomSource.nextLong();
        if (n < 0) n = -n;
        n %= maxRandomLen;
        // add maxLen to pad the leading characters with '0'; remove
        // first digit with substring.
        n += maxRandomLen;
        sessionId.append (Long.toString(n, Character.MAX_RADIX)
                  .substring(1));

        long timeVal = (System.currentTimeMillis() / ticDifference);
        // cut..
        timeVal %= maxSessionLifespanTics;
        // padding, see above
        timeVal += maxSessionLifespanTics;

        sessionId.append (Long.toString (timeVal, Character.MAX_RADIX)
                  .substring(1));

        /*
         * make the string unique: append the session count since last
         * time flip.
         */
        // count sessions only within tics. So the 'real' session count
        // isn't exposed to the public ..
        if (lastTimeVal != timeVal) {
          lastTimeVal = timeVal;
          session_count = 0;
        }
        sessionId.append (Long.toString (++session_count,
                     Character.MAX_RADIX));

        if (jsIdent != null && jsIdent.length() > 0) {
            return sessionId.toString()+"."+jsIdent;
        }
        return sessionId.toString();
    }

    public static synchronized String generateId(String jsIdent) {
        return getIdentifier(jsIdent);
    }
}
