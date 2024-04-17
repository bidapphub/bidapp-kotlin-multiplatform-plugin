package io.bidapp.kotlin_multiplatform

import io.bidapp.sdk.BIDConsent

actual fun setCCPA(CCPA: Boolean) {
    BIDConsent.setCCPA(CCPA)
}

actual fun setCOPPA(COPPA: Boolean) {
    BIDConsent.setCOPPA(COPPA)
}

actual fun setGDPR(GDPR: Boolean) {
    BIDConsent.setGDPR(GDPR)
}