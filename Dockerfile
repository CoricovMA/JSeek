FROM openjdk:11

RUN mkdir -p /usr/app

ADD ./target/JSeek-1.0-SNAPSHOT.jar ./usr/app

WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "JSeek-1.0-SNAPSHOT.jar"]