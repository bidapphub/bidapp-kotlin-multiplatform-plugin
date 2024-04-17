package io.bidapp.demo.UI

import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.bidapp.demo.Data.BIDAppAdsData

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
fun ButtonAds(bidappAdsData:BIDAppAdsData,
              activityOrUIViewController : Any?,
              view: MutableState<Any?>,
              isAutoRefreshOn : MutableState<Boolean>,
              displayBanner : MutableState<Boolean>,
              isInterstitialLoad : MutableState<Boolean>,
              isRewardedLoad : MutableState<Boolean>){
    But(
        text = "Show Interstitial",
        enabled = isInterstitialLoad.value,
        onClick = {
            bidappAdsData.getInterstitial()
                ?.showInterstitial(activityOrUIViewController, bidappAdsData.getFullShow())
        }
    )
    But(
        text = "Show Rewarded",
        enabled = isRewardedLoad.value,
        onClick = {
            bidappAdsData.getRewarded()
                ?.showRewarded(activityOrUIViewController, bidappAdsData.getFullShow())
        }
    )
    But(
        text = if (!displayBanner.value) "Show Banner" else "Refresh Banner",
        enabled = true,
        onClick = {
            if (bidappAdsData.getBanner() == null) {
                bidappAdsData.createBanner(activityOrUIViewController)
                bidappAdsData.getBanner()?.bindBanner(view.value)
            }
            bidappAdsData.getBanner()?.refresh()
        }
    )
    But(
        text = if (!isAutoRefreshOn.value) "Start Auto Refresh Banner" else "Stop Auto Refresh Banner",
        enabled = displayBanner.value,
        onClick = {
            if (!isAutoRefreshOn.value) {
                bidappAdsData.getBanner()?.startAutorefresh(30.0)
                isAutoRefreshOn.value = true
            } else {
                bidappAdsData.getBanner()?.stopAutorefresh()
                isAutoRefreshOn.value = false
            }
        }
    )
    But(
        text = "Destroy Banner",
        enabled = bidappAdsData.getBanner() != null && displayBanner.value,
        onClick = {
            bidappAdsData.destroyBanner()
            displayBanner.value = false
            isAutoRefreshOn.value = false
        }
    )
}

