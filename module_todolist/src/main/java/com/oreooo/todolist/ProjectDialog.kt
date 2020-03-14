package com.oreooo.todolist

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.EditText
import com.example.oreooo.todoforstudy.LItePalDB.Project
import com.oreooo.todolist.fragment.DoingFrag
import com.oreooo.todolist.lItepal.LitePalHelper
import java.text.SimpleDateFormat
import java.util.*

class ProjectDialog private constructor(private val context: Context) {
    private var dialog: ProjectDialog? = null
    private var mContext: Context? = null
    private var mEdit: EditText? = null
    private val interFace = DoingFrag.instance

    init {
        mContext = context
    }

    object dialogFactory {
        fun makeDialog(context: Context): ProjectDialog {
            val dialog = ProjectDialog(context)
            return dialog
        }
    }

    fun showDialog(showDoneProjects: Boolean) {
        val mDialog = AlertDialog.Builder(mContext!!)
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.dialog_add_plan, null)
        mEdit = view.findViewById(R.id.description_edit)

        mDialog.setTitle("创建")
                .setView(view)
                .setPositiveButton("取消", null)
                .setNegativeButton("确定",
                        DialogInterface.OnClickListener { dialogInterface, _ ->
                            val descriptionInput = mEdit!!.text.toString()
                            if (descriptionInput.isEmpty()) {
                                dialogInterface.dismiss()
                                return@OnClickListener
                            }
                            val d = Date()
                            val sdf = SimpleDateFormat("yyyy-MM-dd")
                            val timeInput = sdf.format(d)
                            val p = Project(timeInput, descriptionInput)
                            LitePalHelper.instance.addProject(p)
                            interFace.update(showDoneProjects)
                        })
                .show()
    }

    /**
     * 用来修改每个item
     * @param p
     */
    fun showDialog(p: Project, showDoneProjects: Boolean) {
        val mDialog = AlertDialog.Builder(mContext!!)
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.dialog_add_plan, null)
        mEdit = view.findViewById(R.id.description_edit)
        mEdit!!.setText(p.thePlan)

        mDialog.setTitle("修改")
                .setView(view)
                .setPositiveButton("取消", null)
                .setNegativeButton("确定",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            val descriptionInput = mEdit!!.text.toString()
                            if (descriptionInput.isEmpty()) {
                                dialogInterface.dismiss()
                                return@OnClickListener
                            }
                            p.thePlan = descriptionInput
                            LitePalHelper.instance.updateProject(p)
                            interFace.update(showDoneProjects)
                        })
                .show()
    }
}