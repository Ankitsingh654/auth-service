# ------------ STAGE 1: Build the JAR using Maven ------------
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy pom.xml and download dependencies first (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# ------------ STAGE 2: Run the built JAR ------------
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy JAR file from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose your Spring Boot app port
EXPOSE 8081

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
