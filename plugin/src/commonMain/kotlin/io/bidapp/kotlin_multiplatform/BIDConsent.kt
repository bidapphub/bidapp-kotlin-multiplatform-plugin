package io.bidapp.kotlin_multiplatform

object BidConsent {
    fun setCCPA(CCPA:Boolean){
        io.bidapp.kotlin_multiplatform.setCCPA(CCPA)
    }
    fun setCOPPA(COPPA:Boolean){
        io.bidapp.kotlin_multiplatform.setCOPPA(COPPA)
    }
    fun setGDPR(GDPR:Boolean){
        io.bidapp.kotlin_multiplatform.setGDPR(GDPR)
    }

}

expect fun setCCPA(CCPA:Boolean)
expect fun setCOPPA(COPPA:Boolean)
expect fun setGDPR(GDPR:Boolean)