package com.teamff.music.playmusic.interfaces

import android.view.View
import com.teamff.music.playmusic.db.PlaylistWithSongs

interface IPlaylistClickListener {
    fun onPlaylistClick(playlistWithSongs: PlaylistWithSongs, view: View)
}