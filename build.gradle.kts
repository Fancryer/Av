plugins {
	kotlin("jvm") version "1.9.22"
}

group="org.fancryer"
version="1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	api("org.antlr:antlr4-runtime:4.13.1")
	// https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-reflect
	implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.0")
}

tasks.test {
	useJUnitPlatform()
}

tasks.build {
	java {
		sourceCompatibility=JavaVersion.VERSION_17
	}
}

kotlin {
	jvmToolchain(21)
}


allprojects {
	configurations.all {
		isTransitive=true
	}
}