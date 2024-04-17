
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

# Demo APP
<div align="center">
  <img alt="Logo" src="https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/19d17174-2dd8-4405-a6c6-09452cc619df" width="350"/>
  <img alt="Logo" src="https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/b4ff436b-1676-4a61-b87c-bf5dbb67551a" width="350"/>
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

Interstitial, Rewarded, Banner, Test mode, Logging

```kotlin
const val pubId = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx"

const val isEnableInterstitial = true
const val isEnableRewarded = true
const val isEnableBanner = true
const val isEnableTestMode = true
const val isEnableLogging = true
```


### Samples

![interstitialandroid](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/34e6aa08-c74f-4f72-b2b8-744c749d8b08)
![rewardedandroid](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/44d4857e-9bdb-441f-b2df-aba7de3252d6)
![mrecandroid](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/6ec1e327-a956-43f7-a530-feb52186e7b5)
![bannerandroid](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/ee46cc63-bdde-411b-b0d6-2e498da6d05b)

![interstitialios](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/25eed875-bf7e-4560-9687-484a3a9e390a)
![rewardedios](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/f329536b-2909-4589-9a32-00912954bab2)
![mrecios](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/0a6b1c0e-85ea-4e26-94b3-e9325847dab5)
![bannerios](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/56440e75-1366-4110-933f-7f8434a649ff)

# Contact Us

Email: [support@bidapp.io](support@bidapp.io)
