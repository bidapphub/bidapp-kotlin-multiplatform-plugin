package io.bidapp.core

public class BIDConfiguration {
 public var isLoggingEnable : Boolean? = null
 public var isTestModeEnable : Boolean? = null

 internal var arrayNetworkSDKKey : ArrayList<NetworkSDKKey>? = null
 internal var arrayNetworkAdTag : ArrayList<NetworkAdTag>? = null

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


 public fun setAdTag(adTag : String, secondTag: String? = null, networkId: BIDNetworkId, adFormat: BIDAdFormat, epcm: Double, isInAppBidding : Boolean){
  if (arrayNetworkAdTag == null)
   arrayNetworkAdTag = arrayListOf()
   arrayNetworkAdTag!!.add(NetworkAdTag(adTag, secondTag, networkId, adFormat, epcm, isInAppBidding))
  }
}





public data class NetworkSDKKey(var sdkKey : String, var networkId : BIDNetworkId, var secondKey : String?)
public data class NetworkAdTag(var adTag : String, var secondTag : String? = null, var networkId : BIDNetworkId, var adFormat: BIDAdFormat, var ecpm : Double, var isInAppBidding : Boolean)


