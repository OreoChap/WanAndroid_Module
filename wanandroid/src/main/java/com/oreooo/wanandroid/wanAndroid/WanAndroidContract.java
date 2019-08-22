package com.oreooo.wanandroid.wanAndroid;

import com.oreooo.library.MvpBase.BaseContract;
import com.oreooo.wanandroid.pojo.Article;
import com.oreooo.wanandroid.pojo.BannerDetailData;

import java.util.List;

/**
 * @author Oreo https://github.com/OreoChap
 * @date 2018/12/26
 */
public interface WanAndroidContract {
    interface Presenter extends BaseContract.BasePresenter {
        void getBanner();
        void getArticles(String curPage, boolean isUpdate);
    }

    interface View extends BaseContract.BaseView {
        void showBanner(List<BannerDetailData> list);
        void showArticle(Article data, boolean isUpdate);
    }
}
