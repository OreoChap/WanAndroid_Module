package com.oreooo.todolist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.example.oreooo.todoforstudy.Fragment.DoneFrag
import com.oreooo.baselibrary.mvpbase.StartFragment
import com.oreooo.todolist.fragment.DoingFrag
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class ToDoFrag : StartFragment() {
    private val pagers: MutableList<Fragment> = ArrayList<Fragment>()
    private lateinit var doingFragment: DoingFrag
    private lateinit var doneFragment: DoneFrag
    private lateinit var mDialog: ProjectDialog

    companion object {
        val instance: ToDoFrag by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ToDoFrag()
        }
        var SHOW_DONE_PROJECT = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
    }

    override fun onStart() {
        super.onStart()
        activity
//        doneFragment.checkSysTime()
//        doneFragment.updateUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.menu_main_activity, menu)
        val showDoneProjects = menu?.findItem(R.id.menu_item_show_done_project)
        if (SHOW_DONE_PROJECT) {
            showDoneProjects?.setTitle(R.string.hide_done_project)
        } else {
            showDoneProjects?.setTitle(R.string.show_done_project)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_item_add_project -> {
                mDialog = ProjectDialog.dialogFactory.makeDialog(context!!)
                mDialog.showDialog(SHOW_DONE_PROJECT)
                return true
            }
            R.id.menu_item_show_done_project -> {
                SHOW_DONE_PROJECT = !SHOW_DONE_PROJECT
                doingFragment.showProjects(SHOW_DONE_PROJECT)
//                this.invalidateOptionsMenu()
                return true
            }
            else -> return super.onOptionsItemSelected(item!!)
        }
    }

    override fun init(view: View, savedInstanceState: Bundle?) {
//        LitePal.initialize(this.context!!.applicationContext)

        doingFragment = DoingFrag.instance
        doneFragment = DoneFrag.instance

        pagers.add(doingFragment)
        pagers.add(doneFragment)

        view!!.findViewById<ViewPager>(R.id.pager).adapter = object : FragmentPagerAdapter(fragmentManager) {
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

    override fun setContentView(): Int {
        return R.layout.frag_todo
    }
}