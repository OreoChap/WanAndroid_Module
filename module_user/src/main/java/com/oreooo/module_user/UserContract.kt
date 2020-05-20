package com.oreooo.module_user

import com.oreooo.baselibrary.newmvp.AbstractPresenter
import com.oreooo.baselibrary.newmvp.AbstractView

interface UserContract {
    interface View:AbstractView {
        fun collectArticleRefresh()
    }

    interface Presenter:AbstractPresenter<View> {
        fun getCollectArticle(page:Int)
    }
}