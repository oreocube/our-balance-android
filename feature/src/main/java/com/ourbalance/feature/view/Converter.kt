package com.ourbalance.feature.view

import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter

@BindingAdapter("android:text")
fun bindLongInText(editText: EditText, newValue: Long?) {
    val oldValue = editText.text
    if (oldValue == null && newValue == null) return
    if (oldValue != null && newValue != null && oldValue.toString() == newValue.toString()) return
    editText.setText(newValue?.let { newValue.toString() })
}

@InverseBindingAdapter(attribute = "android:text")
fun getLongFromBinding(view: EditText): Long? {
    return with(view.text.toString()) {
        if (isEmpty()) {
            null
        } else {
            this.toLong()
        }
    }
}
