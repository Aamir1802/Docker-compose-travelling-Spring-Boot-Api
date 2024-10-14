FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY target/spring-boot-mysql-travelling-api.jar spring-boot-mysql-travelling-api.jar
ENTRYPOINT ["java","-jar","/spring-boot-mysql-travelling-api.jar"]
#spring-boot-mysql-travelling-api.jar is   from <finalname> tag in Pom.xml