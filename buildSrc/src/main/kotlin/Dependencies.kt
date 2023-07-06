object Dependencies {
    //kotlin
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect"
    const val KOTLIN_JDK = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
    const val JACKSON = "com.fasterxml.jackson.module:jackson-module-kotlin:${DependencyVersions.JACKSON_VERSION}"
    const val JACKSON_TYPE = "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${DependencyVersions.JACKSON_VERSION}"

    //web
    const val SPRING_WEB = "org.springframework.boot:spring-boot-starter-web"

    //servlet
    const val JAVA_SERVLET = "javax.servlet:javax.servlet-api:${DependencyVersions.SERVLET_VERSION}"

    //validation
    const val SPRING_VALIDATION = "org.springframework.boot:spring-boot-starter-validation"

    //database
    const val SPRING_DATA_JPA = "org.springframework.boot:spring-boot-starter-data-jpa:${PluginVersions.SPRING_BOOT_VERSION}"
    const val MYSQL_CONNECTOR = "mysql:mysql-connector-java:${DependencyVersions.MYSQL}"
    const val SPRING_REDIS = "org.springframework.boot:spring-boot-starter-data-redis:${PluginVersions.SPRING_BOOT_VERSION}"
    const val REDIS = "org.springframework.data:spring-data-redis:${DependencyVersions.REDIS_VERSION}"
    const val MARIADB = "org.mariadb.jdbc:mariadb-java-client:${DependencyVersions.MARIA_VERSION}"

    // security
    const val SPRING_SECURITY = "org.springframework.boot:spring-boot-starter-security"

    //aws
    const val SPRING_AWS = "org.springframework.cloud:spring-cloud-starter-aws:${DependencyVersions.AWS_VERSION}"

    // jwt
    const val JWT = "io.jsonwebtoken:jjwt:${DependencyVersions.JWT_VERSION}"

    // gauth
    const val GAUTH = "com.github.GSM-MSG:GAuth-SDK-Java:v${DependencyVersions.GAUTH_VERSION}"

    // transaction
    const val SPRING_TRANSACTION = "org.springframework:spring-tx:${DependencyVersions.SPRING_TRANSACTION}"

    // graphql
    const val GRAPHQL = "org.springframework.boot:spring-boot-starter-graphql"

    // test
    const val SPRING_TEST = "org.springframework.boot:spring-boot-starter-test:${PluginVersions.SPRING_BOOT_VERSION}"
    const val MOCKK = "io.mockk:mockk:${DependencyVersions.MOCKK_VERSION}"

    // kotest
    const val KOTEST_JUNIT5 = "io.kotest:kotest-runner-junit5:${DependencyVersions.KOTEST_VERSION}"
    const val KOTEST_ASSERTIONS_CORE = "io.kotest:kotest-assertions-core:${DependencyVersions.KOTEST_VERSION}"
    const val KOTEST_EXTENSION_SPRING = "io.kotest.extensions:kotest-extensions-spring:${DependencyVersions.KOTEST_EXTENSION_VERSION}"

}