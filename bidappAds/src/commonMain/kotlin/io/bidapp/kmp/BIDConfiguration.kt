package io.bidapp.kmp

public class BIDConfiguration {
 public var isInterstitialEnable : Boolean? = null
 public var isRewardedEnable : Boolean? = null
 public var isBannerEnable : Boolean? = null
 public var isLoggingEnable : Boolean? = null
 public var isTestModeEnable : Boolean? = null
 internal var arrayNetworkSDKKey : ArrayList<NetworkSDKKey>? = null
 internal var arrayNetworkAdTag : ArrayList<NetworkAdTag>? = null

 public fun enableInterstitialAds(){
  isInterstitialEnable = true
 }
 public fun enableRewardedAds(){
  isRewardedEnable = true
 }
 public fun enableBannerAds(){
  isBannerEnable = true
 }
 public fun enableLoggingAds(){
  isLoggingEnable = true
 }
 public fun enableTestModeAds(){
  isTestModeEnable = true
 }

 public fun setSDKKey(sdkKey: String, networkId: BIDNetworkId, secondKey: String?){
    if (arrayNetworkSDKKey == null) {
     arrayNetworkSDKKey = arrayListOf()
     arrayNetworkSDKKey!!.add(NetworkSDKKey(sdkKey, networkId, secondKey))
    }
    else{
     val existingNetwork = arrayNetworkSDKKey?.find { it.networkId == networkId }
     if (existingNetwork != null) {
      existingNetwork.sdkKey = sdkKey
      existingNetwork.secondKey = secondKey
     } else {
      arrayNetworkSDKKey?.add(NetworkSDKKey(sdkKey, networkId, secondKey))
     }
    }
 }


 public fun setAdTag(adTag : String, networkId: BIDNetworkId, adFormat: BIDAdFormat, epcm: Double){
  if (arrayNetworkAdTag == null)
   arrayNetworkAdTag = arrayListOf()
   arrayNetworkAdTag!!.add(NetworkAdTag(adTag, networkId, adFormat, epcm))
  }
}





public data class NetworkSDKKey(var sdkKey : String, var networkId : BIDNetworkId, var secondKey : String?)
public data class NetworkAdTag(var adTag : String, var networkId : BIDNetworkId, var adFormat: BIDAdFormat, var ecpm : Double)


