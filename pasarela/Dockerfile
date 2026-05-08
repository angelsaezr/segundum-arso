FROM maven:3.8.6-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn package

FROM eclipse-temurin:8-jre
WORKDIR /app
COPY --from=build /app/target/*.war app.war
EXPOSE 8090
CMD ["java", "-jar", "/app/app.war"]