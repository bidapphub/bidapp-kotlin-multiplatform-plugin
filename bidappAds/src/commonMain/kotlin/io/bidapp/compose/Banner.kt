package io.bidapp.compose

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.bidapp.core.BIDAdFormat
import io.bidapp.core.BIDAdInfo
import io.bidapp.core.BIDBanner
import io.bidapp.core.BIDBannerShow
import io.bidapp.core.PlatformView

@Stable
public sealed class BIDEventType {
    public abstract val adInfo: BIDAdInfo?
    @Stable
    public data class BidBannerEventTypeOnClick(override val adInfo: BIDAdInfo?) : BIDEventType()
    @Stable
    public data class BidBannerEventTypeOnLoad(override val adInfo: BIDAdInfo?) : BIDEventType()
    @Stable
    public data class BidBannerEventTypeOnDisplay(override val adInfo: BIDAdInfo?) : BIDEventType()
    @Stable
    public data class BidBannerEventTypeOnFailToDisplay(override val adInfo: BIDAdInfo?) :
        BIDEventType()
    @Stable
    public data object BidBannerEventTypeOnAllNetworksFailedToDisplay : BIDEventType() {
        override val adInfo: BIDAdInfo? = null
    }
}

@Stable
public sealed class BIDBannerState {
    @Stable
    public object NotDisplayOrDestroy : BIDBannerState()
    @Stable
    public object StopAutoRefresh : BIDBannerState()
    @Stable
    public object StartWithAutoRefresh : BIDBannerState()
    @Stable
    public class ShowOrRefresh : BIDBannerState()
}



@Composable
public fun Banner(
    state: BIDBannerState,
    bidAdFormat: BIDAdFormat,
    modifier: Modifier = Modifier,
    bannerIntervalAutoRefresh : Int = 30,
    onEvent: (BIDEventType) -> Unit
) {
    val banner = remember { BIDBanner(bidAdFormat) }
    if (state == BIDBannerState.NotDisplayOrDestroy){
        banner.destroy()
        return
    }
    val bannerViewDelegate = remember {
        object : BIDBannerShow {
            override fun display(info: BIDAdInfo, bidBannerView: BIDBanner) {
                onEvent(BIDEventType.BidBannerEventTypeOnDisplay(info))
            }

            override fun failToDisplay(
                info: BIDAdInfo,
                bidBannerView: BIDBanner,
                error: String
            ) {
                onEvent(BIDEventType.BidBannerEventTypeOnFailToDisplay(info))
            }

            override fun click(info: BIDAdInfo, bidBannerView: BIDBanner) {
                onEvent(BIDEventType.BidBannerEventTypeOnClick(info))
            }

            override fun load(info: BIDAdInfo, bidBannerView: BIDBanner) {
                onEvent(BIDEventType.BidBannerEventTypeOnLoad(info))
            }

            override fun allNetworksFailedToDisplay(error: String) {
                onEvent(BIDEventType.BidBannerEventTypeOnAllNetworksFailedToDisplay)
            }
        }
    }
    LaunchedEffect(state) {
        banner.setBannerViewDelegate(bannerViewDelegate)
        when (state) {
            is BIDBannerState.ShowOrRefresh -> {
                banner.refresh()
            }
            is BIDBannerState.StopAutoRefresh -> {
                banner.stopAutorefresh()
            }

            is BIDBannerState.StartWithAutoRefresh -> {
                banner.startAutorefresh(bannerIntervalAutoRefresh.toDouble())
            }
            else -> {}
        }
    }

    val modifierWithBannerSize = when {
        bidAdFormat.isBanner_320x50() -> modifier.width(320.dp).height(50.dp)
        bidAdFormat.isBanner_300x250() -> modifier.width(300.dp).height(250.dp)
        bidAdFormat.isBanner_728x90() -> modifier.width(728.dp).height(90.dp)
        else -> modifier
    }
    NativeView(banner, modifierWithBannerSize)
}


@Composable
internal expect fun NativeView(adView: PlatformView, modifier: Modifier)



