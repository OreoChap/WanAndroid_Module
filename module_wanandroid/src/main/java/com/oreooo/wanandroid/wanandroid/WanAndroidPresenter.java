package com.oreooo.wanandroid.wanandroid;

import android.util.Log;

import com.oreooo.baselibrary.mvp.BaseContract;
import com.oreooo.wanandroid.base.AbstractView;
import com.oreooo.wanandroid.base.BasePresenter;
import com.oreooo.wanandroid.network.Api;
import com.oreooo.baselibrary.pojo.Article;
import com.oreooo.wanandroid.pojo.BannerData;
import com.oreooo.wanandroid.pojo.BannerDetailData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Oreo https://github.com/OreoChap
 * @date 2018/12/17
 */
public class WanAndroidPresenter extends BasePresenter<WanAndroidContract.View> implements WanAndroidContract.Presenter {
    private static final String TAG = "WanAndroidPresenter";
    private List<BannerDetailData> mDate;

    public static WanAndroidPresenter getInstance() {
        return WanAndroidPresenterHolder.Instance;
    }

    private static class WanAndroidPresenterHolder {
        private static WanAndroidPresenter Instance = new WanAndroidPresenter();
    }

    WanAndroidPresenter() {
    }

//    @Override
//    public void setView(BaseContract.BaseView view) {
//        this.mView = (WanAndroidContract.View) view;
//    }

    @Override
    public void getArticles(String curPage, final boolean isUpdate) {
        addSubscribe(Api.createWanAndroidService().getArticle(curPage).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Article>() {
                    @Override
                    public void accept(Article article) throws Exception {
                        mView.showArticle(article, isUpdate);
                        Log.d(TAG, "getArticles: 请求回调");
                    }
                }));
    }

    @Override
    public void getBanner() {
        Api.createWanAndroidService().getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "getBanner: 发起请求");
                    }

                    @Override
                    public void onNext(BannerData bannerData) {
                        mDate = bannerData.getData();
                        mView.showBanner(mDate);
                        Log.d(TAG, "getBanner: 请求回调");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "getBanner: 请求失败");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "getBanner: 请求完结");
                    }
                });
    }
}