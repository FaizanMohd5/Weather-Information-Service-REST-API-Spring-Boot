# ---- Build Stage ----
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ---- Runtime Stage ----
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/weather-service-0.0.1-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
