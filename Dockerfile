FROM openjdk:11

RUN apt-get update
RUN apt -y install maven

RUN mkdir -p /usr/app

ADD ./target ./usr/app
ADD pom.xml ./usr/app
WORKDIR /usr/app
