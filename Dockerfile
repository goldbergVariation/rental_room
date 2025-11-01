# 1. Maven + Java21でビルド
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 2. Tomcatにデプロイ
FROM tomcat:10.1-jdk21
COPY --from=build /app/target/rental_room.war /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]
