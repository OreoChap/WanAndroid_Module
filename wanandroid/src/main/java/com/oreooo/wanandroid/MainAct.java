package com.oreooo.wanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.oreooo.baselibrary.MvpBase.BaseActivity;
import com.oreooo.baselibrary.MvpBase.BaseFragment;
import com.oreooo.baselibrary.RoutePath.WanAndroidRoutePath;
import com.oreooo.wanandroid.R;
import com.oreooo.wanandroid.wanAndroid.WanAndroidFragment;

import java.util.ArrayList;
import java.util.List;

@Route(path = WanAndroidRoutePath.WANANDROID_ACTIVITY)
public class MainAct extends BaseActivity {

    List<BaseFragment> frags = new ArrayList<>();
        BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
