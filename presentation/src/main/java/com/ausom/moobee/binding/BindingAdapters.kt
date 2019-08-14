package com.ausom.moobee.binding

import android.view.View
import android.widget.ImageView
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.ausom.moobee.GlideApp
import com.google.android.material.appbar.CollapsingToolbarLayout

@BindingAdapter("bind:imageUrl")
fun setImageUrl(imageView: ImageView, imageUrl: String?) {
    if (imageUrl == null) {
        imageView.setImageDrawable(null)
    } else {
        GlideApp.with(imageView.context)
                .load(imageUrl)
                .into(imageView)
    }
}

@BindingAdapter("bind:collapsingToolbarFont")
fun setCollapsingToolbarFont(collapsingToolbarLayout: CollapsingToolbarLayout, @FontRes resId: Int) {
    val typeface = ResourcesCompat.getFont(collapsingToolbarLayout.context, resId)
    collapsingToolbarLayout.apply {
        setCollapsedTitleTypeface(typeface)
        setExpandedTitleTypeface(typeface)
    }
}

@BindingAdapter("bind:visible")
fun setVisible(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}