plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composemulti)
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
        podfile = project.file("../iosApp/Podfile")
        pod("bidapp/AdMob")
        pod("bidapp/Unity")
        pod("bidapp/Liftoff")
       // pod("bidapp/Applovin")
       // pod("bidapp/ApplovinMax")
       // pod("bidapp/Chartboost")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(project(":plugin"))
            implementation(compose.ui)
            implementation(compose.material)
            implementation(compose.foundation)
            implementation(compose.runtime)
            implementation(compose.components.resources)
         }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "io.bidapp.kotlinmultiwork"
    compileSdk = 34
    defaultConfig {
        minSdk = 22
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        implementation ("io.bidapp.networks:unity:+")
        implementation ("io.bidapp.networks:liftoff:+")
        implementation ("io.bidapp.networks:admob:+")
     //   implementation ("io.bidapp.networks:applovin:+")
     //   implementation ("io.bidapp.networks:applovinmax:+")
     //   implementation ("io.bidapp.networks:chartboost:+")
     //   implementation ("io.bidapp.networks:startIo:+")
     //   implementation ("io.bidapp.networks:facebook:+")
     //   implementation ("io.bidapp.networks:digitalturbine:+")
     //   implementation ("io.bidapp.networks:yandex:+")
     //   implementation ("io.bidapp.networks:mytarget:+")
    }
}

dependencies {
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.activity.ktx)


}
