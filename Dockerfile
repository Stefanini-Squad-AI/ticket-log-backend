FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copiar archivos de configuración de Gradle
COPY gradlew gradlew.bat ./
COPY gradle/ gradle/
COPY build.gradle settings.gradle ./

# Dar permisos de ejecución al wrapper de Gradle
RUN chmod +x gradlew

# Copiar código fuente
COPY src/ src/

# Compilar la aplicación (sin ejecutar tests)
RUN ./gradlew build --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiar el JAR compilado
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
