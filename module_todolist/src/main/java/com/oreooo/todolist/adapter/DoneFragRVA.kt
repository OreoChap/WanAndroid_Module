package com.example.oreooo.todoforstudy.Adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import com.example.oreooo.todoforstudy.LItePalDB.Project
import com.oreooo.baselibrary.list.BaseRecyclerAdapter
import com.oreooo.todolist.R

class DoneFragRVA(val context: Context, private val list: List<Project>, private val layoutId: Int,
                  val listener: OnViewHolderClickListener) :
        BaseRecyclerAdapter<Project>(context, list, layoutId, listener) {

    override fun bindHolder(holder: BaseViewHolder, item: Project, position: Int) {
        val textView = holder.getView<View>(R.id.txt_description) as TextView
        val done = (holder.position + 1).toString() + ". " + item.thePlan
        textView.text = done
    }
}