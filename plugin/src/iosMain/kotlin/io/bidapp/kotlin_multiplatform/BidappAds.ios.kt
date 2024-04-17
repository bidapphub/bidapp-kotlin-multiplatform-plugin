package io.bidapp.kotlin_multiplatform
import cocoapods.bidapp.*
import kotlinx.cinterop.ExperimentalForeignApi


actual object BidappAds {
    private val ApplovinId = 1
    private val ApplovinMaxId = 2
    private val UnityId = 3
    private val LiftoffId = 4
    private val ChartboostId = 6
    private val AdmobId = 7
    private val StartIoId = 8
    private val DigitalTurbineId = 9
    private val FacebookId = 10

    @OptIn(ExperimentalForeignApi::class)
    actual fun start(
        pubId: String,
        bidConfiguration: BIDConfiguration,
        applicationContext: Any?
    ) {
        val config =  cocoapods.bidapp.BIDConfiguration()
        if (bidConfiguration.isInterstitialEnable == true) config.enableInterstitialAds()
        if (bidConfiguration.isRewardedEnable == true) config.enableRewardedAds()
        if (bidConfiguration.isBannerEnable == true) config.enableBannerAds()
        if (bidConfiguration.isLoggingEnable == true) {
            BIDLog.logEnabled
            config.enableLogging()
        }
        if (bidConfiguration.isTestModeEnable == true) config.enableTestMode()
        if (!bidConfiguration.arrayNetworkSDKKey.isNullOrEmpty()) {
            bidConfiguration.arrayNetworkSDKKey!!.forEach {
                if (getNetworkId(it.networkId) != null) {
                    config.setSDKKey(it.sdkKey, it.secondKey, getNetworkId(it.networkId)!!,null)
                    if (!bidConfiguration.arrayNetworkAdTag.isNullOrEmpty()) {
                        bidConfiguration.arrayNetworkAdTag!!.forEach { it2 ->
                            if ((it.networkId == it2.networkId) && getNetworkId(it2.networkId) != null && getAdFormat(
                                    it2.adFormat
                                ) != null
                            ) {
                                config.setAdTag(
                                    it2.adTag,
                                    getNetworkId(it2.networkId)!!,
                                    getAdFormat(it2.adFormat)!!,
                                    it2.ecpm,
                                    null
                                )
                            }
                        }
                    }
                }
            }
        }
        cocoapods.bidapp.BidappAds.startWithPubid(pubId, config)
    }


    private fun getNetworkId(networkId: BIDNetworkId): Long? {
        return when (networkId.getNetwork()) {
            ApplovinId -> 1
            ApplovinMaxId -> 2
            UnityId -> 3
            LiftoffId -> 4
            ChartboostId -> 6
            AdmobId -> 7
            StartIoId -> 8
            DigitalTurbineId -> 9
            FacebookId -> 10
            else -> null
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun getAdFormat(adFormat: BIDAdFormat): cocoapods.bidapp.BIDAdFormat? {
        return when (adFormat.getAdFormat()) {
            Interstitial -> cocoapods.bidapp.BIDAdFormat.interstitial as cocoapods.bidapp.BIDAdFormat
            Rewarded -> cocoapods.bidapp.BIDAdFormat.rewarded as cocoapods.bidapp.BIDAdFormat
            Banner -> cocoapods.bidapp.BIDAdFormat.banner_320x50 as cocoapods.bidapp.BIDAdFormat
            Mrec -> cocoapods.bidapp.BIDAdFormat.banner_300x250 as cocoapods.bidapp.BIDAdFormat
            else -> return null
        }
    }

}