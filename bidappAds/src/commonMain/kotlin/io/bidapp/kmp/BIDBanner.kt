package io.bidapp.kmp


public expect class BIDBanner(applicationActivity: Any?, bidBannerSize: BIDAdFormat){
    public fun bindBanner(container : Any?)
    public fun getBannerSize() : BIDAdFormat?
    public fun setBannerViewDelegate(bidBannerShow: BIDBannerShow)
    public fun refresh()
    public fun startAutorefresh(interval: Double)
    public fun stopAutorefresh()
    public fun destroy()
}



