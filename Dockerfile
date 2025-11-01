# Java21対応のTomcat公式イメージを使う
FROM tomcat:10.1-jdk21

# WARファイルをTomcatのwebappsにコピー
COPY target/rental_room.war /usr/local/tomcat/webapps/

# Tomcat起動
CMD ["catalina.sh", "run"]

