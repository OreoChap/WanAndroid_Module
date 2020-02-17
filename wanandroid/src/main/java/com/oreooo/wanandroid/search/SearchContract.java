package com.oreooo.wanandroid.search;

import com.oreooo.baselibrary.MvpBase.BaseContract;
import com.oreooo.wanandroid.pojo.Article;

public interface SearchContract {
    interface Presenter extends BaseContract.BasePresenter {
        void getSearchArticle(int page, String keyword);
    }

    interface View extends BaseContract.BaseView {
        void showSearchResult(Article data);
    }
}
