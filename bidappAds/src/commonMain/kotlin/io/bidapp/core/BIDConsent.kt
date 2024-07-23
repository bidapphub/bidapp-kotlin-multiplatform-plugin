package io.bidapp.core

public object BidConsent {
    public fun setCCPA(CCPA:Boolean){
        io.bidapp.core.setCCPA(CCPA)
    }
    public fun setCOPPA(COPPA:Boolean){
        io.bidapp.core.setCOPPA(COPPA)
    }
    public fun setGDPR(GDPR:Boolean){
        io.bidapp.core.setGDPR(GDPR)
    }

}

public expect fun setCCPA(CCPA:Boolean)
public expect fun setCOPPA(COPPA:Boolean)
public expect fun setGDPR(GDPR:Boolean)