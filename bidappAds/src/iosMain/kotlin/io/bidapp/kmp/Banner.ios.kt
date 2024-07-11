package io.bidapp.kmp


import cocoapods.bidapp.BIDBannerView
import cocoapods.bidapp.BIDBannerViewDelegateProtocol
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSError
import platform.UIKit.UIView
import platform.darwin.NSObject


@OptIn(ExperimentalForeignApi::class)
public actual class BIDBanner public actual constructor(public val bidBannerSize: BIDAdFormat) : PlatformView(
    createBanner(bidBannerSize) as UIView) {
    private var bannerViewDelegate: BIDBannerViewDelegateProtocol? = object : BIDBannerViewDelegateProtocol, NSObject(){}
    private var banner: BIDBannerView? = createBanner(bidBannerSize)


    public actual fun setBannerViewDelegate(bidBannerShow: BIDBannerShow) {
        bannerViewDelegate = object : BIDBannerViewDelegateProtocol, NSObject(){
            override fun bannerDidClick(banner: BIDBannerView, adInfo: cocoapods.bidapp.BIDAdInfo) {
                bidBannerShow.click(createBidAdInfo(adInfo), this@BIDBanner)
            }

            override fun bannerDidDisplay(banner: BIDBannerView, adInfo: cocoapods.bidapp.BIDAdInfo) {
                bidBannerShow.display(createBidAdInfo(adInfo), this@BIDBanner)
            }

            override fun bannerDidLoad(banner: BIDBannerView, adInfo: cocoapods.bidapp.BIDAdInfo) {
                bidBannerShow.load(createBidAdInfo(adInfo), this@BIDBanner)
            }

            override fun bannerDidFailToDisplay(
                banner: BIDBannerView,
                adInfo: cocoapods.bidapp.BIDAdInfo,
                error: NSError
            ) {
                bidBannerShow.failToDisplay(createBidAdInfo(adInfo), this@BIDBanner, error.localizedDescription())
            }

            override fun allNetworksFailedToDisplayAdInBanner(banner: BIDBannerView) {
                bidBannerShow.allNetworksFailedToDisplay("AllNetworksFailedToDisplayAdInBanner")
            }


        }
        banner?.setDelegate(bannerViewDelegate)
    }

    public actual fun refresh() {
        banner?.refreshAd()
    }

    public actual fun startAutorefresh(interval: Double) {
        banner?.startAutorefresh(30.0)
    }

    public actual fun stopAutorefresh() {
        banner?.stopAutorefresh()
    }


    public actual fun destroy() {
        banner?.removeFromSuperview()
        banner = null
        bannerViewDelegate = null
    }


}

@OptIn(ExperimentalForeignApi::class)
private fun createBanner(bidBannerSize: BIDAdFormat): BIDBannerView? {
    return when(bidBannerSize.getAdFormat()){
        BANNER -> {
            BIDBannerView.bannerWithFormat(cocoapods.bidapp.BIDAdFormat.banner_320x50 as cocoapods.bidapp.BIDAdFormat, object : BIDBannerViewDelegateProtocol, NSObject(){})
        }
        MREC -> {
            BIDBannerView.bannerWithFormat(cocoapods.bidapp.BIDAdFormat.banner_300x250 as cocoapods.bidapp.BIDAdFormat, object : BIDBannerViewDelegateProtocol, NSObject(){})
        }
        LEADERBOARD -> {
            BIDBannerView.bannerWithFormat(cocoapods.bidapp.BIDAdFormat.banner_728x90 as cocoapods.bidapp.BIDAdFormat, object : BIDBannerViewDelegateProtocol, NSObject(){})
        }
        else -> {
            log("Error - incorrect banner format")
            null
        }
    }
}

public actual open class PlatformView(public val bannerView: UIView)

