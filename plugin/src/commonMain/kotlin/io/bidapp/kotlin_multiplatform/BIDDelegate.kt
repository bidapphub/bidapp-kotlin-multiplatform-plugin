package io.bidapp.kotlin_multiplatform

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
    fun display(info: BIDAdInfo, bidBannerView : BIDBanner)
    fun failToDisplay(info: BIDAdInfo, bidBannerView : BIDBanner, error:String)
    fun click(info: BIDAdInfo, bidBannerView : BIDBanner)
    fun load(info: BIDAdInfo, bidBannerView : BIDBanner)
    fun allNetworksFailedToDisplay(error:String)
}