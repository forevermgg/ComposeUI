plugins {
    id("simple_android-library-plugin")
}

android {
    defaultConfig()
}

dependencies {
    kotlin()
    baseAndroid()
    compose()
    coroutines()
    implementation(Libs.Tencent.mmkv)
    implementation(Libs.Airbnb.lottie)
}