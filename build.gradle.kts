plugins {
    id("java")
    id("com.diffplug.spotless") version "6.25.0"
}

group = "com.saucedemo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

configurations {
    create("aspectj")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.33.0")
    testImplementation("io.qameta.allure:allure-junit5:2.27.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    "aspectj"("org.aspectj:aspectjweaver:1.9.22.1")
}

spotless {
    java {
        googleJavaFormat()
        target("src/**/*.java")
    }
}

tasks.test {
    useJUnitPlatform()
    jvmArgs("-javaagent:${configurations["aspectj"].singleFile}")
    systemProperty("allure.results.directory", "${layout.buildDirectory.get()}/allure-results")
    systemProperty("headless", project.findProperty("headless") ?: "false")
}

listOf("chrome", "firefox").forEach { browser ->
    tasks.register<Test>("test${browser.replaceFirstChar { it.uppercase() }}") {
        useJUnitPlatform()
        jvmArgs("-javaagent:${configurations["aspectj"].singleFile}")
        systemProperty("browser", browser)
        systemProperty("allure.results.directory", "${layout.buildDirectory.get()}/allure-results")
        systemProperty("headless", project.findProperty("headless") ?: "false")
        testClassesDirs = sourceSets["test"].output.classesDirs
        classpath = sourceSets["test"].runtimeClasspath
        outputs.upToDateWhen { false }
    }
}

tasks.register("testAllBrowsers") {
    dependsOn("testChrome", "testFirefox")
}
