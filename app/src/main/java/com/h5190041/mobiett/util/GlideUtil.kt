package com.h5190041.mobiett.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide


class GlideUtil {

    fun ImageView.resimGetir(url : String?, img : ImageView){
        Glide.with(this.context)
            .load(url)
            .into(this)
    }
}