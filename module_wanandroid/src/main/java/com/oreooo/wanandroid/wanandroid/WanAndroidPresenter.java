package com.oreooo.wanandroid.wanandroid;

import android.util.Log;

import com.oreooo.baselibrary.newmvp.StartPresenter;
import com.oreooo.baselibrary.pojo.Article;
import com.oreooo.wanandroid.network.Api;
import com.oreooo.wanandroid.pojo.BannerData;
import com.oreooo.wanandroid.pojo.BannerDetailData;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Oreo https://github.com/OreoChap
 * @date 2018/12/17
 */
public class WanAndroidPresenter extends StartPresenter<WanAndroidContract.View> implements WanAndroidContract.Presenter {
    private List<BannerDetailData> mDate;

    public static WanAndroidPresenter getInstance() {
        return WanAndroidPresenterHolder.Instance;
    }

    private static class WanAndroidPresenterHolder {
        private static WanAndroidPresenter Instance = new WanAndroidPresenter();
    }

    @Override
    public void getArticles(String curPage, final boolean isUpdate) {
        Log.d("xyz", "getArticles: 开始请求");
        addSubscribe(Api.createWanAndroidService().getArticle(curPage).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Article>() {
                    @Override
                    public void accept(Article article) throws Exception {
                        try {
                            Log.d("xyz", "getArticles: 请求回调");
                            mView.showArticle(article, isUpdate);
                        } catch (Exception e) {
                            Log.e("xyz", "getArticles: Exception——" + e);
                        }

                    }
                }));
    }

    @Override
    public void getBanner() {
        addSubscribe(Api.createWanAndroidService().getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BannerData>() {
                    @Override
                    public void accept(BannerData bannerData) throws Exception {
                        try {
                            mDate = bannerData.getData();
                            mView.showBanner(mDate);
                            Log.d("xyz", "getBanner: 请求回调");
                        } catch (Exception e) {
                            Log.d("xyz", "getBanner: Exception——" + e);

                        }
                    }
                }));
    }
}