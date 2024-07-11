package io.bidapp.demo.Data

import android.app.Activity
import android.content.Context
import io.bidapp.demo.isEnableLogging
import io.bidapp.demo.isEnableTestMode
import io.bidapp.demo.pubId
import io.bidapp.kmp.BIDConfiguration
import io.bidapp.kmp.BidappAds
import io.bidapp.kmp.BidappInitSettings



actual object BIDAppAds{
    fun start(applicationContext : Context){
        val bidConfig = BIDConfiguration()
        if (isEnableLogging) bidConfig.enableLoggingAds()
        if (isEnableTestMode) bidConfig.enableTestModeAds()
        BidappAds.start(BidappInitSettings(pubId, bidConfig, applicationContext))
    }
     fun setActivity(activity: Activity){
        BidappAds.setActivity(activity)
     }

}