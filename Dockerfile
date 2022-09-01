#FROM openjdk:15
#
#COPY *.jar /app.jar
#
#CMD ["--server.port=8080"]
#
#EXPOSE 8080
#
#ENTRYPOINT ["java","-jar", "/app.jar"]


FROM openjdk:15

COPY *.jar /app.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod", "/app.jar"]