# Base image olarak OpenJDK 17 kullan
FROM openjdk:17-jdk-slim

# Uygulamanın çalışacağı dizini oluştur
WORKDIR /app

# Maven'in target klasöründen jar dosyasını image'e kopyala
COPY target/ozgur-yazilim-0.0.1-SNAPSHOT.jar /app/app.jar

# Uygulamanın çalışacağı portu belirt
EXPOSE 8081

# Uygulamayı başlat
ENTRYPOINT ["java", "-jar", "app.jar"]
