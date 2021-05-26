package com.hmscl.huawei.admob_mediation.NativeAds

import android.content.Context
import com.google.android.gms.ads.formats.NativeAdOptions
import com.google.android.gms.ads.mediation.customevent.CustomEventNativeListener
import com.huawei.hms.ads.nativead.NativeAd

class HuaweiCustomEventNativeAdsLoadedEventForwarder(
    private val listener: CustomEventNativeListener,
    private val options: NativeAdOptions,
    private val context: Context
) : HuaweiCustomEventNativeAdsLoadedListener() {
    override fun onNativeAdLoaded(native: NativeAd) {
        val mapper = HuaweiCustomEventNativeAdsMapper(native,context)
        listener.onAdLoaded(mapper)
    }
}