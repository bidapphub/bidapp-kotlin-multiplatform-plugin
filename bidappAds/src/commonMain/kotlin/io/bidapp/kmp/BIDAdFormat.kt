package io.bidapp.kmp


public const val INTERSTITIAL: String = "interstitial"
public const val REWARDED: String = "rewarded"
public const val BANNER: String = "banner"
public const val MREC: String = "mrec"
public const val LEADERBOARD: String = "leaderboard"

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
        return currentAdFormat == BANNER
    }

    public fun isBanner_300x250():Boolean{
        return currentAdFormat == MREC
    }

    public fun isBanner_728x90():Boolean{
        return currentAdFormat == LEADERBOARD
    }

    public fun isInterstitial():Boolean{
        return currentAdFormat == INTERSTITIAL
    }

    public fun isRewarded():Boolean{
        return currentAdFormat == REWARDED
    }

    public companion object {
        public val interstitial: BIDAdFormat = BIDAdFormat().setAdFormat(INTERSTITIAL)
        public val rewarded: BIDAdFormat = BIDAdFormat().setAdFormat(REWARDED)
        public val banner_320x50: BIDAdFormat = BIDAdFormat().setAdFormat(BANNER)
        public val banner_300x250: BIDAdFormat = BIDAdFormat().setAdFormat(MREC)
        public val banner_728x90: BIDAdFormat = BIDAdFormat().setAdFormat(LEADERBOARD)
    }
}