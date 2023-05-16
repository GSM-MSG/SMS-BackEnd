plugins {
	kotlin("jvm") version PluginVersions.JVM_VERSION
}

repositories {
	mavenCentral()
}

subprojects {
	apply {
		plugin("org.jetbrains.kotlin.jvm")
		version = PluginVersions.JVM_VERSION
	}

	dependencies {
		// kotlin
		implementation(Dependencies.KOTLIN_REFLECT)
		implementation(Dependencies.KOTLIN_JDK)
		implementation(Dependencies.JACKSON)

		//servlet
		implementation(Dependencies.JAVA_SERVLET)

		// test
		implementation(Dependencies.SPRING_TEST)
		implementation(Dependencies.MOCKK)
	}
}

allprojects {
	group = "team.msg"
	version = "0.0.1-SNAPSHOT"
	java.sourceCompatibility = JavaVersion.VERSION_11

	tasks {
		compileKotlin {
			kotlinOptions {
				freeCompilerArgs = listOf("-Xjsr305=strict")
				jvmTarget = PluginVersions.JVM_TARGET_VERSION
			}
		}
		compileJava {
			sourceCompatibility = JavaVersion.VERSION_11.majorVersion
		}
		test {
			useJUnitPlatform()
		}
	}
	repositories {
		mavenCentral()
		maven { url = uri("https://jitpack.io") }
	}
}