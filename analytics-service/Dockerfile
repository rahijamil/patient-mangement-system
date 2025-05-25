# ---- Builder with Java 24 and Maven ----
FROM eclipse-temurin:24-jdk AS builder

# Install Maven manually
RUN apt-get update && \
    apt-get install -y maven && \
    mvn -version

WORKDIR /app

COPY pom.xml .

COPY src ./src
RUN mvn clean package -DskipTests -X

# ---- Runtime with Java 24 only ----
FROM eclipse-temurin:24-jdk AS runner

WORKDIR /app

COPY --from=builder /app/target/analytics-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 4002

ENTRYPOINT ["java", "-jar", "app.jar"]
