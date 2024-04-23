package io.bidapp.kmp


public expect class BIDInterstitial(activity : Any?){
    public fun showInterstitial(activity: Any?, bidShowDelegate : BIDFullShow?)
    public fun setLoadDelegate(bidLoadDelegate: BIDFullLoad?)
    public fun setAutoLoad(isAutoLoad: Boolean)
    public fun isInterstitialReady(): Boolean
    public fun load()
    public fun destroy()
}



