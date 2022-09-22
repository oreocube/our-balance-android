import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

android {
    defaultConfig {
        buildConfigField("String", "BASE_URL", properties.getProperty("BASE_URL"))
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":feature"))

    implementation(Libs.AndroidX.CORE)
    implementation(Libs.AndroidX.APPCOMPAT)
    implementation(Libs.AndroidX.MATERIAL)
    implementation(Libs.AndroidX.CONSTRAINT_LAYOUT)
    testImplementation(Libs.Test.JUNIT)
    androidTestImplementation(Libs.AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(Libs.AndroidTest.ESPRESSO_CORE)

    implementation(Libs.Hilt.ANDROID)
    kapt(Libs.Hilt.COMPILER)
    implementation(Libs.Network.RETROFIT)
    implementation(Libs.Network.CONVERTER_GSON)
    implementation(Libs.Network.OKHTTP)
    implementation(Libs.Network.LOGGING_INTERCEPTOR)
    implementation(Libs.Timber.TIMBER)
    implementation(Libs.Coroutines.CORE)
    implementation(Libs.DataStore.DATASTORE)
}
