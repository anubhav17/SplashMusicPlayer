/*
 * Copyright (c) 2020 Hemanth Savarla.
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 */
package com.teamff.music.playmusic.fragments.home

import android.app.ActivityOptions
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.MenuItem.SHOW_AS_ACTION_IF_ROOM
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.text.HtmlCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.teamff.music.appthemehelper.ThemeStore
import com.teamff.music.appthemehelper.common.ATHToolbarActivity
import com.teamff.music.appthemehelper.util.ToolbarContentTintHelper
import com.teamff.music.playmusic.*
import com.teamff.music.playmusic.adapter.HomeAdapter
import com.teamff.music.playmusic.dialogs.CreatePlaylistDialog
import com.teamff.music.playmusic.dialogs.ImportPlaylistDialog
import com.teamff.music.playmusic.fragments.base.AbsMainActivityFragment
import com.teamff.music.playmusic.glide.ProfileBannerGlideRequest
import com.teamff.music.playmusic.glide.UserProfileGlideRequest
import com.teamff.music.playmusic.util.NavigationUtil
import com.teamff.music.playmusic.util.PreferenceUtil
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest

import com.google.android.gms.ads.interstitial.InterstitialAd
import com.teamff.music.playmusic.util.AdmobUtil

import kotlinx.android.synthetic.main.abs_playlists.*
import kotlinx.android.synthetic.main.fragment_banner_home.*
import kotlinx.android.synthetic.main.fragment_banner_home.appNameText
import kotlinx.android.synthetic.main.fragment_banner_home.titleWelcome
import kotlinx.android.synthetic.main.fragment_banner_home.toolbar
import kotlinx.android.synthetic.main.fragment_banner_home.userImage
import kotlinx.android.synthetic.main.home_content.*

class HomeFragment :
    AbsMainActivityFragment(if (PreferenceUtil.isHomeBanner) R.layout.fragment_banner_home else R.layout.fragment_home) {

   lateinit var  mInterstitialAd : InterstitialAd
    //val appContext = requireContext().applicationContext
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.setBottomBarVisibility(true)
        mainActivity.setSupportActionBar(toolbar)
        mainActivity.supportActionBar?.title = null
        setStatusBarColorAuto(view)

    //    AdmobInterstital()
        AdmobUtil.loadAdmobInterstitialAd(requireContext())

        bannerImage?.setOnClickListener {
            val options = ActivityOptions.makeSceneTransitionAnimation(
                mainActivity,
                userImage,
                getString(R.string.transition_user_image)
            )
            NavigationUtil.goToUserInfo(requireActivity(), options)
            if (!App.isProVersion()) {
                AdmobUtil.showInterstitialAd(requireActivity())
            //if (mInterstitialAd.isLoaded) {
              //      mInterstitialAd.show()
              //  }
            }
        }

        lastAdded.setOnClickListener {
            findNavController().navigate(
                R.id.detailListFragment,
                bundleOf(EXTRA_PLAYLIST_TYPE to LAST_ADDED_PLAYLIST)
            )
            if (!App.isProVersion()) {
                AdmobUtil.showInterstitialAd(requireActivity())
                //if (mInterstitialAd.isLoaded) {
                //      mInterstitialAd.show()
                //  }
              }
        }

        topPlayed.setOnClickListener {
            findNavController().navigate(
                R.id.detailListFragment,
                bundleOf(EXTRA_PLAYLIST_TYPE to TOP_PLAYED_PLAYLIST)
            )
            if (!App.isProVersion()) {
                AdmobUtil.showInterstitialAd(requireActivity())
                //if (mInterstitialAd.isLoaded) {
                //      mInterstitialAd.show()
                //  }
            }
        }

        actionShuffle.setOnClickListener {
            libraryViewModel.shuffleSongs()
        }

        history.setOnClickListener {
            findNavController().navigate(
                R.id.detailListFragment,
                bundleOf(EXTRA_PLAYLIST_TYPE to HISTORY_PLAYLIST)
            )
            if (!App.isProVersion()) {
                AdmobUtil.showInterstitialAd(requireActivity())
                //if (mInterstitialAd.isLoaded) {
                //      mInterstitialAd.show()
                //  }
            }
        }

        userImage.setOnClickListener {
            val options = ActivityOptions.makeSceneTransitionAnimation(
                mainActivity,
                userImage,
                getString(R.string.transition_user_image)
            )
            NavigationUtil.goToUserInfo(requireActivity(), options)
            if (!App.isProVersion()) {
                AdmobUtil.showInterstitialAd(requireActivity())
                //if (mInterstitialAd.isLoaded) {
                //      mInterstitialAd.show()
                //  }
            }
        }
        titleWelcome?.text = String.format("%s", PreferenceUtil.userName)

        val homeAdapter = HomeAdapter(mainActivity)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(mainActivity)
            adapter = homeAdapter
        }

        libraryViewModel.getHome().observe(viewLifecycleOwner, Observer {
            homeAdapter.swapData(it)
        })

        loadProfile()
        setupTitle()
    }

