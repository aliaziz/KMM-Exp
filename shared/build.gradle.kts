plugins {
    kotlin("plugin.serialization") version "1.8.21"
    kotlin("multiplatform")
    id("com.android.library")
}
val ktorVersion = "2.3.2"
@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

                //The core ktor dependency
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                //responsible to serializing and deserializing the content to a specific format.
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                //Tells Ktor to use JSON and the kotlin Serialization library
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktorVersion")
            }
        }

        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.example.kmpfirst"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}