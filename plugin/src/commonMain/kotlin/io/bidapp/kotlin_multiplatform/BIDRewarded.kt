package io.bidapp.kotlin_multiplatform

expect class BIDRewarded(activity : Any?){
    fun showRewarded(applicationActivity: Any?, bidShowDelegate : BIDFullShow?)
    fun setLoadDelegate(bidLoadDelegate: BIDFullLoad?)
    fun setAutoLoad(isAutoLoad: Boolean)
    fun isInterstitialReady(): Boolean
    fun load()
    fun destroy()
}

