FROM maven:3.9.11-eclipse-temurin-25-noble AS build

RUN apt-get update && apt-get install -y git

WORKDIR /app
COPY pom.xml .

RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:25-jdk-jammy
RUN apt-get update && apt-get install -y curl

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]

