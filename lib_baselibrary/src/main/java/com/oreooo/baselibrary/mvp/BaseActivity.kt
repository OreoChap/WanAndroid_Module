package com.oreooo.baselibrary.mvp

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.MenuRes
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import com.oreooo.baselibrary.R

open class BaseActivity : AppCompatActivity() {
    protected var mToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    protected fun initToolBar(@IdRes toolbarId: Int, @StringRes title: Int) {
        if (toolbarId == 0) return
        mToolbar = findViewById(toolbarId)
        mToolbar?.setTitle(title) ?: setSupportActionBar(mToolbar)
    }

    protected fun initBackToolBar(@IdRes toolbarId: Int, @StringRes title: Int) {
        initBackToolBar(toolbarId, title, R.mipmap.icon_back)
    }

    protected fun initBackToolBar(@IdRes toolbarId: Int, @StringRes title: Int, backBtnId: Int) {
        if (toolbarId == 0) return
        initToolBar(toolbarId, title)
        mToolbar?.setNavigationIcon(backBtnId)
                ?: mToolbar?.setNavigationOnClickListener(View.OnClickListener { onBackPressed() })
    }

    protected fun setMenu(menu: Menu, @MenuRes menuId: Int) {
        menuInflater.inflate(menuId, menu)
    }
}