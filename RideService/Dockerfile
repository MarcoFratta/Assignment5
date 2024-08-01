# Use a base image with Java and Maven installed
FROM openjdk:17-jdk
# Set the working directory in the container
WORKDIR /app
# Copy the JAR file into the container
COPY build/libs/rideService.jar /app/rideService.jar

# Define the command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "rideService.jar"]