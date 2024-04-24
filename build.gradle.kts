plugins {
    id ("java")
    id ("application")
    id ("edu.sc.seis.launch4j") version "2.4.6"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Test dependencies
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // SQLite JDBC Driver
    implementation("org.xerial:sqlite-jdbc:3.34.0")
    testImplementation("org.mockito:mockito-core:4.0.0")

}


tasks.test {
    useJUnitPlatform()
}

launch4j {
    headerType="console"
    mainClassName = "projects.manager.Main"
    outfile = "ProjectsManager.exe"
    jreMinVersion = "1.8.0"
}
// Use ./gradlew launch4j line to launch launch4j exe file maker :)