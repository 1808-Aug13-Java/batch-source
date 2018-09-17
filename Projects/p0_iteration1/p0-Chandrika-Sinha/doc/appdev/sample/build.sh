#!/bin/sh
# build.sh -- Build Script for the "Hello, World" Application
# $Id: build.sh,v 1.1.2.2 2000/11/27 22:45:59 craigmcc Exp $

# Identify the custom class path components we need
CP=$TOMCAT_HOME/lib/ant.jar:$TOMCAT_HOME/lib/servlet.jar
CP=$CP:$TOMCAT_HOME/lib/jaxp.jar:$TOMCAT_HOME/lib/parser.jar
CP=$CP:$JAVA_HOME/lib/tools.jar

# Execute ANT to perform the requested build target
java -classpath $CP:$CLASSPATH org.apache.tools.ant.Main \
  -Dtomcat.home=$TOMCAT_HOME "$@"
