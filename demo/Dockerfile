# Usar uma imagem base com Java 11
FROM openjdk:11-jdk

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo JAR para dentro do container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
