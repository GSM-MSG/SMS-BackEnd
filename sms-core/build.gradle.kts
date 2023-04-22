plugins {
    kotlin("plugin.allopen") version PluginVersions.ALLOPEN_VERSION
}

repositories {
    mavenCentral()
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