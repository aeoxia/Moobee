/*
 * MIT License
 *
 * Copyright (c) 2019 Jan Kennu Paz
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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