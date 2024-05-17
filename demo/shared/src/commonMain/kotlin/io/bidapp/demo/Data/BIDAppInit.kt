package io.bidapp.demo.Data

import io.bidapp.demo.isEnableLogging
import io.bidapp.demo.isEnableTestMode
import io.bidapp.demo.pubId
import io.bidapp.kmp.BIDConfiguration
import io.bidapp.kmp.BidConsent
import io.bidapp.kmp.BidappAds



fun bidappInit(activity: Any? = null) {
    val bidConfig = BIDConfiguration()
    if (isEnableLogging) bidConfig.enableLoggingAds()
    if (isEnableTestMode) bidConfig.enableTestModeAds()

    BidConsent.setCCPA(true)
    BidConsent.setCOPPA(true)
    BidConsent.setGDPR(true)

    BidappAds.start(pubId, bidConfig, activity)

}



