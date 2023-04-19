plugins {
    kotlin("plugin.allopen") version PluginVersions.ALLOPEN_VERSION
}

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation(Dependencies.GAUTH)
}

allOpen {
}