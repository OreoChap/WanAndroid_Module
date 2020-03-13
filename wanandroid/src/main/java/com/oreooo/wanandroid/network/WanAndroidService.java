package com.oreooo.wanandroid.network;

import com.oreooo.baselibrary.pojo.Article;
import com.oreooo.wanandroid.pojo.BannerData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Oreo https://github.com/OreoChap
 * @date 2018/12/18
 */
public interface WanAndroidService {
    @GET("banner/json")
    Observable<BannerData> getBanner();

    @GET("article/list/{curPage}/json")
    Observable<Article> getArticle(@Path("curPage") String curPage);

    @POST("article/query/{page}/json")
    Observable<Article> getArticle(@Path("page") int page, @Query("k") String keyword);
}
