package com.oreooo.module_user.network

import com.oreooo.baselibrary.pojo.BaseData
import com.oreooo.module_user.CollectArticle
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("lg/collect/list/{page}/json")
    fun getCollectArticle(@Path("page") page: Int): Observable<BaseData<CollectArticle>>
}