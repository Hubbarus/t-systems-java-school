#Dockerfile

FROM tomcat:9.0.39-jdk11-corretto

MAINTAINER "Paul Ponomarev"

COPY target/mms-project.war /usr/local/tomcat/webapps/ROOT.war
