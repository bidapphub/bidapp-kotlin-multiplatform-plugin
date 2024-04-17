package io.bidapp.kotlin_multiplatform

import android.content.Context
import io.bidapp.sdk.AdFormat
import io.bidapp.sdk.BIDConfiguration
import io.bidapp.sdk.BIDNetworkId
import io.bidapp.sdk.BidappAds


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
    private val MyTargetId = 11
    private val YandexId = 12
    actual fun start(
        pubId: String,
        bidConfiguration: io.bidapp.kotlin_multiplatform.BIDConfiguration,
        applicationContext: Any?
    ) {

        val config = BIDConfiguration()
        if (bidConfiguration.isInterstitialEnable == true) config.enableInterstitialAds()
        if (bidConfiguration.isRewardedEnable == true) config.enableRewardedAds()
        if (bidConfiguration.isBannerEnable == true) config.enableBannerAds()
        if (bidConfiguration.isLoggingEnable == true) {
            BIDLog.logEnabled = true
            config.enableLogging()
        }
        if (bidConfiguration.isTestModeEnable == true) config.enableTestMode()
        if (!bidConfiguration.arrayNetworkSDKKey.isNullOrEmpty()) {
            bidConfiguration.arrayNetworkSDKKey!!.forEach {
                if (getNetworkId(it.networkId) != null) {
                    config.setSDKKey(it.sdkKey, getNetworkId(it.networkId)!!, it.secondKey)
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
                                    it2.ecpm
                                )
                            }
                        }
                    }
                }
            }
        }
        val context = applicationContext as? Context
        if (context == null) {
            log("start failed. Error: context is null")
            return
        }
        BidappAds.start(pubId, config, context)
    }

    private fun getNetworkId(networkId: io.bidapp.kotlin_multiplatform.BIDNetworkId): BIDNetworkId? {
        return when (networkId.getNetwork()) {
            ApplovinId -> BIDNetworkId.Applovin
            ApplovinMaxId -> BIDNetworkId.ApplovinMax
            UnityId -> BIDNetworkId.Unity
            LiftoffId -> BIDNetworkId.Liftoff
            ChartboostId -> BIDNetworkId.Chartboost
            AdmobId -> BIDNetworkId.Admob
            StartIoId -> BIDNetworkId.StartIo
            DigitalTurbineId -> BIDNetworkId.DigitalTurbine
            FacebookId -> BIDNetworkId.Facebook
            MyTargetId -> BIDNetworkId.MyTarget
            YandexId -> BIDNetworkId.Yandex
            else -> null
        }
    }

    private fun getAdFormat(adFormat: BIDAdFormat): AdFormat? {
        return when (adFormat.getAdFormat()) {
            Interstitial -> AdFormat.interstitial
            Rewarded -> AdFormat.rewarded
            Banner -> AdFormat.banner_320x50
            Mrec -> AdFormat.banner_300x250
            else -> return null
        }
    }


}