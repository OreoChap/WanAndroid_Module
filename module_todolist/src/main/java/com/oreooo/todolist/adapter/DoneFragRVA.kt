package com.oreooo.todolist.adapter

import android.content.Context
import android.widget.TextView
import com.oreooo.baselibrary.list.BaseRecyclerAdapter
import com.oreooo.module_todolist.R
import com.oreooo.todolist.lItepal.Project

class DoneFragRVA(val context: Context, private val list: List<Project>, private val layoutId: Int,
                  val listener: BaseRecyclerAdapter.OnViewHolderClickListener) :
        BaseRecyclerAdapter<Project>(context, list, layoutId, listener) {

    override fun bindHolder(holder: BaseViewHolder, item: Project, position: Int) {
        val textView = holder.getView<TextView>(R.id.txt_description)
        val done = (holder.position + 1).toString() + ". " + item.thePlan
        textView.text = done
    }
}