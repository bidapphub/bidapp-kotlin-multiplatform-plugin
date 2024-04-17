package io.bidapp.kotlin_multiplatform

import cocoapods.bidapp.BIDAdInfo
import cocoapods.bidapp.BIDBannerView
import cocoapods.bidapp.BIDBannerViewDelegateProtocol
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSError
import platform.UIKit.UIView
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
actual class BIDBanner actual constructor(applicationActivity: Any?, bidBannerSize: BIDAdFormat) {
    private var bannerViewDelegate: BIDBannerViewDelegateProtocol? = object : BIDBannerViewDelegateProtocol, NSObject(){}
    private var banner: BIDBannerView? = createBanner(bidBannerSize)
    private var bannerSize : BIDAdFormat? = bidBannerSize



    actual fun setBannerViewDelegate(bidBannerShow: BIDBannerShow) {
        bannerViewDelegate = object : BIDBannerViewDelegateProtocol, NSObject(){
            override fun bannerDidClick(banner: BIDBannerView, adInfo: BIDAdInfo) {
                bidBannerShow.click(createBidAdInfo(adInfo), this@BIDBanner)
            }

            override fun bannerDidDisplay(banner: BIDBannerView, adInfo: BIDAdInfo) {
                bidBannerShow.display(createBidAdInfo(adInfo), this@BIDBanner)
            }

            override fun bannerDidLoad(banner: BIDBannerView, adInfo: BIDAdInfo) {
                bidBannerShow.load(createBidAdInfo(adInfo), this@BIDBanner)
            }

            override fun bannerDidFailToDisplay(
                banner: BIDBannerView,
                adInfo: BIDAdInfo,
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

    actual fun refresh() {
        banner?.refreshAd()
    }

    actual fun startAutorefresh(interval: Double) {
        banner?.startAutorefresh(30.0)
    }

    actual fun stopAutorefresh() {
        banner?.stopAutorefresh()
    }


    actual fun destroy() {
        banner?.removeFromSuperview()
        banner = null
        bannerViewDelegate = null
    }

    private fun createBanner(bidBannerSize: BIDAdFormat): BIDBannerView? {
        return if (bidBannerSize.isBanner_320x50()) BIDBannerView.bannerWithFormat(cocoapods.bidapp.BIDAdFormat.banner_320x50 as cocoapods.bidapp.BIDAdFormat, bannerViewDelegate!!)
        else if (bidBannerSize.isBanner_300x250()) BIDBannerView.bannerWithFormat(cocoapods.bidapp.BIDAdFormat.banner_300x250 as cocoapods.bidapp.BIDAdFormat, bannerViewDelegate!!)
        else {
            log("Error - incorrect banner format")
            null
        }
    }

    actual fun bindBanner(container: Any?) {
        try {
            banner?.let { (container as? UIView)?.addSubview(it) }
        }catch (e:Exception){
            log("Error bind banner")
        }
    }

    actual fun getBannerSize(): BIDAdFormat? {
        return bannerSize
    }


}

