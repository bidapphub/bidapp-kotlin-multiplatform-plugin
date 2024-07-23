package io.bidapp.core

import cocoapods.bidapp.BIDConsent
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSNumber
import platform.Foundation.numberWithBool


@OptIn(ExperimentalForeignApi::class)
public actual fun setCCPA(CCPA: Boolean) {
    val integerValue: NSNumber = if (CCPA) NSNumber.numberWithBool(true) else NSNumber.numberWithBool(false)
    BIDConsent.CCPA = integerValue
}

@OptIn(ExperimentalForeignApi::class)
public actual fun setCOPPA(COPPA: Boolean) {
    val integerValue: NSNumber = if (COPPA) NSNumber.numberWithBool(true) else NSNumber.numberWithBool(false)
    BIDConsent.COPPA = integerValue
}

@OptIn(ExperimentalForeignApi::class)
public actual fun setGDPR(GDPR: Boolean) {
    val integerValue: NSNumber = if (GDPR) NSNumber.numberWithBool(true) else NSNumber.numberWithBool(false)
    BIDConsent.GDPR = integerValue
}