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
    implementation(Libs.Network.RETROFIT)
    implementation(Libs.Network.CONVERTER_GSON)
    implementation(Libs.Network.OKHTTP)
    implementation(Libs.Network.LOGGING_INTERCEPTOR)
    implementation(Libs.Timber.TIMBER)
    implementation(Libs.Coroutines.CORE)
    implementation(Libs.DataStore.DATASTORE)
    testImplementation(Libs.Test.MOCK_WEB_SERVER)
}
