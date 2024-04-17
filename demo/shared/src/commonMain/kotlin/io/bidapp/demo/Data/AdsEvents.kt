package io.bidapp.demo.Data

interface AdsEvents {
    fun displayBanner()
    fun interstitialLoad()
    fun rewardedLoad()

    fun loadBanner(networkID : Int?)

}