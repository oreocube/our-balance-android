plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Libs.Hilt.CORE)
    kapt(Libs.Hilt.COMPILER)
    implementation(Libs.Coroutines.CORE)
    implementation(Libs.Paging.COMMON)
}