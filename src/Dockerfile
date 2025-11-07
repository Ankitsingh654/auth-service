# Step 1: Use a lightweight, official Java 17 image (replacement for openjdk)
FROM eclipse-temurin:17-jdk

# Step 2: Set working directory inside the container
WORKDIR /app

# Step 3: Copy the Spring Boot JAR file into the container
COPY target/auth-service-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose the port your Spring Boot app runs on
EXPOSE 8081

# Step 5: Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
