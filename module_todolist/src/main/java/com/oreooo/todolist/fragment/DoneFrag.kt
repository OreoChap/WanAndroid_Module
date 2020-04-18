package com.example.oreooo.todoforstudy.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
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
    private var year: Int = 0
    private var month: Int = 0
    private var day: Int = 0
    private lateinit var dialogView: View

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
            val dialogView: View = LayoutInflater.from(context).inflate(R.layout.dialog_date, null)
            val datePicker = dialogView.findViewById<DatePicker>(R.id.dialog_date_picker)

            // datePicker.init 时候，需要注意，month 是从0开始的
            if (year == 0) {
                checkSysTime()
                datePicker.init(mDate.substring(0, 4).toInt(), mDate.substring(5, 7).toInt() - 1, mDate.substring(8).toInt(),
                        { view, year, monthOfYear, dayOfMonth ->
                            this.year = year
                            this.month = monthOfYear + 1
                            this.day = dayOfMonth
                            Log.d("DoneFrag", "DATE: " + year + "/" + month + "/" + day)
                        })
                this.year = mDate.substring(0, 4).toInt()
                this.month = mDate.substring(5, 7).toInt()
                this.day = mDate.substring(8).toInt()
            } else {
                datePicker.init(year, month - 1, day,
                        { view, year, monthOfYear, dayOfMonth ->
                            this.year = year
                            this.month = monthOfYear + 1
                            this.day = dayOfMonth
                            Log.d("DoneFrag", "DATE: " + year + "/" + month + "/" + day)
                        })
            }

            val dialog: AlertDialog = AlertDialog.Builder(context!!)
                    .setView(dialogView)
                    .setPositiveButton(android.R.string.ok) { dialog, which ->
                        mDate = year.toString() + "-" + String.format("%02d", month) + "-" + day.toString()
                        Log.d("DoneFrag", "日期为：" + mDate)
                        updateUI()
                        dialog.dismiss()
                    }
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