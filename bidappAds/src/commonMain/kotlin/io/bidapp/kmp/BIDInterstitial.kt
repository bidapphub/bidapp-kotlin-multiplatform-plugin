package io.bidapp.kmp


expect class BIDInterstitial(activity : Any?){
    fun showInterstitial(activity: Any?, bidShowDelegate : BIDFullShow?)
    fun setLoadDelegate(bidLoadDelegate: BIDFullLoad?)
    fun setAutoLoad(isAutoLoad: Boolean)
    fun isInterstitialReady(): Boolean
    fun load()
    fun destroy()
}