/*    private fun AdmobInterstital() {

            mInterstitialAd = InterstitialAd(context)
            mInterstitialAd.adUnitId = admob_interstital
            mInterstitialAd.loadAd(AdRequest.Builder().build())
            mInterstitialAd.adListener = object : AdListener() {
                override fun onAdLoaded() {
                    Log.d(tag, "onAdLoaded.")

                }

                override fun onAdFailedToLoad(errorCode: Int) {
                    Log.d(tag, "onAdFailedToLoad: $errorCode")
                }

                override fun onAdOpened() {
                    Log.d(tag, "onAdOpened:")
                }

                override fun onAdClicked() {
                    Log.d(tag, "onAdClicked:")
                }

                override fun onAdLeftApplication() {
                    Log.d(tag, "onAdLeftApplication:")
                }

                override fun onAdClosed() {
                    mInterstitialAd.loadAd(AdRequest.Builder().build())
                }
            }

    }*/

    private fun setupTitle() {
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(
                R.id.searchFragment,
                null,
                navOptions
            )
        }
        val color = ThemeStore.accentColor(requireContext())
        val hexColor = String.format("#%06X", 0xFFFFFF and color)
        val appName = HtmlCompat.fromHtml(
            "Sync <span  style='color:$hexColor';>Music</span>",
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
        appNameText.text = appName


    }

    private fun loadProfile() {
        bannerImage?.let {
            ProfileBannerGlideRequest.Builder.from(
                Glide.with(requireContext()),
                ProfileBannerGlideRequest.getBannerModel()
            ).build().into(it)
        }
        UserProfileGlideRequest.Builder.from(
            Glide.with(requireActivity()),
            UserProfileGlideRequest.getUserModel()
        ).build().into(userImage)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
        menu.removeItem(R.id.action_grid_size)
        menu.removeItem(R.id.action_layout_type)
        menu.removeItem(R.id.action_sort_order)
        menu.findItem(R.id.action_settings).setShowAsAction(SHOW_AS_ACTION_IF_ROOM)
        ToolbarContentTintHelper.handleOnCreateOptionsMenu(
            requireContext(),
            toolbar,
            menu,
            ATHToolbarActivity.getToolbarBackgroundColor(toolbar)
        )
    }

    companion object {

        const val TAG: String = "BannerHomeFragment"

        @JvmStatic
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> findNavController().navigate(
                R.id.settingsActivity,
                null,
                navOptions
            )
            R.id.action_import_playlist -> ImportPlaylistDialog().show(
                childFragmentManager,
                "ImportPlaylist"
            )
            R.id.action_add_to_playlist -> CreatePlaylistDialog.create(emptyList()).show(
                childFragmentManager,
                "ShowCreatePlaylistDialog"
            )
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        ToolbarContentTintHelper.handleOnPrepareOptionsMenu(requireActivity(), toolbar)
    }
}
