package com.bidapp.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import io.bidapp.demo.Data.BIDAppAdsData
import io.bidapp.demo.MainView
import io.bidapp.demo.Data.bidappInit


class MainActivity : ComponentActivity() {
    var bidappAdsData: BIDAppAdsData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bidappInit(this)
        bidappAdsData = BIDAppAdsData()
        bidappAdsData?.initialization(this)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView(bidappAdsData = bidappAdsData!!, activity = this)
                }
            }
        }
    }
}






