package com.oreooo.baselibrary.mvpbase

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *  BaseFragment
 *   第一版基于契约类设计用的Fragment 的base类
 * @author oreo
 */
open abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(setContentView(), container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view, savedInstanceState)
    }

    abstract fun setContentView(): Int
    abstract fun init(view: View, savedInstanceState: Bundle?)
}