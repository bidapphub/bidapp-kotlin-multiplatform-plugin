package io.bidapp.kmp

import io.bidapp.sdk.AdInfo

fun createBidAdInfo(adInfo: AdInfo?): BIDAdInfo {
    return BIDAdInfo(
        adInfo?.adFormat?.currentFormat(),
        adInfo?.getAdTag(),
        adInfo?.getNetworkId(),
        adInfo?.getWaterfallId(),
        adInfo?.getLoadSessionId(),
        adInfo?.getShowSessionId()
    )
}