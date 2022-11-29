plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":domain"))

    implementation(Libs.Paging.PAGING)
    implementation(Libs.Hilt.ANDROID)
    implementation(platform(Libs.Firebase.FIREBASE_BOM))
    implementation(Libs.Firebase.AUTH)
    kapt(Libs.Hilt.COMPILER)
    implementation(Libs.Network.RETROFIT)
    implementation(Libs.Network.CONVERTER_GSON)
    implementation(Libs.Network.OKHTTP)
    implementation(Libs.Network.LOGGING_INTERCEPTOR)
    implementation(Libs.Timber.TIMBER)
    implementation(Libs.Coroutines.CORE)
    implementation(Libs.DataStore.DATASTORE)
    testImplementation(Libs.Test.JUNIT)
    testImplementation(Libs.Test.JUNIT_JUPITER)
    testImplementation(Libs.Test.ASSERTJ)
    testImplementation(Libs.Test.MOCK_WEB_SERVER)
    testImplementation(Libs.Test.MOCKK)
    testImplementation(Libs.Test.JSON)
}
