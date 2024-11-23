# Usar a imagem base Amazon Corretto com Java 17
FROM amazoncorretto:17

# Definir a localização do arquivo JAR gerado pelo Gradle
ARG JAR_FILE=build/libs/*.jar

# Copiar o arquivo JAR para dentro do contêiner
COPY ${JAR_FILE} app.jar

# Executar um comando bash para tocar (criar) o arquivo app.jar
RUN bash -c 'touch /app.jar'

# Definir o ponto de entrada do contêiner para executar o arquivo JAR
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
