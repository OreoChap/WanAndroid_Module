package com.example.oreooo.todoforstudy.Fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.oreooo.todoforstudy.Adapter.DoneFragRVA
import com.oreooo.baselibrary.ListBase.BaseRecyclerAdapter
import com.oreooo.todolist.LItePalDB.LitePalHelper
import com.oreooo.todolist.R
import java.text.SimpleDateFormat
import java.util.*

class DoneFrag : Fragment() {
    private var mContext: Context? = null
    private var timeTxt: TextView? = null
    private var mDate: String? = null
    private var rV:RecyclerView? = null

    companion object {
        val instance: DoneFrag by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DoneFrag()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_done, container, false)
        mContext = activity
        rV = view.findViewById<View>(R.id.recycler_doneFragment) as RecyclerView
        timeTxt = view.findViewById<View>(R.id.txt_time) as TextView
        rV!!.layoutManager = LinearLayoutManager(mContext)
        checkSysTime()
        updateUI()
        return view
    }

    fun updateUI() {
//        update(mDate!!)
    }

    private fun update(time: String) {
        rV!!.adapter = (DoneFragRVA(mContext!!, LitePalHelper.instance.getDoneProjectsByToday(time),
                R.layout.list_item_donefragment, BaseRecyclerAdapter.OnViewHolderClickListener { _, _ -> }))
        timeTxt!!.text = time
    }

    fun checkSysTime() {
        val d = Date()
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
        mDate = sdf.format(d)
    }
}
