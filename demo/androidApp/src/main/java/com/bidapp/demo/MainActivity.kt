package com.bidapp.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import io.bidapp.demo.Data.BIDAppAds
import io.bidapp.demo.Data.BIDAppAdsData
import io.bidapp.demo.MainView



class MainActivity : ComponentActivity() {
    private var bidappAdsData: BIDAppAdsData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BIDAppAds.apply {
            start(this@MainActivity.applicationContext)
            setActivity(this@MainActivity)
        }
        bidappAdsData = BIDAppAdsData()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    bidappAdsData?.let { MainView(bidappAdsData = it) }
                }
            }
        }
    }
}






