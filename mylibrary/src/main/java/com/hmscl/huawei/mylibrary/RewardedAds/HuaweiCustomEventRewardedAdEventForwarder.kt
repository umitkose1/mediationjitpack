package com.hmscl.huawei.ads.mediation_adapter_admob.RewardedAds

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.mediation.MediationAdLoadCallback
import com.google.android.gms.ads.mediation.MediationRewardedAd
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback
import com.google.android.gms.ads.mediation.MediationRewardedAdConfiguration
import com.huawei.hms.ads.AdParam
import com.huawei.hms.ads.reward.Reward
import com.huawei.hms.ads.reward.RewardAd
import com.huawei.hms.ads.reward.RewardAdLoadListener
import com.huawei.hms.ads.reward.RewardAdStatusListener

class HuaweiCustomEventRewardedAdEventForwarder(
        private val adConfiguration: MediationRewardedAdConfiguration,
        private val mediationAdLoadCallBack: MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback>
): HuaweiCustomEventRewardedAdListener(),MediationRewardedAd {
    private lateinit var rewardedAdCallback: MediationRewardedAdCallback
    private lateinit var rewardAd: RewardAd
    private var rewardAdId = "testx9dtjwj8hp"
    fun load(adUnit: String?) {
        if (adUnit != null) {
            rewardAdId = adUnit
        }
        this.rewardedAdCallback = mediationAdLoadCallBack.onSuccess(this)
    }

    override fun showAd(context: Context?) {
        rewardAd = RewardAd(context, rewardAdId)
        val listener = object : RewardAdLoadListener() {
            override fun onRewardAdFailedToLoad(p0: Int) {
                super.onRewardAdFailedToLoad(p0)
            }

            override fun onRewardedLoaded() {
                rewardAd.show(context as Activity?,object : RewardAdStatusListener() {
                    override fun onRewardAdClosed() {
                        rewardedAdCallback.onAdClosed()
                    }

                    override fun onRewardAdFailedToShow(errorCode: Int) {
                        rewardedAdCallback.onAdFailedToShow(AdError(errorCode,"Rewarded Ads","Failed to show"))
                    }

                    override fun onRewardAdOpened() {
                        rewardedAdCallback.onAdOpened()
                    }

                    override fun onRewarded(reward: Reward) {
                        rewardedAdCallback.onUserEarnedReward(HuaweiCustomEventRewardedItemMapper(reward.name,reward.amount))
                    }
                })
            }
        }
        rewardAd.loadAd(AdParam.Builder().build(), listener)
    }
}