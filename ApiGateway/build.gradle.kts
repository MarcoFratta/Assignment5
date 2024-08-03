plugins {
	java
	id("org.springframework.boot") version "3.3.1"
	id("io.spring.dependency-management") version "1.1.5"
}

group = "org.unibo"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2023.0.2"

dependencies {
	implementation("org.springframework.cloud:spring-cloud-starter-gateway")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("io.micrometer:micrometer-registry-prometheus")
	implementation("io.micrometer:micrometer-tracing-bridge-brave")
	// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-ui
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
	// https://mvnrepository.com/artifact/com.github.piomin/logstash-logging-spring-boot-starter
	implementation("com.github.piomin:logstash-logging-spring-boot-starter:1.2.2.RELEASE")
	// https://mvnrepository.com/artifact/net.logstash.logback/logstash-logback-encoder
	implementation("net.logstash.logback:logstash-logback-encoder:7.4")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}
tasks.bootJar {
	archiveVersion.set("") // Empty string to remove the version
	archiveClassifier.set("") // Empty string to remove the classifier (e.g., 'SNAPSHOT')
	archiveBaseName.set("apiGateway") // Replace 'your-desired-name' with your preferred name
	// Optionally, if you want to include the version in the name:
	// archiveFileName.set("${archiveBaseName.get()}-${project.version}")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
