package com.teamff.music.playmusic.model

import com.teamff.music.playmusic.repository.LastAddedRepository
import com.teamff.music.playmusic.repository.SongRepository
import com.teamff.music.playmusic.repository.TopPlayedRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class AbsCustomPlaylist(
    id: Long,
    name: String
) : Playlist(id, name), KoinComponent {

    abstract fun songs(): List<Song>

    protected val songRepository by inject<SongRepository>()

    protected val topPlayedRepository by inject<TopPlayedRepository>()

    protected val lastAddedRepository by inject<LastAddedRepository>()
}