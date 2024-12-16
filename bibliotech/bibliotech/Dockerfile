# Dockerfile
FROM openjdk:21-jdk-slim

# Instala o Maven
RUN apt-get update && apt-get install -y maven

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo pom.xml e as dependências
COPY pom.xml .

# Baixa as dependências do projeto (para otimizar cache)
RUN mvn dependency:go-offline

# Copia o código-fonte
COPY src ./src

COPY target/*.jar app.jar

# Compila o projeto e gera o JAR
RUN mvn clean package

# Define o ponto de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
