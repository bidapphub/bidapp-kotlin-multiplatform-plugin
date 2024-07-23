package io.bidapp.demo


import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.runtime.Composable
import io.bidapp.core.BidappAds


@SuppressLint("UnrememberedMutableState")
@Composable
fun MainView(activity: Activity) {
    BidappAds.setActivity(activity)
    App()

}




actual fun log(message: String) {
    Log.d("bidappMPDemo", message)
}

