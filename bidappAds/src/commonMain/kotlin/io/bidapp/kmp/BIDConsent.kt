package io.bidapp.kmp

public object BidConsent {
    public fun setCCPA(CCPA:Boolean){
        io.bidapp.kmp.setCCPA(CCPA)
    }
    public fun setCOPPA(COPPA:Boolean){
        io.bidapp.kmp.setCOPPA(COPPA)
    }
    public fun setGDPR(GDPR:Boolean){
        io.bidapp.kmp.setGDPR(GDPR)
    }

}

public expect fun setCCPA(CCPA:Boolean)
public expect fun setCOPPA(COPPA:Boolean)
public expect fun setGDPR(GDPR:Boolean)