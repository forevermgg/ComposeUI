buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
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
        classpath(Libs.Plugins.detekt)
        classpath(Libs.Plugins.detektFormatting)
        classpath(Libs.Plugins.ktlintGradle)
        classpath(Libs.Plugins.benManesGradleVersions)
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt").version(Versions.detektVersion)
}

allprojects {
    apply("com.github.ben-manes.versions")

    tasks.named("dependencyUpdates").configure {
        // configure the task, for example wrt. resolution strategies
    }
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = "1.8"
}

tasks.register<io.gitlab.arturbosch.detekt.Detekt>("myDetekt") {
    description = "Runs a custom detekt build."
    setSource(files("src/main/kotlin", "src/test/kotlin"))
    config.setFrom(files("$rootDir/config.yml"))
    debug = true
    reports {
        xml {
            destination = file("build/reports/mydetekt.xml")
        }
        html.destination = file("build/reports/mydetekt.html")
    }
    include("**/*.kt")
    include("**/*.kts")
    exclude("resources/")
    exclude("build/")
}

tasks.register(Tasks.clean, Delete::class) {
    delete(rootProject.buildDir)
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    // include("**/special/package/**") // only analyze a sub package inside src/main/kotlin
    exclude("**/special/package/internal/**") // but exclude our legacy internal package
}
