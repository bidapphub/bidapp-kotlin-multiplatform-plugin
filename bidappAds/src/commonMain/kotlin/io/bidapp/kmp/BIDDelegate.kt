package io.bidapp.kmp

interface BIDFullLoad {
    fun load(info: BIDAdInfo)
    fun failLoad(info: BIDAdInfo, error:String)
}

interface BIDFullShow  {
    fun display(info: BIDAdInfo)
    fun failToDisplay(info: BIDAdInfo, error:String)
    fun click(info: BIDAdInfo)
    fun hide(info: BIDAdInfo)
    fun allNetworksFailedToDisplay(error:String)
    fun reward()
}




interface BIDBannerShow {
    fun display(info: BIDAdInfo, bidBannerView : io.bidapp.kmp.BIDBanner)
    fun failToDisplay(info: BIDAdInfo, bidBannerView : io.bidapp.kmp.BIDBanner, error:String)
    fun click(info: BIDAdInfo, bidBannerView : io.bidapp.kmp.BIDBanner)
    fun load(info: BIDAdInfo, bidBannerView : io.bidapp.kmp.BIDBanner)
    fun allNetworksFailedToDisplay(error:String)
}