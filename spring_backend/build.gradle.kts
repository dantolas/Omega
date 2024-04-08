plugins{
    application
    java
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
}


application {
    mainClass = "com.kuta.Main"
}


repositories{
    mavenCentral()
    gradlePluginPortal()
}

dependencies{
    implementation("com.google.code.gson:gson:2.10.1")
    implementation ("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.session:spring-session-core")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("mysql:mysql-connector-java:8.0.33")
}

dependencyManagement {
  imports {
      mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
  }
}

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

tasks {
    withType<JavaCompile>{
        options.compilerArgs.add("-parameters")
    }
    bootJar{
        archiveBaseName.set("boot-app")
        destinationDirectory.set(file("./"))
    }
}

// Pass default system input to gradle run (Default input stream)
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

