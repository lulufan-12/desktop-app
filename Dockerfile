FROM maven:3.8.6-jdk-11 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:11-jre
COPY --from=build /home/app/target/application-0.0.1-SNAPSHOT-jar-with-dependencies.jar /usr/local/lib/app.jar
ENV DB_URL=jdbc:postgresql://localhost:5432/application
ENV DB_USER=postgres
ENV DB_PASSWORD=54325432
ENV PERSISTENCE_UNIT=persistence
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]