package com.ourbalance.feature.view

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.BindingAdapter

@BindingAdapter("layout_constraintHeight_percent")
fun View.setLayoutConstraintHeightPercent(ratio: Int) {
    val percent = ratio.toFloat() / 100
    with(ConstraintSet()) {
        val layout = parent as ConstraintLayout
        clone(layout)
        constrainPercentHeight(id, percent)
        applyTo(layout)
    }
}
