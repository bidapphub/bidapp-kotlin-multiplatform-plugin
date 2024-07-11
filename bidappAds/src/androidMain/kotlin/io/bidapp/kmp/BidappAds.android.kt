package io.bidapp.kmp

import android.app.Activity
import android.content.Context
import io.bidapp.sdk.AdFormat
import io.bidapp.sdk.BIDNetworkId
import io.bidapp.sdk.BidappAds
import java.lang.ref.WeakReference


public actual object BidappAds {
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
    private var activity : WeakReference<Activity>? = null
    public actual fun start(bidappInitSettings: BidappInitSettings) {
        val config = io.bidapp.sdk.BIDConfiguration()
        if (bidappInitSettings.bidConfiguration.isLoggingEnable == true) {
            BIDLog.logEnabled = true
            config.enableLogging()
        }
        if (bidappInitSettings.bidConfiguration.isTestModeEnable == true) config.enableTestMode()
        if (!bidappInitSettings.bidConfiguration.arrayNetworkSDKKey.isNullOrEmpty()) {
            bidappInitSettings.bidConfiguration.arrayNetworkSDKKey!!.forEach {
                if (getNetworkId(it.networkId) != null) {
                    config.setSDKKey(it.sdkKey, getNetworkId(it.networkId)!!, it.secondKey)
                    if (!bidappInitSettings.bidConfiguration.arrayNetworkAdTag.isNullOrEmpty()) {
                        bidappInitSettings.bidConfiguration.arrayNetworkAdTag!!.forEach { it2 ->
                            if ((it.networkId == it2.networkId) && getNetworkId(it2.networkId) != null && getAdFormat(
                                    it2.adFormat
                                ) != null
                            ) {
                                config.setAdTag(
                                    it2.adTag,
                                    getNetworkId(it2.networkId)!!,
                                    getAdFormat(it2.adFormat)!!,
                                    it2.ecpm,
                                    it2.isInAppBidding
                                )
                            }
                        }
                    }
                }
            }
        }
        val context = bidappInitSettings.applicationContext as? Context
        if (context == null) {
            log("start failed. Error: context is null")
            return
        }
        BidappAds.start(bidappInitSettings.pubId, config, context)
    }

    private fun getNetworkId(networkId: io.bidapp.kmp.BIDNetworkId): BIDNetworkId? {
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
            INTERSTITIAL -> AdFormat.interstitial
            REWARDED -> AdFormat.rewarded
            BANNER -> AdFormat.banner_320x50
            MREC -> AdFormat.banner_300x250
            LEADERBOARD -> AdFormat.banner_728x90
            else -> return null
        }
    }

    public fun setActivity(activity : Activity){
        this.activity = WeakReference(activity)
    }

    public fun getActivity():Activity?{
        return this.activity?.get()
    }

}




public actual class BidappInitSettings(public val pubId : String, public val bidConfiguration: BIDConfiguration, public val applicationContext: Context)