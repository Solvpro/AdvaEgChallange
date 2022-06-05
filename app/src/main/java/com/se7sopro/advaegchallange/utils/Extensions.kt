package com.se7sopro.advaegchallange.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.se7sopro.advaegchallange.R
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso


fun ImageView.loadImage(url: String, progressBar: ProgressBar) =
    Picasso
        .get()
        .load(url)
        .placeholder(R.drawable.product)
        .fetch(object : Callback {

            override fun onError(e: java.lang.Exception?) {
                e?.printStackTrace()
            }

            override fun onSuccess() {
                progressBar.visibility = View.GONE
                Picasso
                    .get()
                    .load(url)
                    .placeholder(R.drawable.product)
                    .into(this@loadImage)
            }
        })

fun ImageView.loadImageFromCache(url: String) =
    Picasso
        .get()
        .load(url)
        .networkPolicy(NetworkPolicy.OFFLINE)
        .placeholder(R.drawable.product)
        .into(this)

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}