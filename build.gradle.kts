plugins {
	kotlin("jvm") version "1.6.21"
}


repositories {
	mavenCentral()
}

subprojects {
	apply {
		plugin("org.jetbrains.kotlin.jvm")
		version = "1.6.21"
	}

	dependencies {
		// kotlin
		implementation(Dependencies.KOTLIN_REFLECT)
		implementation(Dependencies.KOTLIN_JDK)
		implementation(Dependencies.JACKSON)

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
				jvmTarget = "11"
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
	}
}
