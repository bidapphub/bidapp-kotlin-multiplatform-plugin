package io.bidapp.demo.UI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.bidapp.demo.Data.BIDAppAdsData
import io.bidapp.kotlin_multiplatform.BIDBanner

@Composable
fun Banner(bidappAdsData : BIDAppAdsData, view : MutableState<Any?>){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            ShowBanner(bidappAdsData.getBanner()) {
                view.value = it
            }
    }
}

@Composable
expect fun ShowBanner(banner: BIDBanner?, onsuccess: (view: Any) -> Unit)