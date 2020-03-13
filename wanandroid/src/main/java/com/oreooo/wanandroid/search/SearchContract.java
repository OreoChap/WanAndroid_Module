package com.oreooo.wanandroid.search;

import com.oreooo.baselibrary.mvp.BaseContract;
import com.oreooo.baselibrary.pojo.Article;

public interface SearchContract {
    interface Presenter extends BaseContract.BasePresenter {
        void getSearchArticle(int page, String keyword);
    }

    interface View extends BaseContract.BaseView {
        void showSearchResult(Article data);
    }
}
