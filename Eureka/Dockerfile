# Start with a base image containing Java runtime
FROM openjdk:17-jdk

# Expose the application port
EXPOSE 8761

# The application's jar file
ARG JAR_FILE=build/libs/Eureka-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]