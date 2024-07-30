package io.bidapp.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
    public object BidBannerEventTypeOnAllNetworksFailedToDisplay : BIDEventType() {
        override val adInfo: BIDAdInfo? = null
    }
}

@Stable
public sealed class BIDBannerState {
    @Stable
    public data object NotDisplayOrDestroy : BIDBannerState()

    @Stable
    public data object StopAutoRefresh : BIDBannerState()

    @Stable
    public class ShowWithRefresh() : BIDBannerState()

    @Stable
    public data class StartAutoRefresh(public var interval : Double) : BIDBannerState()
}


@Composable
public fun Banner(
    state: BIDBannerState,
    bidAdFormat: BIDAdFormat,
    modifier: Modifier = Modifier,
    onEvent: (BIDEventType) -> Unit
) {
    val banner = remember { BIDBanner(bidAdFormat) }
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
            is BIDBannerState.NotDisplayOrDestroy -> {
                banner.destroy()
            }

            is BIDBannerState.ShowWithRefresh -> {
                banner.refresh()
            }

            is BIDBannerState.StopAutoRefresh -> {
                banner.stopAutorefresh()
            }

            is BIDBannerState.StartAutoRefresh -> {
                banner.startAutorefresh(state.interval)
            }
        }
    }
    NativeView(banner, modifier)
}


@Composable
internal expect fun NativeView(adView: PlatformView, modifier: Modifier)



