FROM openjdk:17-slim AS builder
WORKDIR /app

COPY . .
RUN chmod +x ./gradlew
RUN apt-get update && apt-get install -y coreutils
RUN ./gradlew bootJar -x test

# -------------------------------------------

FROM openjdk:17-slim
WORKDIR /opt/app

COPY --from=builder /app/build/libs/*.jar ./spring-boot-application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "spring-boot-application.jar"]