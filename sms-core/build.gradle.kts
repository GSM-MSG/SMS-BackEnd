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
    annotation("team.msg.sms.common.annotation.UseCase")
    annotation("team.msg.sms.common.annotation.ReadOnlyUseCase")
    annotation("team.msg.sms.common.annotation.Service")
}