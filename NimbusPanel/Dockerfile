FROM maven:3.9-eclipse-temurin-17 AS build

# Setting the working directory inside the container
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

# Now copy the rest of the source code in
COPY src ./src

# Building the fat JAR inside the container
RUN mvn clean package -DskipTests

# Start fresh FROM a SLIM image with only the Java 17 runtime — no Maven, no JDK build tools
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/NimbusPanel-0.0.1-SNAPSHOT.jar app.jar

# App listens on 8080 
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]