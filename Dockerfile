FROM eclipse-temurin:17-alpine
COPY build/libs/alpha-feign-currency-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]