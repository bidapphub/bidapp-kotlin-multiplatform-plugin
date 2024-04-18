package io.bidapp.kmp


expect class BIDBanner(applicationActivity: Any?, bidBannerSize: BIDAdFormat){
    fun bindBanner(container : Any?)
    fun getBannerSize() : BIDAdFormat?
    fun setBannerViewDelegate(bidBannerShow: BIDBannerShow)
    fun refresh()
    fun startAutorefresh(interval: Double)
    fun stopAutorefresh()
    fun destroy()
}



