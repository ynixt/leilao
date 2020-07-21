FROM gradle:6.4.1-jdk14 as builder
WORKDIR /api

COPY . .
RUN gradle bootJar

ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/api/build/libs/leilao.jar"]
