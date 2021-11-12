buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://mirrors.tencent.com/nexus/repository/maven-public/")
        maven("https://mirrors.tencent.com/repository/maven/tencent_public")
        maven("https://mirrors.tencent.com/repository/maven/thirdparty")
        maven("https://mirrors.tencent.com/repository/maven/thirdparty-snapshots")
        maven("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/")
        maven("https://jitpack.io")
    }
    dependencies {
        classpath(Libs.Plugins.buildGradle)
        classpath(Libs.Plugins.kotlinGradle)
        classpath(Libs.Plugins.detekt) // 在这里添加 Detekt 依赖
        classpath(Libs.Plugins.detektFormatting)
        classpath(Libs.Plugins.ktlintGradle)
    }
}

tasks.register(Tasks.clean, Delete::class) {
    delete(rootProject.buildDir)
}