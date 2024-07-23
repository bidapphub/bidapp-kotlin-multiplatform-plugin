plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composemulti)
    alias(libs.plugins.compose.compiler)
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
        pod("bidapp/SDK")
        framework {
            baseName = "io.bidapp.kmp"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.constraintlayout)
            implementation("io.bidapp:sdk:2.0.1")
        }
        commonMain.dependencies {
            implementation(compose.foundation)
            implementation(compose.runtime)
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
dependencies {
    implementation(libs.androidx.foundation.layout.android)
}



