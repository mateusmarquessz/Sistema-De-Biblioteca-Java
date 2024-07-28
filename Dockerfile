# Use a imagem do Maven para construir o aplicativo com JDK 17
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copie os arquivos pom.xml e as dependências para o diretório de trabalho
COPY pom.xml .
RUN mvn dependency:go-offline

# Copie o código-fonte do projeto
COPY src ./src

# Construa o projeto, isso criará o arquivo JAR na pasta target
RUN mvn clean package -DskipTests

# Use a imagem base do OpenJDK 21 para a execução
FROM openjdk:21-jdk-slim

# Defina o diretório de trabalho
WORKDIR /app

# Copie o JAR construído do estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Exponha a porta em que o aplicativo será executado
EXPOSE 8080

# Comando para executar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]
