FROM openjdk:22-jdk
WORKDIR /app
ADD target/securityproj.jar /app/securityproj.jar
ENTRYPOINT ["java","-jar","securityproj.jar"]