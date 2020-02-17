package com.oreooo.wanandroid.search;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import com.oreooo.baselibrary.ButtonClickListener;
import com.oreooo.baselibrary.ListBase.BaseRecyclerAdapter;
import com.oreooo.baselibrary.MvpBase.BaseActivity;
import com.oreooo.baselibrary.Util.StringUtil;
import com.oreooo.wanandroid.R;
import com.oreooo.wanandroid.pojo.Article;
import com.oreooo.wanandroid.pojo.ArticleDatas;

import java.util.List;

public class SearchAct extends BaseActivity implements SearchContract.View {

    private RecyclerView mSearchResultList;
    private TextInputEditText mSearchEditText;
    private Button mSearchButton;
    private SearchContract.Presenter mPresenter;
    private String mKeyword;
    private BaseRecyclerAdapter<ArticleDatas> mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_search);
        init();
    }

    private void init() {
        mSearchResultList = findViewById(R.id.recycler_search);
        mSearchEditText = findViewById(R.id.edit_text_search);
        mSearchButton = findViewById(R.id.search_btn);


        mSearchButton.setOnClickListener(new ButtonClickListener() {
            @Override
            protected void onSingleClick() {
                final String keyword = String.valueOf(mSearchEditText.getText());
                mKeyword = keyword;
                if (StringUtil.isNotEmpty(keyword)) {
                    mPresenter.getSearchArticle(0, keyword);
                } else {
                    // todo 弹出搜索关键字为空的提示？
                }
            }
        });

    }

    @Override
    public void showSearchResult(Article data) {
        if (mAdapter == null) {
            mAdapter = new BaseRecyclerAdapter<ArticleDatas>(SearchAct.this,
                    data.getData().getDatas(), R.layout.list_item_article, null) {
                @Override
                public void bindHolder(BaseViewHolder holder, ArticleDatas item, int position) {
                    ((TextView) holder.getView(R.id.txt_article_name))
                            .setText((Html.fromHtml("《" + item.getTitle() + "》")));
                }
            };
            mSearchResultList.setLayoutManager(new LinearLayoutManager(SearchAct.this));
            mSearchResultList.setAdapter(mAdapter);
        } else {
            mAdapter.setNewData(data.getData().getDatas());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        subscribe();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unsubscribe();
    }

    @Override
    public void subscribe() {
        this.mPresenter = SearchPresenter.getInstance();
        mPresenter.setView(this);
    }

    @Override
    public void unsubscribe() {
        this.mPresenter = null;
    }
}
