package com.teamff.music.playmusic.model.smartplaylist

import com.teamff.music.playmusic.App
import com.teamff.music.playmusic.R
import com.teamff.music.playmusic.model.Song
import kotlinx.android.parcel.Parcelize

@Parcelize
class TopTracksPlaylist : AbsSmartPlaylist(
    name = App.getContext().getString(R.string.my_top_tracks),
    iconRes = R.drawable.ic_trending_up
) {
    override fun songs(): List<Song> {
        return topPlayedRepository.topTracks()
    }
}