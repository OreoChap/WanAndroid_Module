package com.oreooo.wanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.oreooo.baselibrary.MvpBase.BaseActivity;
import com.oreooo.baselibrary.MvpBase.BaseFragment;
import com.oreooo.baselibrary.RoutePath.WanAndroidRoutePath;
import com.oreooo.wanandroid.wanAndroid.WanAndroidFragment;

import java.util.ArrayList;
import java.util.List;

@Route(path = WanAndroidRoutePath.WANANDROID_ACTIVITY)
public class MainAct extends BaseActivity {

    List<BaseFragment> frags = new ArrayList<>();
    BottomNavigationView mBottomNavigationView;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
//        ARouter.getInstance().inject(this);
        switchFrags(WanAndroidFragment.getInstance());
        initView();
    }

    // todo 暂时直接返回到桌面，以后用账号密码登录后，再添加处理
    @Override
    public void onBackPressed() {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    private void switchFrags(BaseFragment frag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (!frags.contains(frag)) {
            frags.add(frag);
        }
        transaction.replace(R.id.main_container_fragment, frag).commit();
    }

    private void initView() {
        mBottomNavigationView = findViewById(R.id.bottom_navigation_view);
        mToolbar = findViewById(R.id.toolbar_main);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar()!= null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        mToolbar.findViewById(R.id.toolbar_main_navigation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationView)findViewById(R.id.view_nav)).requestFocus();
            }
        });

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.item_bottom_1) {
                    switchFrags(WanAndroidFragment.getInstance());
                } else if (id == R.id.item_bottom_2) {

                }
                return true;
            }
        });

        mBottomNavigationView.getMenu().getItem(0).setChecked(true);


    }
}
