# Compilación con Maven
FROM maven:3.8.6-eclipse-temurin-8 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn -e dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests -B

# Ejecución
FROM eclipse-temurin:8-jre

WORKDIR /app

COPY --from=build /app/target/productos-0.0.1-SNAPSHOT.war app.war

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.war"]
