package io.bidapp.kmp


public expect class BIDBanner(
    bidBannerSize: BIDAdFormat
) : PlatformView{
    public fun setBannerViewDelegate(bidBannerShow: BIDBannerShow)
    public fun refresh()
    public fun startAutorefresh(interval: Double)
    public fun stopAutorefresh()
    public fun destroy()
}


public expect open class PlatformView



