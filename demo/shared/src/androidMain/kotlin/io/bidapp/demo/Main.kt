package io.bidapp.demo


import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.compose.runtime.Composable
import androidx.core.os.postDelayed
import io.bidapp.demo.Data.BIDAppAdsData



@Composable
fun MainView(bidappAdsData: BIDAppAdsData) {
    App(bidappAdsData)
}

actual fun requestLayout(view:Any?) {
    Handler(Looper.getMainLooper()).postDelayed(200) {
        (view as? View)?.rootView?.requestLayout()
    }

}


actual fun log(message: String) {
    Log.d("bidappMPDemo", message)
}

