plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlinx.compose) apply false
    alias(libs.plugins.kotlinx.compose.compiler) apply false
}

tasks.register<Exec>("addFilesToXcodeproj") {
    workingDir(layout.projectDirectory)
    commandLine("bash", "-c", "./exportToXcode.sh")
}