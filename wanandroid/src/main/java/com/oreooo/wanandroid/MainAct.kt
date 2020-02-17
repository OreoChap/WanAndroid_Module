package com.oreooo.wanandroid

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.oreooo.baselibrary.MvpBase.BaseActivity
import com.oreooo.baselibrary.MvpBase.BaseFragment
import com.oreooo.baselibrary.RoutePath.WanAndroidRoutePath
import com.oreooo.todolist.ToDoFrag
import com.oreooo.wanandroid.search.SearchAct
import com.oreooo.wanandroid.wanAndroid.WanAndroidFragment
import kotlinx.android.synthetic.main.act_main.*
import java.util.*

@Route(path = WanAndroidRoutePath.WANANDROID_ACTIVITY)
class MainAct : BaseActivity() {

    private var frags: MutableList<BaseFragment> = ArrayList()
//    private lateinit var mBottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)
        //        ARouter.getInstance().inject(this);
        switchFrags(WanAndroidFragment.getInstance())
        initView()
    }

    // todo 暂时直接返回到桌面，以后用账号密码登录后，再添加处理
    override fun onBackPressed() {
        val i = Intent(Intent.ACTION_MAIN)
        i.addCategory(Intent.CATEGORY_HOME)
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(i)
    }

    private fun switchFrags(frag: BaseFragment) {
        val transaction = supportFragmentManager.beginTransaction()
        if (!frags.contains(frag)) {
            frags.add(frag)
        }
        transaction.replace(R.id.main_container, frag).commit()
    }

    private fun initView() {
//        mBottomNavigationView = findViewById(R.id.bottom_navigation_view)

        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar_main))
        if (supportActionBar != null)
            supportActionBar!!.setDisplayShowTitleEnabled(false)

        findViewById<ImageView>(R.id.toolbar_main_navigation).setOnClickListener(View.OnClickListener {
            findViewById<NavigationView>(R.id.view_nav).requestFocus()
        })

        findViewById<Button>(R.id.toolbar_main_search_btn).setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.setClass(this, SearchAct::class.java)
            startActivity(intent)
        })


        bottom_navigation_view.setOnNavigationItemSelectedListener { menuItem ->
            val id = menuItem.itemId
            if (id == R.id.item_bottom_1) {
                switchFrags(WanAndroidFragment.getInstance())
            } else if (id == R.id.item_bottom_2) {
                switchFrags(ToDoFrag.instance)
            } else if (id == R.id.item_bottom_3) {

            }
            true
        }

        bottom_navigation_view.menu.getItem(0).isChecked = true
    }
}
