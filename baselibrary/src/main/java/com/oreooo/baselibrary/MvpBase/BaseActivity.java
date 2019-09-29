package com.oreooo.baselibrary.MvpBase;

import android.support.annotation.IdRes;
import android.support.annotation.MenuRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.oreooo.baselibrary.R;

public class BaseActivity extends AppCompatActivity {
    protected Toolbar mToolbar;

    protected void initToolBar(@IdRes int toolbarId, @StringRes int title) {
        if (toolbarId == 0) return;
        mToolbar = findViewById(toolbarId);
        if (title != 0) mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
    }

    protected void initBackToolBar(@IdRes int toolbarId, @StringRes int title) {
        initBackToolBar(toolbarId, title, R.mipmap.icon_back);
    }

    /**
     * @param toolbarId toolbar在layout中的id
     * @param title     toolbar标题名字
     * @param backBtnId toolbar的navigation图标
     */

    protected void initBackToolBar(@IdRes int toolbarId, @StringRes int title, int backBtnId) {
        if (toolbarId == 0) return;
        initToolBar(toolbarId, title);
        mToolbar.setNavigationIcon(backBtnId);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    protected void setMenu(Menu menu, @MenuRes int menuId) {
        getMenuInflater().inflate(menuId, menu);
    }
}
