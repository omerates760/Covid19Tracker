package com.monofire.common.ui.bindingadapter

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

@BindingAdapter("goneVisible")
fun ProgressBar.setViewVisibility(
    visible: Boolean
) {
    if (visible) View.VISIBLE else View.GONE
}
