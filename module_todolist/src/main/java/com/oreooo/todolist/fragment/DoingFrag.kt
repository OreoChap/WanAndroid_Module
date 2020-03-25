package com.oreooo.todolist.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.oreooo.todoforstudy.Adapter.DoingFragRVA
import com.oreooo.todolist.Interface
import com.oreooo.todolist.R
import com.oreooo.todolist.lItepal.LitePalHelper
import com.oreooo.todolist.lItepal.Project

class DoingFrag : Fragment(), Interface.Dialog {
    private lateinit var rV: RecyclerView
    private lateinit var doingFragmentRVA: DoingFragRVA

    companion object {
        val instance: DoingFrag by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DoingFrag()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_doing, container, false)
        rV = view.findViewById(R.id.doingFragmentRV)
        rV.layoutManager = LinearLayoutManager(context)
        upDateUI(true)
        return view
    }

    private fun upDateUI(showDoneProjects: Boolean) {
        val mList: List<Project>
        if (showDoneProjects) {
            mList = LitePalHelper.instance.allProject
        } else {
            mList = LitePalHelper.instance.notDoneProject
        }
        doingFragmentRVA = DoingFragRVA(mList)
        rV.adapter = doingFragmentRVA
        doingFragmentRVA.notifyDataSetChanged()
    }

    // 显示或隐藏 Done项目
    fun showProjects(showDoneProjects: Boolean) {
        upDateUI(showDoneProjects)
    }

    override fun update(b: Boolean) {
        upDateUI(b)
    }
}