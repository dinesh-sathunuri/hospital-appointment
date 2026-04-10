FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src ./src
RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/Hospital-0.0.1-SNAPSHOT.jar"]