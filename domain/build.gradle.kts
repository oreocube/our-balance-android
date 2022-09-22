plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation(Libs.Hilt.CORE)
    kapt(Libs.Hilt.COMPILER)
    implementation(Libs.Coroutines.CORE)
}