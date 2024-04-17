package io.bidapp.kotlin_multiplatform

import cocoapods.bidapp.loadSessionId
import cocoapods.bidapp.networkId
import cocoapods.bidapp.showSessionId
import cocoapods.bidapp.waterfallId
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
fun createBidAdInfo(adInfo: cocoapods.bidapp.BIDAdInfo): BIDAdInfo {
    return BIDAdInfo(
        adInfo.format.name,
        null,
        adInfo.networkId,
        adInfo.waterfallId,
        adInfo.loadSessionId,
        adInfo.showSessionId
    )
}