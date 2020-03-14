package com.oreooo.wanandroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.Gravity
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.oreo.module_search.SearchAct
import com.oreo.wxarticle.WxArticleFragment
import com.oreooo.baselibrary.mvp.BaseActivity
import com.oreooo.baselibrary.mvp.BaseFragment
import com.oreooo.baselibrary.route.RoutePath
import com.oreooo.wanandroid.wanandroid.WanAndroidFragment
import kotlinx.android.synthetic.main.act_main.*

@Route(path = RoutePath.WANANDROID_ACTIVITY)
class WanAndroidActivity : BaseActivity() {

    private var frags: HashMap<String, BaseFragment> = HashMap()
    private var currentFragName: String = ""
//    private lateinit var mBottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)
        //        ARouter.getInstance().inject(this);
        switchFrags(getString(R.string.WanAndroid_Frag), WanAndroidFragment.getInstance())
        initView()
    }

    // todo 暂时直接返回到桌面，以后用账号密码登录后，再添加处理
    override fun onBackPressed() {
        val i = Intent(Intent.ACTION_MAIN)
        i.addCategory(Intent.CATEGORY_HOME)
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(i)
    }

    private fun switchFrags(newFragName: String, newFrag: BaseFragment) {
        val transaction = supportFragmentManager.beginTransaction()
        if (!TextUtils.isEmpty(currentFragName) && frags.containsKey(currentFragName)) {
            val currentFrag = frags[currentFragName]
            transaction.hide(currentFrag!!)
            if (!frags.containsKey(newFragName)) {
                frags[newFragName] = newFrag
                transaction.add(R.id.main_container, newFrag)
            }
            currentFragName = newFragName
            transaction.show(newFrag)
        } else {
            frags[newFragName] = newFrag
            currentFragName = newFragName
            transaction.replace(R.id.main_container, newFrag)
        }
        transaction.commit()
    }

    private fun initView() {
//        mBottomNavigationView = findViewById(R.id.bottom_navigation_view)

        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar_main))
        if (supportActionBar != null)
            supportActionBar!!.setDisplayShowTitleEnabled(false)

        toolbar_main_search_btn.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, SearchAct::class.java)
            startActivity(intent)
        }

        toolbar_main_navigation.setOnClickListener {
            drawer_main.openDrawer(Gravity.START)
        }

        bottom_navigation_view.setOnNavigationItemSelectedListener { menuItem ->
            val id = menuItem.itemId
            if (id == R.id.item_bottom_1) {
                switchFrags(getString(R.string.WanAndroid_Frag), WanAndroidFragment.getInstance())
//                val frag = (ARouter.getInstance().build(RoutePath.WANDROID_FRAGMENT).navigation()) as BaseFragment
//                switchFrags(frag)
            } else if (id == R.id.item_bottom_2) {
//                val frag = (ARouter.getInstance().build(RoutePath.WXARTICLE_FRAGMENT).navigation()) as WxArticleFragment

                switchFrags(getString(R.string.WxArticle_Frag), WxArticleFragment.instance)
            } else if (id == R.id.item_bottom_3) {

            }
            true
        }

        bottom_navigation_view.menu.getItem(0).isChecked = true

        view_nav.menu.findItem(R.id.nav_item_code_labs).setOnMenuItemClickListener {
            Toast.makeText(this, "123", Toast.LENGTH_SHORT).show()
            true
        }

//        frags.add(WanAndroidFragment.getInstance())
//        frags.add(WxArticleFragment.instance)

    }
}
