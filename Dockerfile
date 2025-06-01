# Etapa de build
FROM maven:3.9.6-openjdk-17 AS build
WORKDIR /app

# Copia apenas os arquivos necessários primeiro (melhor uso de cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o restante do código
COPY . .

# Compila o projeto (sem testes para build mais rápido)
RUN mvn clean install -DskipTests

# Etapa de execução
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copia o jar da etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta usada pela aplicação
EXPOSE 8080

# Inicia a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
