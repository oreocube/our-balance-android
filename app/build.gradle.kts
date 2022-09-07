plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        applicationId = Config.APPLICATION_ID
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK
        versionCode = Config.VERSION_CODE
        versionName = Config.VERSION_NAME

        testInstrumentationRunner = Config.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":data"))
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
