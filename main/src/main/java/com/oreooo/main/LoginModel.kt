package com.oreooo.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import android.widget.Toast

class LoginModel : ViewModel() {

    private val _name = MutableLiveData<String>()
    private val _lastName = MutableLiveData<String>()

     var name: LiveData<String> = _name
     var pwd: LiveData<String> = _lastName

    fun login() {
        Log.d("12345","66666")
    }
}
