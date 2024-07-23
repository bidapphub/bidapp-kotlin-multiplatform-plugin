package io.bidapp.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.bidapp.core.BIDAdFormat
import io.bidapp.core.BIDAdInfo
import io.bidapp.core.BIDBanner
import io.bidapp.core.BIDBannerShow
import io.bidapp.core.PlatformView
import io.bidapp.core.log

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
    public data object NotDisplayed : BIDBannerState()

    @Stable
    public data object Destroyed : BIDBannerState()

    @Stable
    public class ShowingWithOutAutoRefresh() : BIDBannerState()

    @Stable
    public class ShowingWithAutoRefresh : BIDBannerState() {
        private var interval: Double = 30.0
        private var isStop: Boolean = false

        public fun setInterval(interval: Double) {
            this.interval = interval
        }

        public fun getInterval(): Double {
            return this.interval
        }

        public fun stop(isStop: Boolean) {
            this.isStop = isStop
        }

        public fun isStopAutoRefresh(): Boolean {
            return this.isStop
        }
    }
}


@Composable
public fun Banner(
    state: BIDBannerState,
    bidAdFormat: BIDAdFormat,
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
    banner.setBannerViewDelegate(bannerViewDelegate)
    if (state !is BIDBannerState.Destroyed && state !is BIDBannerState.NotDisplayed) {
        CreateView(banner, bidAdFormat)
    }
    when (state) {
        is BIDBannerState.ShowingWithAutoRefresh -> {
            if (state.isStopAutoRefresh()) {
                banner.stopAutorefresh()
            } else {
                banner.startAutorefresh(state.getInterval())
            }
        }

        is BIDBannerState.ShowingWithOutAutoRefresh -> {
            banner.refresh()
        }

        is BIDBannerState.Destroyed -> {
            banner.destroy()
        }
        else -> {
            log("Undefined banner state")
        }
    }
}

@Composable
internal fun CreateView(adView: PlatformView, bannerFormat: BIDAdFormat) {
    Box(
        modifier = Modifier.size(
            if (bannerFormat.isBanner_320x50()) 320.dp else if (bannerFormat.isBanner_300x250()) 300.dp else if (bannerFormat.isBanner_728x90()) 728.dp else 0.dp,
            if (bannerFormat.isBanner_320x50()) 50.dp else if (bannerFormat.isBanner_300x250()) 250.dp else if (bannerFormat.isBanner_728x90()) 90.dp else 0.dp,
        )
    ) {
        NativeView(adView)
    }
}

@Composable
internal expect fun NativeView(adView: PlatformView)

