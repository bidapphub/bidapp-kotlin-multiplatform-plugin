package io.bidapp.demo


import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import io.bidapp.core.BidappAds


@SuppressLint("UnrememberedMutableState")
@Composable
fun MainView() {
    BidappAds.setActivity(LocalView.current.context as Activity)
    App()
}




actual fun log(message: String) {
    Log.d("bidappMPDemo", message)
}

