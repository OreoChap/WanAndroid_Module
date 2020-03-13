package com.oreooo.wanandroid.search;

import android.util.Log;

import com.oreooo.baselibrary.mvp.BaseContract;
import com.oreooo.wanandroid.network.Api;
import com.oreooo.baselibrary.pojo.Article;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View mView;
    private CompositeDisposable co = new CompositeDisposable();

    @Override
    public void getSearchArticle(int page, String keyword) {
        Api.createWanAndroidService().getArticle(page, keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Article>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("SearchPresenter", "getSearchArticle: 发起请求");
                    }

                    @Override
                    public void onNext(Article article) {
                        Log.i("SearchPresenter", "getSearchArticle: 请求回调");
                        mView.showSearchResult(article);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("SearchPresenter", "getSearchArticle: 发起失败");

                    }

                    @Override
                    public void onComplete() {
                        Log.i("SearchPresenter", "getSearchArticle: 请求结束");
                    }
                });
    }

    @Override
    public void clearRequest() {

    }

    @Override
    public void setView(BaseContract.BaseView view) {
        mView = (SearchContract.View) view;
    }

    public static SearchPresenter getInstance() {
        return SearchPresenterHolder.Instance;
    }

    private static class SearchPresenterHolder {
        private static SearchPresenter Instance = new SearchPresenter();
    }
}
