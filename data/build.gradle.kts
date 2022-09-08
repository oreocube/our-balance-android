plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":domain"))

    implementation(Libs.Hilt.ANDROID)
    kapt(Libs.Hilt.COMPILER)
}
