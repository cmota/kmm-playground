pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Playground"
include(":androidApp")
include(":shared")

includeBuild("plugins/multiplatform-swiftpackage-m1_support")