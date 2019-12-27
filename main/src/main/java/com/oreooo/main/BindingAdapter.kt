package com.oreooo.main

import android.databinding.BindingAdapter
import android.view.View

@BindingAdapter(value = ["app:name","app:pwd"], requireAll = true)
fun loginClickable(button: View, nameLength: Int, pwdLength: Int) {
    val clickable:Boolean = nameLength > 0 && pwdLength > 0
    button.isClickable = clickable
}