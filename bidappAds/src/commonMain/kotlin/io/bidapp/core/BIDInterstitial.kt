package io.bidapp.core




public expect class BIDInterstitial(){
    public fun showInterstitial(bidShowDelegate : BIDFullShow?)
    public fun setLoadDelegate(bidLoadDelegate: BIDFullLoad?)
    public fun setAutoLoad(isAutoLoad: Boolean)
    public fun isInterstitialReady(): Boolean
    public fun load()
    public fun destroy()
}



