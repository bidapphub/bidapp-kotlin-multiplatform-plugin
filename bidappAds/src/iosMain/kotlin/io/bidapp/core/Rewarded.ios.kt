package io.bidapp.core
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
public actual class BIDRewarded actual constructor(){
    private var rewarded : BIDRewarded? = BIDRewarded()
    private var rewardedShowDelegate : BIDRewardedDelegateProtocol? = null
    private var rewardedLoadDelegate : BIDFullscreenLoadDelegateProtocol? = null
    public actual fun showRewarded(
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

    public actual fun setLoadDelegate(bidLoadDelegate: BIDFullLoad?) {
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

    public actual fun setAutoLoad(isAutoLoad: Boolean) {
        rewarded?.autoload = isAutoLoad
    }

    public actual fun isInterstitialReady(): Boolean {
        return rewarded?.isAdReady() ?: false
    }

    public actual fun load() {
        rewarded?.load()
    }

    public actual fun destroy() {
        rewarded = null
        rewardedLoadDelegate = null
        rewardedShowDelegate = null
    }

}


