import com.google.protobuf.gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
    id("com.google.protobuf") version "0.8.18"
    idea
}

group = "org.example"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}


val grpcVersion = "3.19.3"
val grpcKotlinVersion = "1.2.1"
val grpcProtoVersion = "1.45.1"

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    api("io.grpc:grpc-stub:$grpcProtoVersion")
    api("io.grpc:grpc-protobuf:$grpcProtoVersion")
    api("io.grpc:grpc-kotlin-stub:1.2.0")
    api("io.grpc:grpc-netty-shaded:$grpcProtoVersion")
    api("com.google.protobuf:protobuf-kotlin:$grpcVersion")

    api("jakarta.annotation:jakarta.annotation-api:1.3.5")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$grpcVersion"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpcProtoVersion"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:$grpcKotlinVersion:jdk7@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
        }
    }
}