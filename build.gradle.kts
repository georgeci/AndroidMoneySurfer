buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven("https://dl.bintray.com/arrow-kt/arrow-kt/")
        maven("https://oss.jfrog.org/artifactory/oss-snapshot-local/") // for SNAPSHOT builds
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-alpha03")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21")
        classpath("com.squareup.sqldelight:gradle-plugin:1.4.3")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.30.1-alpha")
    }
}

allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven("https://dl.bintray.com/arrow-kt/arrow-kt/")
        maven("https://oss.jfrog.org/artifactory/oss-snapshot-local/") // for SNAPSHOT builds
    }
}

task("clean", Delete::class) {
    delete(buildDir)
}