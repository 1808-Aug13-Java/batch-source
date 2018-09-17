@echo off
rem build.bat -- Build Script for the "Hello, World" Application
rem $Id: build.bat,v 1.3.2.3 2000/12/27 17:20:11 marcsaeg Exp $

set _CP=%CP%

rem Identify the custom class path components we need
set CP=%TOMCAT_HOME%\lib\ant.jar;%TOMCAT_HOME%\lib\servlet.jar
set CP=%CP%;%TOMCAT_HOME%\lib\jaxp.jar;%TOMCAT_HOME%\lib\parser.jar
set CP=%CP%;%JAVA_HOME%\lib\tools.jar

rem Execute ANT to perform the requird build target
java -classpath %CP%;%CLASSPATH% org.apache.tools.ant.Main -Dtomcat.home=%TOMCAT_HOME% %1 %2 %3 %4 %5 %6 %7 %8 %9

set CP=%_CP%
set _CP=
