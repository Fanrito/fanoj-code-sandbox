# Docker 镜像构建
# @author <a href="https://github.com/Fanrito">范奇森</a>
FROM maven:3.8.1-jdk-8-slim as builder

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn package -DskipTests

# Run the web service on container startup.
CMD ["java","-jar","/app/target/fanoj-code-sandbox-0.0.1-SNAPSHOT.jar"]