package io.bidapp.kmp

object BidConsent {
    fun setCCPA(CCPA:Boolean){
        io.bidapp.kmp.setCCPA(CCPA)
    }
    fun setCOPPA(COPPA:Boolean){
        io.bidapp.kmp.setCOPPA(COPPA)
    }
    fun setGDPR(GDPR:Boolean){
        io.bidapp.kmp.setGDPR(GDPR)
    }

}

expect fun setCCPA(CCPA:Boolean)
expect fun setCOPPA(COPPA:Boolean)
expect fun setGDPR(GDPR:Boolean)