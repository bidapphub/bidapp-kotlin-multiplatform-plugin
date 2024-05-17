plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("maven-publish")
    id("signing")
}

kotlin {
    explicitApi()
    androidTarget {
        publishAllLibraryVariants()
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Bidapp ads for kotlin multiplatform"
        homepage = "https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin"
        version = "1.0"
        ios.deploymentTarget = "12.0"
        podfile = project.file("../demo/iosApp/Podfile")
        pod("bidapp/SDK") {
            extraOpts += listOf("-compiler-option", "-fmodules")
        }
        framework {
            baseName = "io.bidapp.kmp"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation("androidx.constraintlayout:constraintlayout:2.1.4")
            implementation("io.bidapp:sdk:1.1.0")
        }
        commonMain.dependencies {
        }
    }
}
android {
    namespace = "io.bidapp.kmp"
    compileSdk = 34
    defaultConfig {
        minSdk = 22
        targetSdk = 34
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}




