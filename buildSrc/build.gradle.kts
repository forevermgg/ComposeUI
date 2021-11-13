plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
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
}

dependencies {
    compileOnly(gradleApi())
    implementation("com.android.tools.build:gradle:7.0.1")
    implementation(kotlin("gradle-plugin", "1.5.21"))
    // implementation("com.android.tools.build:gradle:7.1.0-alpha12")
}
