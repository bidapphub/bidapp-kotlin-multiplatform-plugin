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
import io.bidapp.demo.OnClickEvent
import io.bidapp.demo.StateButton


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
fun ButtonAds(stateButton: StateButton, onClick: (OnClickEvent) -> Unit) {
    But(
        text = "Show Interstitial",
        enabled = stateButton.isEnableInterstitial,
        onClick = {
            onClick(OnClickEvent.SHOW_INTERSTITIAL)
        }
    )
    But(
        text = "Show Rewarded",
        enabled = stateButton.isEnableRewarded,
        onClick = {
            onClick(OnClickEvent.SHOW_REWARDED)
        }
    )
    But(
        text = if (stateButton.isShowingBanner) "Refresh Banner" else "Show Banner",
        enabled = true,
        onClick = {
            onClick(OnClickEvent.SHOW_REFRESH_BANNER)
        }
    )
    But(
        text = if (stateButton.isAutoRefresh) "Stop Auto Refresh Banner" else "Start Auto Refresh Banner",
        enabled = stateButton.isShowingBanner,
        onClick = {
            onClick(OnClickEvent.START_STOP_AUTO_REFRESH_BANNER)
        }
    )
    But(
        text = "Destroy Banner",
        enabled = stateButton.isShowingBanner,
        onClick = {
            onClick(OnClickEvent.DESTROY_BANNER)
        }
    )
}


