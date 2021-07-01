/*
 * Copyright (c) 2020 Hemanth Savarla.
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 */
package com.teamff.music.playmusic.glide

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.teamff.music.appthemehelper.util.ATHUtil
import com.teamff.music.playmusic.R
import com.teamff.music.playmusic.glide.palette.BitmapPaletteTarget
import com.teamff.music.playmusic.glide.palette.BitmapPaletteWrapper
import com.teamff.music.playmusic.util.ColorUtil
import com.bumptech.glide.request.animation.GlideAnimation

abstract class SingleColorTarget(view: ImageView) : BitmapPaletteTarget(view) {

    private val defaultFooterColor: Int
        get() = ATHUtil.resolveColor(view.context, R.attr.colorControlNormal)

    abstract fun onColorReady(color: Int)

    override fun onLoadFailed(e: Exception?, errorDrawable: Drawable?) {
        super.onLoadFailed(e, errorDrawable)
        onColorReady(defaultFooterColor)
    }

    override fun onResourceReady(
        resource: BitmapPaletteWrapper?,
        glideAnimation: GlideAnimation<in BitmapPaletteWrapper>?
    ) {
        super.onResourceReady(resource, glideAnimation)
        resource?.let {
            onColorReady(
                ColorUtil.getColor(
                    it.palette,
                    ATHUtil.resolveColor(view.context, R.attr.colorPrimary)
                )
            )
        }
    }
}
