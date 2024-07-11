package io.bidapp.demo.UI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.bidapp.demo.Data.BIDAppAdsData
import io.bidapp.kmp.BIDBanner

@Composable
fun Banner(bidappAdsData : BIDAppAdsData, view : MutableState<Any?>){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CreateBannerPlace(bidappAdsData.getBanner()) {
                view.value = it
            }
    }
}

@Composable
expect fun CreateBannerPlace(banner: BIDBanner?, onsuccess: (view: Any) -> Unit)


expect fun addBanner(view: Any, banner: BIDBanner?)

expect fun removeBanner(view: Any)