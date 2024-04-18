plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.com.vanniktech.maven.publish)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    val podfilePath: String? = project.findProperty("podfilePath") as String?
    val bidappPods = project.file(podfilePath ?: "../iosApp/Podfile")
    if (!bidappPods.exists()) {
        throw GradleException("Podfile not found at ../iosApp/Podfile. If it's not there, please specify it manually in gradle.properties (ex podfilePath=../Podfile) and make sure it's correct.")
    }

    cocoapods {
        summary = "Bidapp ads for kotlin multiplatform"
        homepage = "https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file(bidappPods)
        pod("bidapp/SDK")
        framework {
            baseName = "bidapp_kmp"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
         }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "io.bidapp.kmp"
    compileSdk = 34
    defaultConfig {
        minSdk = 22
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    dependencies {
       implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
       implementation ("io.bidapp:sdk:1.0.7")
    }
}
dependencies {
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.activity.ktx)
}
