package com.teamff.music.playmusic.model.smartplaylist

import androidx.annotation.DrawableRes
import com.teamff.music.playmusic.R
import com.teamff.music.playmusic.model.AbsCustomPlaylist

abstract class AbsSmartPlaylist(
    name: String,
    @DrawableRes val iconRes: Int = R.drawable.ic_queue_music
) : AbsCustomPlaylist(
    id = PlaylistIdGenerator(name, iconRes),
    name = name
)