object Libs {
    object AndroidX {
        const val CORE = "androidx.core:core-ktx:${Version.CORE}"
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Version.APPCOMPAT}"
        const val MATERIAL = "com.google.android.material:material:${Version.MATERIAL}"
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT_LAYOUT}"
    }

    object Hilt {
        const val ANDROID_GRADLE_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:${Version.HILT}"
        const val ANDROID = "com.google.dagger:hilt-android:${Version.HILT}"
        const val COMPILER = "com.google.dagger:hilt-compiler:${Version.HILT}"
    }

    object Test {
        const val JUNIT = "junit:junit:${Version.JUNIT}"
    }

    object AndroidTest {
        const val ANDROID_JUNIT = "androidx.test.ext:junit:${Version.ANDROID_JUNIT}"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Version.ESPRESSO_CORE}"
    }
}
