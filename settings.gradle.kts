enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven {
            url = uri("https://cboost.jfrog.io/artifactory/chartboost-ads/")
        }

            maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        maven {
            url = uri("https://cboost.jfrog.io/artifactory/chartboost-ads/")
        }
            mavenCentral()
    }
}

rootProject.name = "BidappAds-Demo"
include(":demo:androidApp")
include(":bidappAds")
include(":demo:shared")
