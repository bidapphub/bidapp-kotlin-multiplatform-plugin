package io.bidapp.core

import io.bidapp.sdk.BIDConsent

public actual fun setCCPA(CCPA: Boolean) {
    BIDConsent.setCCPA(CCPA)
}

public actual fun setCOPPA(COPPA: Boolean) {
    BIDConsent.setCOPPA(COPPA)
}

public actual fun setGDPR(GDPR: Boolean) {
    BIDConsent.setGDPR(GDPR)
}