plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":domain"))

    implementation(Libs.AndroidX.CORE)
    implementation(Libs.AndroidX.APPCOMPAT)
    implementation(Libs.AndroidX.MATERIAL)
    implementation(Libs.AndroidX.CONSTRAINT_LAYOUT)
    testImplementation(Libs.Test.JUNIT)
    androidTestImplementation(Libs.AndroidTest.ANDROID_JUNIT)
    androidTestImplementation(Libs.AndroidTest.ESPRESSO_CORE)

    implementation(Libs.Hilt.ANDROID)
    kapt(Libs.Hilt.COMPILER)
}
