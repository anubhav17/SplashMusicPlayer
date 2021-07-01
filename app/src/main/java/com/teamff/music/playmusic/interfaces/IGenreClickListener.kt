package com.teamff.music.playmusic.interfaces

import android.view.View
import com.teamff.music.playmusic.model.Genre

interface IGenreClickListener {
    fun onClickGenre(genre: Genre, view: View)
}