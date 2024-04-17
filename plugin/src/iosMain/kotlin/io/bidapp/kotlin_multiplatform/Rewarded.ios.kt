package io.bidapp.kotlin_multiplatform
import cocoapods.bidapp.BIDAdInfo
import cocoapods.bidapp.BIDFullscreenLoadDelegateProtocol
import cocoapods.bidapp.BIDRewarded
import cocoapods.bidapp.BIDRewardedDelegateProtocol
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSError
import platform.UIKit.UIApplication
import platform.UIKit.UIViewController
import platform.darwin.NSObject




@OptIn(ExperimentalForeignApi::class)
actual class BIDRewarded actual constructor(activity : Any?){
    private var rewarded : BIDRewarded? = BIDRewarded()
    private var rewardedShowDelegate : BIDRewardedDelegateProtocol? = null
    private var rewardedLoadDelegate : BIDFullscreenLoadDelegateProtocol? = null
    actual fun showRewarded(
        applicationActivity: Any?,
        bidShowDelegate: BIDFullShow?
    ) {
        rewardedShowDelegate = object : BIDRewardedDelegateProtocol, NSObject(){
            override fun allNetworksDidFailToDisplayAd() {
                bidShowDelegate?.allNetworksFailedToDisplay("All networks did fail to display ad")
            }

            override fun didClickAd(adInfo: BIDAdInfo) {
                bidShowDelegate?.click(createBidAdInfo(adInfo))
            }

            override fun didDisplayAd(adInfo: BIDAdInfo) {
                bidShowDelegate?.display(createBidAdInfo(adInfo))
            }

            override fun didFailToDisplayAd(adInfo: BIDAdInfo, error: NSError) {
                bidShowDelegate?.failToDisplay(createBidAdInfo(adInfo), error.localizedDescription)
            }

            override fun didHideAd(adInfo: BIDAdInfo) {
                bidShowDelegate?.hide(createBidAdInfo(adInfo))
            }

            override fun didRewardUser() {
                bidShowDelegate?.reward()
            }

            override fun viewControllerForDisplayAd(): UIViewController {
                return UIApplication.sharedApplication.keyWindow?.rootViewController!!
            }
        }

        rewarded?.showWithDelegate(rewardedShowDelegate as BIDRewardedDelegateProtocol)
    }

    actual fun setLoadDelegate(bidLoadDelegate: BIDFullLoad?) {
        rewardedLoadDelegate = object : BIDFullscreenLoadDelegateProtocol, NSObject(){
            override fun didFailToLoadAd(adInfo: BIDAdInfo, error: NSError) {
                bidLoadDelegate?.failLoad(createBidAdInfo(adInfo), error.localizedDescription())
            }

            override fun didLoadAd(adInfo: BIDAdInfo) {
                bidLoadDelegate?.load(createBidAdInfo(adInfo))
            }
        }
        rewarded?.setLoadDelegate(rewardedLoadDelegate)
    }

    actual fun setAutoLoad(isAutoLoad: Boolean) {
        rewarded?.autoload = isAutoLoad
    }

    actual fun isInterstitialReady(): Boolean {
        return rewarded?.isAdReady() ?: false
    }

    actual fun load() {
        rewarded?.load()
    }

    actual fun destroy() {
        rewarded = null
        rewardedLoadDelegate = null
        rewardedShowDelegate = null
    }

}


