package com.teamff.music.playmusic.model.smartplaylist

import com.teamff.music.playmusic.App
import com.teamff.music.playmusic.R
import com.teamff.music.playmusic.model.Song
import kotlinx.android.parcel.Parcelize

@Parcelize
class ShuffleAllPlaylist : AbsSmartPlaylist(
    name = App.getContext().getString(R.string.action_shuffle_all),
    iconRes = R.drawable.ic_shuffle
) {
    override fun songs(): List<Song> {
        return songRepository.songs()
    }
}