package com.teamff.music.playmusic.model

import org.jaudiotagger.tag.FieldKey

class LoadingInfo(
    val filePaths: List<String>?,
    val fieldKeyValueMap: Map<FieldKey, String>?,
    val artworkInfo: ArtworkInfo?
)