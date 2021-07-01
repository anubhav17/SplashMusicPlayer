package com.teamff.music.playmusic.interfaces

import com.teamff.music.playmusic.model.Album
import com.teamff.music.playmusic.model.Artist
import com.teamff.music.playmusic.model.Genre

interface IHomeClickListener {
    fun onAlbumClick(album: Album)

    fun onArtistClick(artist: Artist)

    fun onGenreClick(genre: Genre)
}