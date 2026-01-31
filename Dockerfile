# ETAPA 1: BUILD (Compilación)
# Usamos una imagen con Maven y Java 21 para compilar el proyecto
FROM maven:3.9-eclipse-temurin-21-alpine AS builder

WORKDIR /app
COPY pom.xml .
COPY src ./src

# Empaqueta el proyecto saltando los tests para ir rápido ahora
RUN mvn clean package -DskipTests

# ETAPA 2: RUNTIME (Ejecución)
# Usamos una imagen muy ligera solo con Java (sin Maven) para correr la app
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiamos solo el archivo .jar generado en la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Exponemos el puerto 8080
EXPOSE 8080

# Comando para arrancar la app
ENTRYPOINT ["java", "-jar", "app.jar"]