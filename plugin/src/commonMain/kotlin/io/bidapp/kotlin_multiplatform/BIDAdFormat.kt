package io.bidapp.kotlin_multiplatform


const val Interstitial = "interstitial"
const val Rewarded = "rewarded"
const val Banner = "banner"
const val Mrec = "mrec"

class BIDAdFormat {
    private var currentAdFormat : String? = null
    internal fun setAdFormat(adFormat : String) : BIDAdFormat {
        this.currentAdFormat = adFormat
        return this
    }
    internal fun getAdFormat() : String {
        return currentAdFormat ?: "UNKNOWN"
    }
    fun isBanner_320x50():Boolean{
        return currentAdFormat == Banner
    }

    fun isBanner_300x250():Boolean{
        return currentAdFormat == Mrec
    }

    fun isInterstitial():Boolean{
        return currentAdFormat == Interstitial
    }

    fun isRewarded():Boolean{
        return currentAdFormat == Rewarded
    }

    companion object {
        val interstitial = BIDAdFormat().setAdFormat(Interstitial)
        val rewarded = BIDAdFormat().setAdFormat(Rewarded)
        val banner_320x50 = BIDAdFormat().setAdFormat(Banner)
        val banner_300x250 = BIDAdFormat().setAdFormat(Mrec)
    }
}