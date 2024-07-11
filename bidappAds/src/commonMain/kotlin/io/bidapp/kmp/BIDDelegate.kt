package io.bidapp.kmp

public interface BIDFullLoad {
    public fun load(info: BIDAdInfo)
    public fun failLoad(info: BIDAdInfo, error:String)
}

public interface BIDFullShow  {
    public fun display(info: BIDAdInfo)
    public fun failToDisplay(info: BIDAdInfo, error:String)
    public fun click(info: BIDAdInfo)
    public fun hide(info: BIDAdInfo)
    public fun allNetworksFailedToDisplay(error:String)
    public fun reward()
}




public interface BIDBannerShow {
    public fun display(info: BIDAdInfo, bidBannerView : BIDBanner)
    public fun failToDisplay(info: BIDAdInfo, bidBannerView : BIDBanner, error:String)
    public fun click(info: BIDAdInfo, bidBannerView : BIDBanner)
    public fun load(info: BIDAdInfo, bidBannerView : BIDBanner)
    public fun allNetworksFailedToDisplay(error:String)
}