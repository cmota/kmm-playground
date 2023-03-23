import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.chromaticnoise.multiplatform-swiftpackage-m1-support")
}

multiplatformSwiftPackage {
    xcframeworkName("SharedKit")
    swiftToolsVersion("5.3")
    targetPlatforms {
        iOS { v("13") }
        watchOS { v("7") }
    }
    buildConfiguration { release() }
    outputDirectory(File(projectDir, "shared"))
}

kotlin {
    explicitApiWarning()

    android {
        publishAllLibraryVariants()
        publishLibraryVariantsGroupedByFlavor = true
    }

    val xcf = XCFramework("SharedKit")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),

        watchosArm32(),
        watchosArm64(),
        watchosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "SharedKit"

            isStatic = false
            linkerOpts.add("-lsqlite3")

            xcf.add(this)
        }
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }

        val watchosArm32Main by getting
        val watchosArm64Main by getting
        val watchosSimulatorArm64Main by getting
        val watchosMain by creating {
            dependsOn(commonMain)

            watchosArm32Main.dependsOn(this)
            watchosArm64Main.dependsOn(this)
            watchosSimulatorArm64Main.dependsOn(this)
        }
    }
}

android {
    namespace = "com.cmota.playground"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}