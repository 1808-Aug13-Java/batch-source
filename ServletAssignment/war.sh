#!/usr/bin/env bash
SERVLET_NAME="heyo"
WEBAPPS="webapps"
BIN="bin"
CATALINA_HOME="/home/chat/tarballs/apache-tomcat-9.0.11"

#shutdown
"${CATALINA_HOME}/${BIN}/shutdown.sh"
rm -rf "${CATALINA_HOME}/${WEBAPPS}/${SERVLET_NAME}"
rm -rf "${CATALINA_HOME}/${WEBAPPS}/${SERVLET_NAME}.war"
#start
"${CATALINA_HOME}/${BIN}/startup.sh"
mvn tomcat7:deploy
