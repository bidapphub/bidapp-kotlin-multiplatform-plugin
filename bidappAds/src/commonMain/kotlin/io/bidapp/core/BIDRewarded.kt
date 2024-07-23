package io.bidapp.core




public expect class BIDRewarded(){
    public fun showRewarded(bidShowDelegate : BIDFullShow?)
    public fun setLoadDelegate(bidLoadDelegate: BIDFullLoad?)
    public fun setAutoLoad(isAutoLoad: Boolean)
    public fun isInterstitialReady(): Boolean
    public fun load()
    public fun destroy()
}

