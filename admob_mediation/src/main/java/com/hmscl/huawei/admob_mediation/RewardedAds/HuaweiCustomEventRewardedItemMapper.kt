package com.hmscl.huawei.admob_mediation.RewardedAds

import com.google.android.gms.ads.rewarded.RewardItem

class HuaweiCustomEventRewardedItemMapper(
        private val type: String,
        private val amount: Int
): RewardItem {
    override fun getType(): String {
        return type
    }

    override fun getAmount(): Int {
        return amount
    }
}