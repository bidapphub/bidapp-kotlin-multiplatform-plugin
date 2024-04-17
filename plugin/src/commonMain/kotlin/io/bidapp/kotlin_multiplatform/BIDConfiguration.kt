package io.bidapp.kotlin_multiplatform

class BIDConfiguration {
 var isInterstitialEnable : Boolean? = null
 var isRewardedEnable : Boolean? = null
 var isBannerEnable : Boolean? = null
 var isLoggingEnable : Boolean? = null
 var isTestModeEnable : Boolean? = null
 internal var arrayNetworkSDKKey : ArrayList<NetworkSDKKey>? = null
 internal var arrayNetworkAdTag : ArrayList<NetworkAdTag>? = null

 fun enableInterstitialAds(){
  isInterstitialEnable = true
 }
 fun enableRewardedAds(){
  isRewardedEnable = true
 }
 fun enableBannerAds(){
  isBannerEnable = true
 }
 fun enableLoggingAds(){
  isLoggingEnable = true
 }
 fun enableTestModeAds(){
  isTestModeEnable = true
 }

 fun setSDKKey(sdkKey: String, networkId: BIDNetworkId, secondKey: String?){
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


 fun setAdTag(adTag : String, networkId: BIDNetworkId, adFormat: BIDAdFormat, epcm: Double){
  if (arrayNetworkAdTag == null)
   arrayNetworkAdTag = arrayListOf()
   arrayNetworkAdTag!!.add(NetworkAdTag(adTag, networkId, adFormat, epcm))
  }
}





data class NetworkSDKKey(var sdkKey : String, var networkId : BIDNetworkId, var secondKey : String?)
data class NetworkAdTag(var adTag : String, var networkId : BIDNetworkId, var adFormat: BIDAdFormat, var ecpm : Double)


