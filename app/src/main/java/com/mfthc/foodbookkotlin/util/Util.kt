package com.mfthc.foodbookkotlin.util

import android.content.Context
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mfthc.foodbookkotlin.R

fun ImageView.downloadImage(url:String?,placeholder: CircularProgressDrawable){

    val option = RequestOptions().placeholder(placeholder).error(R.drawable.ic_launcher_background)
    Glide.with(context).setDefaultRequestOptions(option).load(url).into(this)
}
fun makePlaceHolder(context: Context):CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}