import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.addPlugin(AndroidConfig.Plugin.androidLib)
        project.addPlugin(AndroidConfig.Plugin.kotlinAndroid)
        project.addPlugin(AndroidConfig.Plugin.kapt)
        project.androidConfiguration { defaultConfig() }
        project.kotlinCompileOptions()
    }

    /**
     * Configures the [Java][org.gradle.api.plugins.JavaPluginExtension] extension.
     */
    private fun applyJavaExtension(project: Project) {
        val extension = project.extensions.findByType(JavaPluginExtension::class.java) ?: return
        extension.apply {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }
}