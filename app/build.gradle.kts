plugins {
    id("android-application-plugin")
}
apply(plugin = "kotlin-android-extensions")
android {
    defaultConfig()
    viewBinding.isEnabled = true
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = Versions.compose
}

dependencies {
    implementation(project(":baseLib"))
    implementation(Libs.Airbnb.mavericks)
    kotlin()
    dependencyInjection()
    baseAndroid()
    navigation()
    coroutines()
    compose()
    test()
}