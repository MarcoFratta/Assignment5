FROM eclipse-temurin:17-jre-focal
WORKDIR /home/app
COPY --link /build/docker/main/layers/libs /home/app/libs
COPY --link /build/docker/main/layers/app /home/app/
COPY --link /build/docker/main/layers/resources /home/app/resources

ENTRYPOINT ["java", "-jar", "/home/app/application.jar"]
