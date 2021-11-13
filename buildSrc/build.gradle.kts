plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
    kotlin("jvm") version "1.5.21"
}

gradlePlugin {
    plugins {
        register("android-application-plugin") {
            id = "android-application-plugin"
            implementationClass = "AndroidApplicationPlugin"
        }
        register("android-library-plugin") {
            id = "android-library-plugin"
            implementationClass = "AndroidLibraryPlugin"
        }
        register("kotlin-library-plugin") {
            id = "kotlin-library-plugin"
            implementationClass = "KotlinLibraryPlugin"
        }
        register("simple_android-library-plugin") {
            id = "simple_android-library-plugin"
            implementationClass = "SimpleAndroidLibraryPlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

gradlePlugin {
    plugins {
        register("aps") {
            id = "aps-plugin"
            implementationClass = "PasswordStorePlugin"
        }
        register("crowdin") {
            id = "crowdin-plugin"
            implementationClass = "CrowdinDownloadPlugin"
        }
        register("versioning") {
            id = "versioning-plugin"
            implementationClass = "VersioningPlugin"
        }
        register("psl") {
            id = "psl-plugin"
            implementationClass = "PublicSuffixListPlugin"
        }
    }
}


dependencies {
    compileOnly(gradleApi())
    // implementation("com.android.tools.build:gradle-api:7.0.3")
    // implementation("com.android.tools.build:gradle:7.0.3")
    // implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
    // implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.21")
    implementation("com.android.tools.build:gradle:7.1.0-alpha12")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:5.16.0")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.40.1")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.vanniktech:gradle-maven-publish-plugin:0.18.0")
    implementation("com.vdurmont:semver4j:3.1.0")
    implementation("de.undercouch:gradle-download-task:4.1.2")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.5.31")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
    implementation("org.jetbrains.kotlinx:binary-compatibility-validator:0.6.0")
}
