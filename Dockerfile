FROM maven:3.6.3-openjdk-15 AS build
COPY src /individualproject/src
COPY pom.xml /individualproject
RUN mvn -f /individualproject/pom.xml clean
RUN mvn -f /individualproject/pom.xml install

#
# Package stage
#
FROM openjdk:15
COPY --from=build /individualproject/target/hobbybook-app.jar hobbybook-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","hobbybook-app.jar"]


