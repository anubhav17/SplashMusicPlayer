package com.teamff.music.playmusic.util

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.teamff.music.playmusic.admob_interstital


object AdmobUtil {
    private var mInterstitialAd: InterstitialAd? = null

    fun loadAdmobInterstitialAd(context: Context) {
        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            context,
            admob_interstital,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d("TAG", adError?.message)
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d("TAG", "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                }
            })

    }
    fun showInterstitialAd(activity: Activity) {
        if (mInterstitialAd!=null){
            mInterstitialAd?.show(activity)
        }
    }
}