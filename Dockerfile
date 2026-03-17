FROM eclipse-temurin:17-jdk-alpine
COPY components/services/implementation/build/libs/booking-service-implementation-1.0.jar /booking-service-implementation.jar
ENTRYPOINT ["java", "-jar","/booking-service-implementation.jar"]