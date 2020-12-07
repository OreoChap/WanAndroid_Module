package com.oreooo.wanandroid.wanandroid

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.oreooo.baselibrary.mvpbase.BaseActivity
import com.oreooo.baselibrary.mvpbase.BaseFragment
import com.oreooo.baselibrary.route.RoutePath
import com.oreooo.module_wanandroid.R
import kotlinx.android.synthetic.main.wanandroid_main.*

class WanAndroidAct : BaseActivity() {

    private var frags: HashMap<String, BaseFragment> = HashMap()
    private var currentFragName: String = ""

    companion object {
        lateinit var mContext: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wanandroid_main)

        switchFrags(getString(R.string.WanAndroid_Frag), WanAndroidFragment.getInstance())
        initView()
    }

    private fun initView() {
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar_main))
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayShowTitleEnabled(false)
        }

        toolbar_main_search_btn.setOnClickListener {
//            ARouter.getInstance().build(RoutePath.SEARCH_ACTIVITY).navigation()
        }

        toolbar_main_navigation.setOnClickListener {
            drawer_main.openDrawer(Gravity.START)
        }

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
}