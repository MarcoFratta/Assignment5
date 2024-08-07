import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    java
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
extra["springCloudVersion"] = "2023.0.2"
java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("io.micrometer:micrometer-registry-prometheus")
    // Validation API
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-tracing-bridge-brave")
    // https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-ui
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
    // https://mvnrepository.com/artifact/com.github.piomin/logstash-logging-spring-boot-starter
    implementation("com.github.piomin:logstash-logging-spring-boot-starter:1.2.2.RELEASE")
    // https://mvnrepository.com/artifact/net.logstash.logback/logstash-logback-encoder
    implementation("net.logstash.logback:logstash-logback-encoder:7.4")
    //zipkin
    implementation("io.zipkin.reporter2:zipkin-reporter-brave")

}


springBoot {
    // Customize the mainClassName if needed (replace 'com.example.Application' with your main class)
    mainClass.set("com.escooter.EScooterManagementSystem")
}
tasks.bootJar {
    archiveVersion.set("") // Empty string to remove the version
    archiveClassifier.set("") // Empty string to remove the classifier (e.g., 'SNAPSHOT')
    archiveBaseName.set("rideService") // Replace 'your-desired-name' with your preferred name
    // Optionally, if you want to include the version in the name:
    // archiveFileName.set("${archiveBaseName.get()}-${project.version}")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}


tasks.withType<Test> {
    useJUnitPlatform()
}
