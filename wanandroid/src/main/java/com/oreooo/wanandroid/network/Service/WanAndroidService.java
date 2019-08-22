package com.oreooo.wanandroid.network.Service;

import com.oreooo.wanandroid.pojo.Article;
import com.oreooo.wanandroid.pojo.BannerData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Oreo https://github.com/OreoChap
 * @date 2018/12/18
 */
public interface WanAndroidService {
    @GET ("banner/json")
    Observable<BannerData> getBanner();

    @GET ("article/list/{curPage}/json")
    Observable<Article> getArticle(@Path("curPage") String curPage);
}
