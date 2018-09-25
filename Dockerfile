FROM openjdk:8-alpine
MAINTAINER Vasilij Melnychuk <vasilij@melnychuk.me>

COPY target/telog.jar telog.jar

EXPOSE 8080

CMD ["java", "-jar", "telog.jar", "-p", "8080"]
