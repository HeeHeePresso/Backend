import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
    id("org.jetbrains.kotlin.plugin.spring") version "1.9.22"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.9.22"

    // mapstruct
    kotlin("kapt") version "1.9.10"
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}

group = "org.heeheepresso"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}


val kotestVersion = "4.4.3"
val grpcSpringBootStarterVersion = "4.4.5"

dependencies {
    // grpc
    api(project(":order-grpc"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // test container
    testImplementation("org.testcontainers:junit-jupiter:1.16.3")
    testImplementation("org.testcontainers:mysql:1.16.3")

    // mapstruct
    implementation("org.mapstruct:mapstruct:1.5.2.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.2.Final")

    // Kotest
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    implementation("io.kotest:kotest-extensions-spring:$kotestVersion")
    testImplementation("io.mockk:mockk:1.13.4")

    // QueryDSL
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
    kapt("jakarta.annotation:jakarta.annotation-api")
    kapt("jakarta.persistence:jakarta.persistence-api")

    // grpc
    implementation("net.devh:grpc-server-spring-boot-starter:2.12.0.RELEASE")

    // logging
    implementation("io.github.oshai:kotlin-logging-jvm:5.1.1")

    // jasypt (설정파일 암호화)
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
}

val testContainerVersion by extra {
    "1.15.1"
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:$testContainerVersion")
    }
}
