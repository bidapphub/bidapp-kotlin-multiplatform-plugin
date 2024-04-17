package io.bidapp.kotlin_multiplatform


expect object BidappAds {
    fun start(pubId:String, bidConfiguration: BIDConfiguration, applicationContext:Any?)
}



