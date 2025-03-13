# Fase de construcción
FROM maven:3.9.1-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests

# Fase de ejecución
FROM eclipse-temurin:17-jre-alpine  # Usa JRE en lugar de JDK para reducir el tamaño
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

