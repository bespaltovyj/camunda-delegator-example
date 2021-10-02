plugins {
    kotlin("jvm") version "1.5.31"

    kotlin("plugin.spring") version "1.5.31"

    id("org.springframework.boot") version "2.5.2"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
}

group = "io.github.bespaltovyj"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter-webapp:7.14.0")
    implementation("ru.tinkoff.top:camunda-delegator-spring-boot-starter:5.0.8")

    implementation("org.springdoc:springdoc-openapi-ui:1.5.10")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.5.10")

    implementation("ch.qos.logback:logback-core:1.2.6")
    implementation("ch.qos.logback:logback-classic:1.2.6")
    implementation("ch.qos.logback:logback-access:1.2.6")
    implementation("net.logstash.logback:logstash-logback-encoder:6.6")

    implementation("com.h2database:h2")


    testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")
    testImplementation("org.junit.vintage:junit-vintage-engine:5.8.1")
    testImplementation("ru.tinkoff.top:camunda-delegator-test:5.0.8")
    testImplementation("org.camunda.bpm.assert:camunda-bpm-assert:10.0.0")
    testImplementation("org.camunda.bpm.extension:camunda-bpm-process-test-coverage:0.4.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.2")

}

tasks {
    test {
        systemProperty(
            "camunda-bpm-process-test-coverage.target-dir-root",
            "build/process-test-coverage/"
        )
        useJUnitPlatform {
            includeEngines("junit-vintage", "junit-jupiter")
        }
    }
}
