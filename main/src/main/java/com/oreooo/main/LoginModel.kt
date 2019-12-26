package com.oreooo.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableField
import android.view.View
import android.widget.Toast

class LoginModel : ViewModel() {

    private lateinit var name: LiveData<String>
    private lateinit var pwd: LiveData<String>

    fun onNameChanged(s: CharSequence) {
        name!!.set(s.toString())

    }

    fun onPwdChanged(s: CharSequence) {
        pwd!!.set(s.toString())
    }

    fun login(view: View) {

    }
}
