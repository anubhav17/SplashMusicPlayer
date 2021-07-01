package com.teamff.music.playmusic.model.smartplaylist

import com.teamff.music.playmusic.App
import com.teamff.music.playmusic.R
import com.teamff.music.playmusic.model.Song
import kotlinx.android.parcel.Parcelize

@Parcelize
class LastAddedPlaylist : AbsSmartPlaylist(
    name = App.getContext().getString(R.string.last_added),
    iconRes = R.drawable.ic_library_add
) {
    override fun songs(): List<Song> {
        return lastAddedRepository.recentSongs()
    }
}