# Stage 1: Build the Kotlin application
FROM gradle:8.4-jdk17 AS build

WORKDIR /app

# Copia tudo para garantir que os arquivos necessários estejam disponíveis
COPY . .

# Executa o build ignorando os testes
#RUN ./gradlew clean build -x test --no-daemon
RUN chmod +x gradlew && ./gradlew clean build -x test --no-daemon

# Stage 2: Runtime
FROM eclipse-temurin:17-jre
#openjdk:17-jre-slim

WORKDIR /app

# Copia o jar gerado
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
