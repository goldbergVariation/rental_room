# ---- 1. Mavenでビルド ----
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ---- 2. Tomcatでデプロイ ----
FROM tomcat:10.1-jdk21
COPY --from=build /app/target/rental_room-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/rental_room.war
CMD ["catalina.sh", "run"]