package com.oreooo.todolist.adapter

import android.app.AlertDialog
import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.TextView
import com.example.oreooo.todoforstudy.ToDoAct
import com.oreooo.module_todolist.R
import com.oreooo.todolist.MessageEvent
import com.oreooo.todolist.ProjectDialog
import com.oreooo.todolist.lItepal.LitePalHelper
import com.oreooo.todolist.lItepal.Project
import org.greenrobot.eventbus.EventBus
import java.text.SimpleDateFormat
import java.util.*

class DoingFragRVA(var list: List<Project>) : RecyclerView.Adapter<DoingFragRVA.RVHolder>() {
    val instance by lazy { this }
    private var mContext: Context? = null
    private var items: List<Project>? = null

    init {
        items = list
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RVHolder {
        mContext = p0.context
        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(R.layout.list_item_doingfragment,
                p0, false)
        return RVHolder(view, p0.context)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(viewHolder: RVHolder, position: Int) {
        val project = items?.get(position) ?: return
        with(viewHolder) {
            val first = items?.get(position - 1)
            if (position > 0 && first?.addTime == project.addTime) {
                bindViewHolder(project, true)
            } else {
                bindViewHolder(project, false)
            }
        }
    }

    class RVHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView), CompoundButton.OnCheckedChangeListener {
        var mProject: Project? = null
        var mView: View = itemView
        private var timeTxt: TextView? = null
        private var button: CheckBox? = null
        private var context: Context? = null

        var doneTimeStr: String = ""
        var doneDateStr: String = ""

        init {
            timeTxt = itemView.findViewById<View>(R.id.rv_item_time) as TextView

            button = itemView.findViewById<View>(R.id.rv_item_button) as CheckBox
            this.context = context
        }

        fun bindViewHolder(project: Project, isSameTime: Boolean) {
            mProject = project
            with(project) {
                timeTxt?.setText(addTime)
                val description = mView.findViewById<View>(R.id.rv_item_description) as TextView
                description.setText(thePlan)
                description.setOnLongClickListener(View.OnLongClickListener {
                    val dialog = ProjectDialog.dialogFactory.makeDialog(context!!)
                    dialog.showDialog(project, ToDoAct.SHOW_DONE_PROJECT)
                    true
                })
                checkedChange(isDone)
                button?.setChecked(isDone == 1)

                if (isSameTime) {
                    timeTxt?.setVisibility(View.GONE)
                } else {
                    timeTxt?.setVisibility(View.VISIBLE)
                }
                button?.setOnCheckedChangeListener(this@RVHolder)
            }
        }

        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            Log.d("DoingFragRVA", "此时的isChecked= " + isChecked)
            if (mProject!!.isDone == 1 && !isChecked) {
                val dialog = AlertDialog.Builder(context)
                dialog.setTitle("是否更改为未完成")
                        .setNegativeButton("确定") { _, _ ->
                            updateDoneFragmentUI(0)
                        }
                        .setPositiveButton("取消") { _, _ -> buttonView?.setChecked(true) }
                        .setOnCancelListener { buttonView?.setChecked(true) }
                        .show()
            } else if (mProject!!.isDone == 0) {
                updateDoneFragmentUI(1)
            }
        }

        private fun updateDoneFragmentUI(isDone: Int) {
            checkedChange(isDone)
            mProject?.isDone = isDone
            LitePalHelper.instance.updateProject(mProject!!)
            EventBus.getDefault().post(MessageEvent.DoneFragmentUpdateUIEvent("Send DoneFragmentUpdateUI Event"))
        }

        private fun checkedChange(isDone: Int) {
            val description = mView.findViewById<View>(R.id.rv_item_description) as TextView
            mProject?.let {
                if (isDone == 1) {
                    description.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
                    description.setTextColor(context!!.resources.getColor(R.color.rv_item_gray))
                    checkDate()
                    it.doneTime = doneTimeStr
                    it.doneDate = doneDateStr
                } else {
                    description.paint.flags = 0
                    description.setTextColor((context!!.resources).getColor(R.color.rv_item_black))
                    it.doneTime = "0"
                    it.doneDate = "0"
                }
            }
        }

        private fun checkDate() {
            val d = Date()
            val sdf1 = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
            val sdf2 = SimpleDateFormat("HH:mm:ss", Locale.CHINA)
            doneDateStr = sdf1.format(d)
            doneTimeStr = sdf2.format(d)
        }

        // 通过设置每个子项的宽高，隐藏 Done子项
        private fun hideDoneProject(showDoneProjects: Boolean) {
            val params = itemView.layoutParams as RecyclerView.LayoutParams
            if (showDoneProjects) {
                params.height = LinearLayout.LayoutParams.WRAP_CONTENT
                params.width = LinearLayout.LayoutParams.MATCH_PARENT
                itemView.visibility = View.VISIBLE
            } else {
                itemView.visibility = View.GONE
                params.height = 0
                params.width = 0
            }
            itemView.layoutParams = params
        }
    }
}