package io.bidapp.kmp

import android.app.Activity
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import io.bidapp.kmp.BIDAdFormat
import io.bidapp.kmp.BIDBannerShow
import io.bidapp.sdk.AdFormat
import io.bidapp.sdk.AdInfo
import io.bidapp.sdk.BIDBannerViewDelegate
import io.bidapp.sdk.BannerView



public actual class BIDBanner actual constructor(applicationActivity: Any?, bidBannerSize: BIDAdFormat) {
    private var banner: BannerView? = createBanner(applicationActivity, bidBannerSize)
    private var delegate: BIDBannerViewDelegate? = null
    private var bannerSize : BIDAdFormat? = bidBannerSize


    public actual fun setBannerViewDelegate(bidBannerShow: BIDBannerShow) {
        if (banner != null) {
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
            banner?.setBannerViewDelegate(delegate as BIDBannerViewDelegate)
        }
    }


    public actual fun refresh() {
         banner?.refreshAd()
    }

    public actual fun startAutorefresh(interval: Double) {
        banner?.startAutoRefresh(interval)
    }

    public actual fun stopAutorefresh() {
        banner?.stopAutoRefresh()
    }


    public actual fun destroy() {
        delegate = null
        banner?.destroy()
        banner = null
        bannerSize = null
    }

    private fun createBanner(applicationActivity: Any?, bidBannerSize: BIDAdFormat): BannerView? {
        if ((applicationActivity as? Activity) == null) return null
        return if (bidBannerSize.isBanner_300x250()) BannerView(applicationActivity).banner(
            AdFormat.banner_300x250
        ) else if (bidBannerSize.isBanner_320x50()) BannerView(applicationActivity).banner(
            AdFormat.banner_320x50
        )
        else {
            log("Error - incorrect banner format")
            null
        }
    }


    public actual fun bindBanner(container: Any?) {
        when(container){
            container as? FrameLayout -> {
                (container as? FrameLayout)?.addView(banner)
            }
            container as? ConstraintLayout -> {
                (container as? ConstraintLayout)?.addView(banner)
            }
            else -> log("Error bind banner - use FrameLayout or ConstraintLayout for bind")
        }
    }

    public actual fun getBannerSize(): BIDAdFormat? {
        return bannerSize
    }
}
