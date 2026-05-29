// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.9")
        classpath("com.google.firebase:perf-plugin:1.4.2")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.google.dagger.hilt.android") version "2.47" apply false
}

allprojects {
    tasks.withType<Test>().configureEach {
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}

// Load API keys from secrets.properties
val secretsFile = rootProject.file("secrets.properties")
if (secretsFile.exists()) {
    val properties = java.util.Properties()
    properties.load(java.io.FileInputStream(secretsFile))
    extra["GEMINI_API_KEY"] = properties.getProperty("GEMINI_API_KEY", "")
} else {
    extra["GEMINI_API_KEY"] = System.getenv("GEMINI_API_KEY") ?: ""
}