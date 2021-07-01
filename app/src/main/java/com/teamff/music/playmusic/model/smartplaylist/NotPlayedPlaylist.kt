package com.teamff.music.playmusic.model.smartplaylist

import com.teamff.music.playmusic.App
import com.teamff.music.playmusic.R
import com.teamff.music.playmusic.model.Song
import kotlinx.android.parcel.Parcelize

@Parcelize
class NotPlayedPlaylist : AbsSmartPlaylist(
    name = App.getContext().getString(R.string.not_recently_played),
    iconRes = R.drawable.ic_watch_later
) {
    override fun songs(): List<Song> {
        return topPlayedRepository.notRecentlyPlayedTracks()
    }
}