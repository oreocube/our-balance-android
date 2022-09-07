object Version {
    const val CORE = "1.8.0"
    const val APPCOMPAT = "1.5.0"
    const val MATERIAL = "1.6.1"
    const val CONSTRAINT_LAYOUT = "2.1.4"

    const val JUNIT = "4.13.2"
    const val ANDROID_JUNIT = "1.1.3"
    const val ESPRESSO_CORE = "3.4.0"

}

object AndroidX {
    const val CORE = "androidx.core:core-ktx:${Version.CORE}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Version.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${Version.MATERIAL}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT_LAYOUT}"
}

object Test {
    const val JUNIT = "junit:junit:${Version.JUNIT}"
}

object AndroidTest {
    const val ANDROID_JUNIT = "androidx.test.ext:junit:${Version.ANDROID_JUNIT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Version.ESPRESSO_CORE}"
}
