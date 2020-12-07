package com.oreooo.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.Gravity
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.oreo.module_search.SearchAct
import com.oreo.wxarticle.WxArticleAct
import com.oreo.wxarticle.WxArticleFragment
import com.oreooo.baselibrary.IContext
import com.oreooo.baselibrary.mvpbase.BaseActivity
import com.oreooo.baselibrary.mvpbase.BaseFragment
import com.oreooo.baselibrary.route.RoutePath
import com.oreooo.module_user.UserAct
import com.oreooo.wanandroid.wanandroid.WanAndroidAct
import com.oreooo.wanandroid.wanandroid.WanAndroidFragment
import kotlinx.android.synthetic.main.main.*

/**
 *  主页
 */
@Route(path = RoutePath.WANANDROID_ACTIVITY)
class MainActivity : BaseActivity(), IContext {

    private var frags: HashMap<String, BaseFragment> = HashMap()
    private var currentFragName: String = ""

    companion object {
        lateinit var mContext: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        setApplication()
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
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar_main))
        if (supportActionBar != null)
            supportActionBar!!.setDisplayShowTitleEnabled(false)

        toolbar_main_search_btn.setOnClickListener {
            ARouter.getInstance().build(RoutePath.SEARCH_ACTIVITY).navigation()
        }

        toolbar_main_navigation.setOnClickListener {
            drawer_main.openDrawer(Gravity.START)
        }

        // 设置底部框
        bottom_navigation_view.setOnNavigationItemSelectedListener { menuItem ->
            setBottomNavMenu(menuItem.itemId)
            true
        }
        // 默认第一项
        bottom_navigation_view.menu.getItem(0).isChecked = true

        // 设置侧拉框
        setNavMenu(R.id.nav_item_code_labs, R.id.nav_item_todo, R.id.nav_item_user)
    }

    /**
     *  侧拉框选项：
     *  nav_item_code_labs: codeLab：测试代码用
     *  nav_item_todo: todo页面
     */
    private fun setNavMenu(vararg itemId: Int) {
        itemId.forEach {
            view_nav.menu.findItem(it).setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_item_code_labs -> Toast.makeText(this, "123", Toast.LENGTH_SHORT).show()
                    R.id.nav_item_todo -> ARouter.getInstance().build(RoutePath.TODO_ACTIVITY).navigation()
                    R.id.nav_item_user -> ARouter.getInstance().build(RoutePath.USER_ACTIVITY).navigation()
                }
                true
            }
        }
    }

    /**
     *  底部框选项（页面设置）
     *  item_bottom_1：主页
     *  item_bottom_2：公众号文章
     */
    private fun setBottomNavMenu(itemId: Int) {
        var frag: BaseFragment? = null
        var fragName: String? = null
        when (itemId) {
            R.id.item_bottom_1 -> {
                frag = WanAndroidFragment.getInstance()
                fragName = getString(R.string.WanAndroid_Frag)
            }
            R.id.item_bottom_2 -> {
                frag = WxArticleFragment.instance
                fragName = getString(R.string.WxArticle_Frag)
            }
            R.id.item_bottom_3 -> {

            }
        }
        if (frag != null && fragName != null) {
            switchFrags(fragName, frag)
        }
    }

    override fun setApplication() {
        WanAndroidAct.mContext = mContext
        SearchAct.mContext = mContext
        UserAct.mContext = mContext
        WxArticleAct.mContext = mContext
    }
}
