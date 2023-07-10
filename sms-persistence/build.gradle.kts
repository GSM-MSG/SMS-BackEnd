plugins {
    kotlin("plugin.jpa") version PluginVersions.JPA_PLUGIN_VERSION
    kotlin("plugin.spring") version PluginVersions.SPRING_PLUGIN_VERSION
    kotlin("plugin.allopen") version PluginVersions.ALLOPEN_VERSION
}

dependencies {
    // impl project
    implementation(project(":sms-core"))

    // database
    implementation(Dependencies.SPRING_DATA_JPA)
    runtimeOnly(Dependencies.MYSQL_CONNECTOR)
    implementation(Dependencies.REDIS)
    implementation(Dependencies.SPRING_REDIS)
    implementation(Dependencies.MARIADB)

    implementation(Dependencies.QUERYDSL)
    kapt(Dependencies.QUERYDSL_PROCESSOR)
}

allOpen {
    annotation(AllOpen.ENTITY)
    annotation(AllOpen.MAPPED_SUPERCLASS)
    annotation(AllOpen.EMBEDDABLE)
}