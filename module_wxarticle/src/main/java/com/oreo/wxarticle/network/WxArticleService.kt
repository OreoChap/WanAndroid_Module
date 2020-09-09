package com.oreo.wxarticle.network

import com.oreo.wxarticle.pojo.ArticleClass
import com.oreooo.baselibrary.pojo.Article
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WxArticleService {
    @GET("wxarticle/chapters/json")
    suspend fun getArticleClass()
            : ArticleClass

    @GET("wxarticle/list/{id}/{page}/json?k={key}")
    suspend fun getKeywordArticle(@Path("id") authorId: Int,
                                  @Path("page") articlePage: Int,
                                  @Query("key") keyWord: String)
            : Article

    @GET("wxarticle/list/{id}/{page}/json")
    suspend fun getAuthorArticle(@Path("id") authorId: Int,
                                 @Path("page") articlePage: Int)
            : Article
}