FROM maven:3.8.6-openjdk-8

WORKDIR /app

COPY pom.xml .
RUN mvn -B dependency:resolve

COPY src ./src

EXPOSE 8080

CMD ["mvn", "jetty:run"]