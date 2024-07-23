package io.bidapp.core

import android.app.Activity
import android.view.View
import io.bidapp.sdk.AdFormat
import io.bidapp.sdk.AdInfo
import io.bidapp.sdk.BIDBannerViewDelegate
import io.bidapp.sdk.BannerView



public actual class BIDBanner actual constructor(format: BIDAdFormat) : PlatformView(
    createBanner(
    BidappAds.getActivity(), format)
)
 {
     private val _bidBannerSize = format
     public actual val bidBannerSize: BIDAdFormat
         get() = _bidBannerSize
     private var delegate: BIDBannerViewDelegate? = null
     public actual fun setBannerViewDelegate(bidBannerShow: BIDBannerShow) {
        delegate = object : BIDBannerViewDelegate {
            override fun adViewClicked(adView: BannerView, adInfo: AdInfo?) {
                bidBannerShow.click(createBidAdInfo(adInfo), this@BIDBanner)
            }
            override fun adViewDidDisplayAd(adView: BannerView, adInfo: AdInfo?) {
                bidBannerShow.display(createBidAdInfo(adInfo), this@BIDBanner)
            }
            override fun adViewDidFailToDisplayAd(
                adView: BannerView,
                adInfo: AdInfo?,
                errors: Error
            ) {
                bidBannerShow.failToDisplay(
                    createBidAdInfo(adInfo), this@BIDBanner, errors.message.toString()
                )
            }
            override fun adViewDidLoadAd(adView: BannerView, adInfo: AdInfo?) {
                bidBannerShow.load(createBidAdInfo(adInfo), this@BIDBanner)
            }
            override fun allNetworksFailedToDisplayAd(adView: BannerView) {
                bidBannerShow.allNetworksFailedToDisplay("All Networks Failed To Display Ad")
            }
        }
        (this.bannerView as BannerView).setBannerViewDelegate(delegate as BIDBannerViewDelegate)
    }


    public actual fun refresh() {
         (this.bannerView as BannerView).refreshAd()
    }

    public actual fun startAutorefresh(interval: Double) {
        (this.bannerView as BannerView).startAutoRefresh(interval)
    }

    public actual fun stopAutorefresh() {
        (this.bannerView as BannerView).stopAutoRefresh()
    }


    public actual fun destroy() {
        (this.bannerView as BannerView).destroy()
        delegate = null
        }


 }


private fun createBanner(applicationActivity: Any?, bidBannerSize: BIDAdFormat): BannerView? {
    if ((applicationActivity as? Activity) == null) return null
    return when(bidBannerSize.getAdFormat()){
        BANNER -> {
            BannerView(applicationActivity).banner(AdFormat.banner_320x50)
        }
        MREC -> {
            BannerView(applicationActivity).banner(AdFormat.banner_300x250)
        }
        LEADERBOARD -> {
            BannerView(applicationActivity).banner(AdFormat.banner_728x90)
        }
        else -> {
            log("Error - incorrect banner format")
            null
        }

    }
}


public actual open class PlatformView(public val bannerView: View?)




