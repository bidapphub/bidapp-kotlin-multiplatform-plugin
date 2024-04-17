
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
  <img alt="Logo" src="https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/ca3dd16c-d331-42b7-8fdc-d523f4dbe7d7" width="350"/>
  <img alt="Logo" src="https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/beb03923-69e8-43ca-9382-338a4cfa33af" width="350"/>

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
![interstitialios](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/367af6a6-8013-40fb-aea3-c1d0724f875e)
![rewardedios](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/ca1878a1-0527-4f22-8609-99efda607cd9)
![mrecios](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/78730b45-da9a-472e-8d9b-b3cfa67d5d85)
![bannerios](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/de314a7a-4a9a-4ba9-b1d6-142e3be57161)




![interstitialandroid](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/cc7d2789-fd1c-4e74-b181-ece7ca6d0152)
![rewardedandroid](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/64da5ea4-56c3-47b1-9409-6379d29a11ac)
![mrecandroid](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/a47799d4-0fb0-40d7-9293-0e36da7d31f9)
![bannerandroid](https://github.com/bidapphub/bidapp-kotlin-multiplatform-plugin/assets/148830475/8d984357-b405-43e2-bad4-ed7a8bb16ecb)



# Contact Us

Email: [support@bidapp.io](support@bidapp.io)
