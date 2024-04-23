package io.bidapp.kmp


public const val Interstitial: String = "interstitial"
public const val Rewarded: String = "rewarded"
public const val Banner: String = "banner"
public const val Mrec: String = "mrec"

public class BIDAdFormat {
    private var currentAdFormat : String? = null
    internal fun setAdFormat(adFormat : String) : BIDAdFormat {
        this.currentAdFormat = adFormat
        return this
    }
    internal fun getAdFormat() : String {
        return currentAdFormat ?: "UNKNOWN"
    }
    public fun isBanner_320x50():Boolean{
        return currentAdFormat == Banner
    }

    public fun isBanner_300x250():Boolean{
        return currentAdFormat == Mrec
    }

    public fun isInterstitial():Boolean{
        return currentAdFormat == Interstitial
    }

    public fun isRewarded():Boolean{
        return currentAdFormat == Rewarded
    }

    public companion object {
        public val interstitial: BIDAdFormat = BIDAdFormat().setAdFormat(Interstitial)
        public val rewarded: BIDAdFormat = BIDAdFormat().setAdFormat(Rewarded)
        public val banner_320x50: BIDAdFormat = BIDAdFormat().setAdFormat(Banner)
        public val banner_300x250: BIDAdFormat = BIDAdFormat().setAdFormat(Mrec)
    }
}