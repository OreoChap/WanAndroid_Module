package com.example.oreooo.todoforstudy

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.Menu
import android.view.MenuItem
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.oreooo.todoforstudy.Fragment.DoneFrag
import com.oreooo.baselibrary.mvp.BaseActivity
import com.oreooo.baselibrary.route.RoutePath
import com.oreooo.todolist.fragment.DoingFrag
import com.oreooo.todolist.MessageEvent
import com.oreooo.todolist.ProjectDialog
import com.oreooo.todolist.R
import kotlinx.android.synthetic.main.todo_act.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.litepal.LitePal

/**
 * @author Oreo https://github.com/OreoChap
 * @date 2019/8/4
 */

@Route(path = RoutePath.TODO_ACTIVITY)
class ToDoAct : BaseActivity() {
    private val pagers: MutableList<Fragment> = ArrayList<Fragment>()
    private val doingFragment: DoingFrag = DoingFrag.instance
    private var doneFragment: DoneFrag = DoneFrag.instance
    private var mDialog = ProjectDialog.dialogFactory.makeDialog(this)

    // EventBus注册，初始化LitePal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.todo_act)
//        LitePal.initialize(applicationContext)
        init()
        EventBus.getDefault().register(this)
    }

    // EventBus取消注册
    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    // 更新doneFragment中的日期更新method，并更新doneFragment的UI
    override fun onRestart() {
        super.onRestart()
        doneFragment.checkSysTime()
        doneFragment.updateUI()
    }

    // 菜单初始化
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        val showDoneProjects = menu?.findItem(R.id.menu_item_show_done_project)
        if (SHOW_DONE_PROJECT) {
            showDoneProjects?.setTitle(R.string.hide_done_project)
        } else {
            showDoneProjects?.setTitle(R.string.show_done_project)
        }
        return true
    }

    // 菜单栏
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_item_add_project -> {
                mDialog.showDialog(SHOW_DONE_PROJECT)
                return true
            }
            R.id.menu_item_show_done_project -> {
                SHOW_DONE_PROJECT = !SHOW_DONE_PROJECT
                doingFragment.showProjects(SHOW_DONE_PROJECT)
                this.invalidateOptionsMenu()
                return true
            }
            else -> return super.onOptionsItemSelected(item!!)
        }
    }

    private fun init() {
        pagers.add(doingFragment)
        pagers.add(doneFragment)

        pager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return pagers[position]
            }

            override fun getCount(): Int {
                return pagers.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                val title = arrayOf("DOING", "DONE")
                return title[position]
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent.DoneFragmentUpdateUIEvent) {
        doneFragment.updateUI()
    }

    companion object {
        var SHOW_DONE_PROJECT = true
    }
}