plugins {
    kotlin("plugin.allopen") version PluginVersions.ALLOPEN_VERSION
}

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    // transaction
    implementation(Dependencies.SPRING_TRANSACTION)

    // gauth
    implementation(Dependencies.GAUTH)
}

allOpen {
    annotation(AllOpen.USECASE)
    annotation(AllOpen.READ_ONLY_USECASE)
    annotation(AllOpen.SERVICE)
}