package com.oreo.module_search.network

import com.oreooo.baselibrary.pojo.Article
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchService {

    @POST("article/query/{page}/json")
    suspend fun getArticle(@Path("page") page: Int, @Query("k") keyword: String)
            : Article
}