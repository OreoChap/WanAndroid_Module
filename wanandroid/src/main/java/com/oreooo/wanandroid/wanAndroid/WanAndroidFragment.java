package com.oreooo.wanandroid.wanAndroid;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.oreooo.baselibrary.RoutePath.WanAndroidRoutePath;
import com.oreooo.library.MvpBase.BaseFragment;
import com.oreooo.wanandroid.GlideImageLoader;
import com.oreooo.wanandroid.R;
import com.oreooo.wanandroid.pojo.Article;
import com.oreooo.wanandroid.pojo.BannerDetailData;
import com.oreooo.wanandroid.test.TRecyclerViewAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oreo https://github.com/OreoChap
 * @date 2018/12/13
 */

public class WanAndroidFragment extends BaseFragment implements WanAndroidContract.View{
    public static WanAndroidFragment wanAndroidFragment;
    WanAndroidContract.Presenter mPresenter;
    WanAndroidAdapter mAdapter;
    int ArticlePage = 0;

    public static WanAndroidFragment getInstance() {
        if (wanAndroidFragment == null) {
            synchronized (WanAndroidFragment.class) {
                if (wanAndroidFragment == null) {
                    wanAndroidFragment = new WanAndroidFragment();
                }
            }
        }
        return wanAndroidFragment;
    }

    @Override
    public void initView(View view) {
        RefreshLayout refreshLayout = view.findViewById(R.id.layout_refresh);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getArticles(String.valueOf(ArticlePage), true);
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                ++ArticlePage;
                if (mAdapter.getItemCount() <= 20 * ArticlePage) {
                    mPresenter.getArticles(String.valueOf(ArticlePage), false);
                } else {
                    --ArticlePage;
                }
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    @Override
    public void initListener() { }

    @Override
    public void showArticle(final Article data, boolean isUpdate) {
        if (wanAndroidFragment.getView() != null) {
            if (mAdapter == null || isUpdate) {
                RecyclerView mRecyclerView = wanAndroidFragment.getView().findViewById(R.id.recycler_wanAndroid);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mAdapter = new WanAndroidAdapter(getActivity(),
                        data.getData().getDatas(), R.layout.list_item_article, new TRecyclerViewAdapter.OnViewHolderClickListener() {
                    @Override
                    public void onClick(int position) {
                        ARouter.getInstance().build(WanAndroidRoutePath.WEBVIEW_ACTIVITY)
                                .withString("webUrl",  mAdapter.mData.get(position).getLink())
                                .navigation();
                    }
                });
                mRecyclerView.setAdapter(mAdapter);
                ArticlePage = 0;
            }

            if (mAdapter != null) {
                if (mAdapter.getItemCount() <= 20 * ArticlePage){
                    mAdapter.addData(data.getData().getDatas());
                    mAdapter.notifyItemChanged(mAdapter.mData.size());
                }
            }
        }
    }

    @Override
    public void showBanner(final List<BannerDetailData> list) {
        if (wanAndroidFragment.getView() != null) {
            Banner mBanner = wanAndroidFragment.getView().findViewById(R.id.banner_wanAndroid);
            List<String> bannerUrl = new ArrayList<>();
            List<String> titles = new ArrayList<>();
            for (BannerDetailData item : list) {
                bannerUrl.add(item.getImagePath());
                titles.add(item.getTitle());
            }

            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                    .setImageLoader(new GlideImageLoader())
                    .setImages(bannerUrl)
                    .setBannerTitles(titles)
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            ARouter.getInstance().build(WanAndroidRoutePath.WEBVIEW_ACTIVITY)
                                    .withString("webUrl", list.get(position).getUrl())
                                    .navigation();
                        }
                    })
                    .setBannerAnimation(Transformer.DepthPage)
                    .setDelayTime(1500)
                    .start();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        subscribe();
    }

    @Override
    public void onStop() {
        super.onStop();
        unsubscribe();
    }

    @Override
    public int setContentView() {
        return R.layout.fragment_wanandroid;
    }

    @Override
    public void subscribe() {
        this.mPresenter = WanAndroidPresenter.getInstance();
        mPresenter.setView(this);
        if (mAdapter == null) {
            mPresenter.getBanner();
            mPresenter.getArticles("0", true);
        }
    }

    @Override
    public void unsubscribe() {
        this.mPresenter = null;
    }
}
