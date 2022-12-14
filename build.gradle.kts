// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version ("7.2.2") apply (false)
    id("com.android.library") version ("7.2.2") apply (false)
    id("org.jetbrains.kotlin.android") version ("1.6.21") apply (false)
}
buildscript {
    dependencies {
        classpath(Libs.Hilt.ANDROID_GRADLE_PLUGIN)
    }
}

subprojects {
    afterEvaluate {
        project.apply("$rootDir/gradle/common.gradle")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
