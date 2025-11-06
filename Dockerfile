# Build stage
FROM maven:3.9-eclipse-temurin-8 AS build

WORKDIR /app
COPY pom.xml .
# Download dependencies first (faster builds)
RUN mvn -B -q dependency:go-offline

COPY . .
RUN mvn -B -q package -DskipTests

# Run stage
FROM eclipse-temurin:8-jre
WORKDIR /app

# Copy the generated jar (whatever its exact name)
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
