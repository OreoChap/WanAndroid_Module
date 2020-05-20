package com.oreooo.wanandroid.wanandroid;

import com.oreooo.baselibrary.newmvp.AbstractPresenter;
import com.oreooo.baselibrary.newmvp.AbstractView;
import com.oreooo.baselibrary.pojo.Article;
import com.oreooo.wanandroid.pojo.BannerDetailData;

import java.util.List;

/**
 * @author Oreo https://github.com/OreoChap
 * @date 2018/12/26
 */
public interface WanAndroidContract {
    interface Presenter extends AbstractPresenter<View> {
        void getBanner();

        void getArticles(String curPage, boolean isUpdate);
    }

    interface View extends AbstractView {
        void showBanner(List<BannerDetailData> list);

        void showArticle(Article data, boolean isUpdate);
    }
}