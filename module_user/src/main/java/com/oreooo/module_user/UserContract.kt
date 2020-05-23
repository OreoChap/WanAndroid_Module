package com.oreooo.module_user

import com.oreooo.baselibrary.newmvp.AbstractPresenter
import com.oreooo.baselibrary.newmvp.AbstractView
import com.oreooo.baselibrary.pojo.BaseData

interface UserContract {
    interface View:AbstractView {
        fun collectArticleRefresh(data:List<CollectArticle>)
    }

    interface Presenter:AbstractPresenter<View> {
        fun getCollectArticle(page:Int)
    }
}