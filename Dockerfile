# Dockerfile

# Используйте официальный образ OpenJDK
FROM openjdk:17-jdk-alpine

# Задайте рабочую директорию в контейнере
WORKDIR /app

# Копируйте jar-файл в контейнер
COPY target/t1_project-0.0.1-SNAPSHOT.jar app.jar

# Запустите приложение
ENTRYPOINT ["java", "-jar", "app.jar"]
