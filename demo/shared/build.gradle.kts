plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composemulti)
    alias(libs.plugins.compose.compiler)
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
        summary = "Bidapp ads for kotlin multiplatform"
        homepage = "https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin"
        version = "1.0"
        ios.deploymentTarget = "12.0"
        podfile = project.file("../iosApp/Podfile")
        pod("bidapp/AdMob")
        pod("bidapp/Unity")
        pod("bidapp/Liftoff")
        pod("bidapp/Applovin")
        pod("bidapp/ApplovinMax")
        pod("bidapp/Chartboost")
        pod("bidapp/StartApp")
        pod("bidapp/Fyber")
        pod("bidapp/Facebook")
//        pod("bidapp/Bigo")
//        pod("bidapp/Mintegral")
//        pod("bidapp/InMobi")
//        pod("bidapp/IronSource")
//        pod("bidapp/Smaato")
        framework {
            baseName = "shared"
            binaryOption("bundleId", "com.bidapp.demo.shared")
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":bidappAds"))
            implementation(compose.ui)
            implementation(compose.material)
            implementation(compose.foundation)
            implementation(compose.runtime)
            implementation(compose.components.resources)
            implementation(libs.lifecycle.viewmodel)
            implementation(libs.lifecycle.viewmodel.compose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "io.bidapp.demo"
    compileSdk = 34
    defaultConfig {
        minSdk = 22
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }

    dependencies {
        implementation ("io.bidapp.networks:unity:+")
        implementation("io.bidapp.networks:liftoff:+")
        implementation("io.bidapp.networks:admob:+")
        implementation ("io.bidapp.networks:applovin:+")
        implementation ("io.bidapp.networks:applovinmax:+")
        implementation("io.bidapp.networks:chartboost:+")
        implementation ("io.bidapp.networks:startIo:+")
        implementation ("io.bidapp.networks:facebook:+")
        implementation ("io.bidapp.networks:digitalturbine:+")
        implementation ("io.bidapp.networks:yandex:+")
        implementation ("io.bidapp.networks:mytarget:+")
        implementation ("io.bidapp.networks:bigoads:+")
        implementation ("io.bidapp.networks:inmobi:+")
        implementation ("io.bidapp.networks:ironsource:+")
        implementation ("io.bidapp.networks:mintegral:+")
        implementation ("io.bidapp.networks:smaato:+")
        implementation ("io.bidapp.networks:pangle:+")
    }
}

dependencies {
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.activity.ktx)
}
