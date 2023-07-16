# Usar una imagen base con JDK 20 y Maven 3.1.0
FROM maven:3.9.3-eclipse-temurin-20 AS build

# Establecer un directorio de trabajo
WORKDIR /app

# Copiar archivos de tu proyecto al directorio de trabajo
COPY . /app

# Ejecutar Maven para construir el proyecto
RUN mvn clean package

# Crear una nueva imagen basada en OpenJDK 20
FROM openjdk:20-jdk-slim

# Exponer el puerto que utilizará la aplicación
EXPOSE 8080

# Copiar el archivo JAR construido desde la etapa anterior
COPY --from=build /app/target/ClinicaOdontologica-0.0.1-SNAPSHOT.jar /app/ClinicaOdontologica-0.0.1-SNAPSHOT.jar

# Establecer el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/ClinicaOdontologica-0.0.1-SNAPSHOT.jar"]