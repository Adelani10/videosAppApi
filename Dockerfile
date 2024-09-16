FROM maven:3.9.8-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21
COPY --from=build /target/videos-0.0.1-SNAPSHOT.jar videos.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]