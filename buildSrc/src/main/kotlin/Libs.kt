object Libs {

    object Plugins {
        const val buildGradle = "com.android.tools.build:gradle:${Versions.gradle}"
        const val gradleBuildToolsApi = "com.android.tools.build:gradle-api:${Versions.gradle}"
        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val ktlintGradle = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktlint}"
        const val detekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.detektVersion}"
        const val detektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detektVersion}"
        const val benManesGradleVersions = "com.github.ben-manes:gradle-versions-plugin:${Versions.benManesGradleVersionsPluginVersion}"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Versions.ktx}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.ktxFragment}"
        const val vmKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ktxViewModel}"
        const val lifecycleRuntimeKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.ktxLifecycleRuntime}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
        const val navUi = "androidx.navigation:navigation-ui-ktx:${Versions.nav}"

        object Compose {
            const val ui = "androidx.compose.ui:ui:${Versions.compose}"
            const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
            const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
            const val material = "androidx.compose.material:material:${Versions.compose}"
            const val constraint =
                "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraint}"
            const val materialIconsCore =
                "androidx.compose.material:material-icons-core:${Versions.compose}"
            const val materialIconsExtended =
                "androidx.compose.material:material-icons-extended:${Versions.compose}"

            const val activity = "androidx.activity:activity-compose:${Versions.composeActivity}"
            const val viewModel =
                "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModel}"

            object AndroidTest {
                const val uiTest = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
            }
        }

        object AndroidTest {
            const val junit = "androidx.test.ext:junit:${Versions.androidXJunit}"
            const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        }
    }

    object Google {
        const val material = "com.google.android.material:material:${Versions.material}"

        object Annotation {
        }
    }

    object Jetbrains {
        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

        object Coroutines {
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
            const val android =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

            object Test {
                const val coroutines =
                    "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
            }
        }
    }

    object JUnit {
        object Runtime {
        }
    }

    object Tencent {
        const val mmkv = "com.tencent:mmkv-static:${Versions.mmkv}"
    }

    object Airbnb {
        const val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"
        const val mavericks = "com.airbnb.android:mavericks:${Versions.mavericksVersion}"
    }
}