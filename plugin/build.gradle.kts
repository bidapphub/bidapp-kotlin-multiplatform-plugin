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

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../demo/iosApp/Podfile")
        pod("bidapp/SDK")
        framework {
            baseName = "bidapp_kotlin_multiplatform"
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
    namespace = "io.bidapp.kotlin_multiplatform"
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
       implementation("io.bidapp:sdk:1.0.7")
    }
}
dependencies {
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.activity.ktx)
}
