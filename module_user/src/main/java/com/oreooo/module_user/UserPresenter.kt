package com.oreooo.module_user

import android.util.Log
import com.oreooo.baselibrary.newmvp.StartPresenter
import com.oreooo.baselibrary.pojo.BaseData
import com.oreooo.module_user.network.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class UserPresenter : StartPresenter<UserContract.View>(), UserContract.Presenter {

    companion object {
        @Volatile
        private var instance: UserPresenter? = null

        fun getInstance(): UserPresenter = synchronized(this) {
            instance ?: UserPresenter().also { instance = it }
        }
    }

    override fun getCollectArticle(page: Int) {
        addSubscribe(Api.createUserService().getCollectArticle(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Consumer<BaseData<CollectArticle>> {
                    override fun accept(data: BaseData<CollectArticle>) {
                        val d:ArrayList<CollectArticle>  = data.data.datas as ArrayList<CollectArticle>
                        mView.collectArticleRefresh(d)
                    }
                }, Consumer<Throwable> {
                    Log.e("xyz", it.toString())
                })
        )
    }
}