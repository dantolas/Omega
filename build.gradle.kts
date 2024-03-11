plugins{
    application
    java
    id ("com.github.johnrengelman.shadow") version "8.1.1"
}


application {
    mainClass = "com.kuta.Main"
}


repositories{
    mavenCentral()
    gradlePluginPortal()
}

dependencies{
    implementation("mysql:mysql-connector-java:8.0.33")
     implementation("com.google.code.gson:gson:2.10.1")
}

tasks {
  withType<Jar> {
        manifest {
            attributes["Main-Class"] = "com.kuta.Main" 
        }
        configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    }

    shadowJar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveBaseName.set("postman")
    destinationDirectory.set(File("./"))
    mergeServiceFiles()
    manifest {
        attributes("Main-Class" to "com.kuta.Main")
    }
  }
}

// Pass default system input to gradle run (Default input stream)
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

