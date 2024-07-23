package io.bidapp.core


import io.bidapp.sdk.AdInfo

public fun createBidAdInfo(adInfo: AdInfo?): BIDAdInfo {
    return BIDAdInfo(
        adInfo?.adFormat?.currentFormat(),
        adInfo?.getAdTag(),
        adInfo?.getNetworkId(),
        adInfo?.getWaterfallId(),
        adInfo?.getLoadSessionId(),
        adInfo?.getShowSessionId()
    )
}