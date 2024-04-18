package io.bidapp.demo.Data

import io.bidapp.demo.isEnableBanner
import io.bidapp.demo.isEnableInterstitial
import io.bidapp.demo.isEnableLogging
import io.bidapp.demo.isEnableRewarded
import io.bidapp.demo.isEnableTestMode
import io.bidapp.demo.pubId
import io.bidapp.kmp.BIDConfiguration
import io.bidapp.kmp.BidConsent
import io.bidapp.kmp.BidappAds



fun bidappInit(activity: Any? = null) {
    val bidConfig = BIDConfiguration()
    if (isEnableBanner) bidConfig.enableBannerAds()
    if (isEnableRewarded) bidConfig.enableRewardedAds()
    if (isEnableInterstitial) bidConfig.enableInterstitialAds()
    if (isEnableLogging) bidConfig.enableLoggingAds()
    if (isEnableTestMode) bidConfig.enableTestModeAds()

    BidConsent.setCCPA(true)
    BidConsent.setCOPPA(true)
    BidConsent.setGDPR(true)

    io.bidapp.kmp.BidappAds.start(pubId, bidConfig, activity)

}



