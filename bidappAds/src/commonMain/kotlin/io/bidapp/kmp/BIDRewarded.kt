package io.bidapp.kmp

public expect class BIDRewarded(activity : Any?){
    public fun showRewarded(applicationActivity: Any?, bidShowDelegate : BIDFullShow?)
    public fun setLoadDelegate(bidLoadDelegate: BIDFullLoad?)
    public fun setAutoLoad(isAutoLoad: Boolean)
    public fun isInterstitialReady(): Boolean
    public fun load()
    public fun destroy()
}

