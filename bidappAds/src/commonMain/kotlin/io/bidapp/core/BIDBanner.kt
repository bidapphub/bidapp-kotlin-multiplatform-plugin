package io.bidapp.core




public expect class BIDBanner(format: BIDAdFormat) : PlatformView {
    public val bidBannerSize: BIDAdFormat
    public fun setBannerViewDelegate(bidBannerShow: BIDBannerShow)
    public fun refresh()
    public fun startAutorefresh(interval: Double)
    public fun stopAutorefresh()
    public fun destroy()
}
public expect open class PlatformView




