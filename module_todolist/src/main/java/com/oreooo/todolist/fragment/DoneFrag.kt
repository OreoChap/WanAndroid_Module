package com.example.oreooo.todoforstudy.Fragment

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.oreooo.baselibrary.list.BaseRecyclerAdapter
import com.oreooo.todolist.R
import com.oreooo.todolist.adapter.DoneFragRVA
import com.oreooo.todolist.lItepal.LitePalHelper
import java.text.SimpleDateFormat
import java.util.*

class DoneFrag : Fragment() {
    private var mDate: String = ""
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mDoneTime: TextView

    companion object {
        val instance: DoneFrag by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DoneFrag()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_done, container, false)
        mRecyclerView = view.findViewById(R.id.recycler_doneFragment)
        mDoneTime = view.findViewById(R.id.txt_time)
        mDoneTime.setOnClickListener {
          val  dialog:AlertDialog = AlertDialog.Builder(context!!)
                  .setView(LayoutInflater.from(context).inflate(R.layout.dialog_date, container))
                  .setPositiveButton("OK", object : DialogInterface.OnClickListener {
                      override fun onClick(dialog: DialogInterface?, which: Int) {

                      }
                  })
                  .show()
        }
        checkSysTime()
        updateUI()
        return view
    }

    fun updateUI() {
        update(mDate)
    }

    private fun update(time: String) {
        mRecyclerView.adapter = (DoneFragRVA(context!!, LitePalHelper.instance.getDoneProjectsByToday(time),
                R.layout.list_item_donefragment, BaseRecyclerAdapter.OnViewHolderClickListener { _, _ -> }))
        mDoneTime.text = time
    }

    fun checkSysTime() {
        val d = Date()
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
        mDate = sdf.format(d)
    }
}