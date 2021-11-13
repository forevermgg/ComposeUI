import org.gradle.api.JavaVersion

object AndroidConfig {

    object Plugin {
        const val androidApp = "com.android.application"
        const val androidLib = "com.android.library"
        const val kotlinAndroid = "kotlin-android"
        const val kapt = "kotlin-kapt"
        const val javaLib = "kotlin-library"
        const val kotlin = "kotlin"
        const val junit5 = "de.mannodermaus.android-junit5"
        const val mavenPublish = "maven-publish"
    }

    const val sdkVersion = 31
    const val minSdkVersion = 21
    const val compileSdkVersion = 30
    const val targetSdkVersion = 30
    const val versionCode = 1
    const val versionName = "1.0"
    val javaVersion = JavaVersion.VERSION_11
}
