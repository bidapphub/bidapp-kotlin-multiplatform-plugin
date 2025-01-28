
<div align="center">
    <img alt="Logo" src="demo/shared/src/commonMain/composeResources/drawable/bidapp_logo.png" width="120"/>
</div>
 <br/><br/>
<div align="center">
    <p>
        <img src="https://img.shields.io/badge/Kotlin-_multiplatform-blue"/>
        <img src="https://img.shields.io/badge/Android-green"/>
        <img src="https://img.shields.io/badge/IOS-red"/>
    </p>
</div>

# Bidapp ads kotlin multiplatform demo

Bidapp ads kotlin multiplatform demo for Android and IOS

# Documentation

Complete integration instructions and usage guide for the Bidapp Ads Kotlin Multiplatform library can be found [here](https://docs.bidapp.io).

# Integrate Bidapp KMP library

To use Bidapp multiplatform, implement the library in the build.gradle.kts file within the commonMain section:

```groovy
// Latest version 0.8.1
    sourceSets {
        commonMain.dependencies {
            implementation("io.bidapp:kmp:+")
         }
    }
```

On iOS, you also have to add the Bidapp pod to your Podfile:

```kotlin
target 'iosApp' do
    pod 'bidapp/SDK'
end 
```
For detailed usage of the library, please follow the link provided in the documentation.

# Demo APP
<div align="center">
  <img alt="Logo" src="https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/823f5ced-e486-478d-9263-b00d97cf1519" width="350"/>
  <img alt="Logo" src="https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/12a20f5a-cf49-464c-9633-ad5de6ec9a41" width="350"/>
</div>
<br/><br/>
The demo directory contains a shared module and test applications for Android and iOS

# Setting up advertising display in the demo

* Go to the [Bidapp dashboard](https://dashboard-564.pages.dev), add a new application, and connect advertising networks.  
For this demo, a test App ID for AdMob is set in the AndroidManifest for Android and in the Info.plist for iOS.

* In the build.gradle.kts file of the shared module, you can configure the adapters 	according to the settings of the advertising networks on the Bidapp dashboard.


Example for Android:
```groovy
plugins {
 android {
    //...
    dependencies {
        implementation ("io.bidapp.networks:unity:latest")
        implementation ("io.bidapp.networks:liftoff:latest")
        implementation ("io.bidapp.networks:admob:latest")
    }
  }
}
```

Example for IOS:
```groovy
plugins {
 cocoapods {
        //...
        pod("bidapp/AdMob")
        pod("bidapp/Unity")
        pod("bidapp/Liftoff")
        //...
    }
}
```

* Set in the Settings file located in the shared module the pubId for Android and iOS. Additionally, you can enable or disable:

Test mode, Logging

```kotlin
const val pubId = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx"

const val isEnableTestMode = true
const val isEnableLogging = true
```



# IOS
![interstitialios](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/5e9a2bfb-ae33-4560-881c-d14f3f547fe1)
![rewardedios](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/02127e04-4987-4e2c-b340-f9878c495694)
![mrecios](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/3e6da323-0172-47f1-a316-fc5dab1a746c)
![bannerios](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/eb4a266f-4bc0-48a8-b3f1-da77ad6ab679)


# Android
![interstitialandroid](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/14bba893-1185-4238-9a8d-a16321e5d77f)
![rewardedandroid](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/7909f3d7-172e-4e72-b76e-2a5c332af238)
![mrecandroid](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/53ca3cc2-9ebd-4c8c-9992-8acc3399a7cf)
![bannerandroid](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/197dbe9c-9ccc-4f51-b136-9a7e19a0391b)



# Contact Us

Email: [support@bidapp.io](support@bidapp.io)
