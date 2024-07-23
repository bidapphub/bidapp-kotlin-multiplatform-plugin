package io.bidapp.demo.Ads

import io.bidapp.demo.Ads.data.AdsEvents


interface AppAds {
    fun startSdk()
    fun setAdEvents(adsEvents: AdsEvents)
    fun createInterstitial()
    fun createRewarded()
    fun showInterstitial()
    fun showRewarded()
    fun destroy()

}