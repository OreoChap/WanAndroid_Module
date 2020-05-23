package com.oreooo.module_user

import com.oreooo.baselibrary.mvpbase.BaseContract
import com.oreooo.baselibrary.newmvp.BasePresenter
import com.oreooo.baselibrary.pojo.BaseData
import com.oreooo.module_user.network.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class UserPresenter:BasePresenter<UserContract.View>(), UserContract.Presenter{

    companion object {
        @Volatile
        private var instance:UserPresenter? = null

        fun  getInstance():UserPresenter = synchronized(this) {
            instance?: UserPresenter().also { instance = it }
        }
    }

    override fun getCollectArticle(page: Int) {
        addSubscribe(Api.createUserService().getCollectArticle(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Consumer<BaseData<CollectArticle>>{
                    override fun accept(data: BaseData<CollectArticle>?) {
                        mView.collectArticleRefresh(data!!.datas)
                    }
                }))
    }
}