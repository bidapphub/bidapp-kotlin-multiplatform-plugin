package io.bidapp.demo.UI

import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.bidapp.compose.BIDBannerState
import io.bidapp.demo.AdsDataViewModel

@Composable
fun But(text: String, enabled: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (enabled) Color.Green else Color.Gray
        ),
        modifier = Modifier
            .width(300.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun ButtonAds(adsAppData: AdsDataViewModel) {
    But(
        text = "Show Interstitial",
        enabled = adsAppData.isInterstitialLoad,
        onClick = {
            adsAppData.showInterstitial()
        }
    )
    But(
        text = "Show Rewarded",
        enabled = adsAppData.isRewardedLoad,
        onClick = {
            adsAppData.showRewarded()
        }
    )
    But(
        text = if (!adsAppData.displayBanner) "Show Banner" else "Refresh Banner",
        enabled = true,
        onClick = {
            adsAppData.bannerState = BIDBannerState.ShowingWithOutAutoRefresh()
        }
    )
    But(
        text = if (adsAppData.bannerState is BIDBannerState.ShowingWithAutoRefresh && (adsAppData.bannerState as BIDBannerState.ShowingWithAutoRefresh).isStopAutoRefresh()) "Start Auto Refresh Banner" else "Stop Auto Refresh Banner",
        enabled = adsAppData.displayBanner,
        onClick = {
            if (adsAppData.bannerState is BIDBannerState.ShowingWithAutoRefresh && (adsAppData.bannerState as BIDBannerState.ShowingWithAutoRefresh).isStopAutoRefresh()) {
                val startAutoRefresh = BIDBannerState.ShowingWithAutoRefresh()
                startAutoRefresh.setInterval(30.0)
                adsAppData.bannerState = startAutoRefresh
            } else {
                val stopAutoRefresh = BIDBannerState.ShowingWithAutoRefresh()
                stopAutoRefresh.stop(true)
                adsAppData.bannerState = stopAutoRefresh
            }
        }
    )
    But(
        text = "Destroy Banner",
        enabled = adsAppData.displayBanner,
        onClick = {
            adsAppData.bannerState = BIDBannerState.Destroyed
            adsAppData.displayBanner = false
        }
    )
}

