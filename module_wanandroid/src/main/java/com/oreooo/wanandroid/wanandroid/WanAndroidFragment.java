package com.oreooo.wanandroid.wanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.oreooo.baselibrary.list.BaseRecyclerAdapter;
import com.oreooo.baselibrary.pojo.Article;
import com.oreooo.baselibrary.route.RoutePath;
import com.oreooo.main.services.LoginServiceApi;
import com.oreooo.module_wanandroid.R;
import com.oreooo.wanandroid.GlideImageLoader;
import com.oreooo.baselibrary.newmvp.StartFragment;
import com.oreooo.wanandroid.pojo.BannerDetailData;
import com.oreooo.wanandroid.webview.WebViewActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Oreo https://github.com/OreoChap
 * @date 2018/12/13
 */

@Route(path = RoutePath.WANDROID_FRAGMENT)
public class WanAndroidFragment extends StartFragment<WanAndroidContract.Presenter> implements WanAndroidContract.View {
    public static WanAndroidFragment wanAndroidFragment;
    private WanAndroidAdapter mAdapter;
    int articlePage = 0;
    private RecyclerView mRecyclerView;

    /**
     * 尝试用 ReentrantLock 替代 synchronized
     */
    private final static ReentrantLock lock = new ReentrantLock();

    public static WanAndroidFragment getInstance() {
        if (wanAndroidFragment == null) {
//            synchronized (WanAndroidFragment.class) {
//                if (wanAndroidFragment == null) {
//                    wanAndroidFragment = new WanAndroidFragment();
//                }
//            }
            lock.lock();
            try {
                if (wanAndroidFragment == null) {
                    wanAndroidFragment = new WanAndroidFragment();
                }
            } finally {
                lock.unlock();
            }
        }
        return wanAndroidFragment;
    }

    @Override
    public void init(View view, Bundle savedInstanceState) {
        RefreshLayout refreshLayout = view.findViewById(R.id.layout_refresh);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getArticles(String.valueOf(articlePage), true);
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                ++articlePage;
                if (mAdapter.getItemCount() - 1 <= 20 * articlePage) {
                    mPresenter.getArticles(String.valueOf(articlePage), false);
                } else {
                    --articlePage;
                }
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });

        try {
            LoginServiceApi api = ARouter.getInstance().navigation(LoginServiceApi.class);

            Log.e("xyz", api.getUserId() + "");
        }catch (Exception e) {
            Log.e("xyz", e.toString() );
        }
    }

    @Override
    public void showArticle(final Article data, boolean isUpdate) {
        if (wanAndroidFragment.getView() != null) {
            if (mAdapter == null || isUpdate) {
                mRecyclerView = wanAndroidFragment.getView().findViewById(R.id.recycler_wanAndroid);
                mAdapter = new WanAndroidAdapter(getActivity(),
                        data.getData().getDatas(), R.layout.list_item_article, new BaseRecyclerAdapter.OnViewHolderClickListener() {
                    @Override
                    public void onClick(int position, View view) {
                        Intent i = new Intent(getActivity(), WebViewActivity.class);
                        i.putExtra("webUrl", mAdapter.getData().get(position - 1).getLink());
                        startActivity(i);
                    }
                });
                mRecyclerView.setAdapter(mAdapter);
                articlePage = 0;
                mPresenter.getBanner();
            }
        }
        if (mAdapter != null) {
            if (mAdapter.getItemCount() - 1 <= 20 * articlePage && data.getData().getDatas() != null) {
                mAdapter.addData(data.getData().getDatas());
                mAdapter.notifyItemChanged(mAdapter.getData().size());
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showBanner(final List<BannerDetailData> list) {
        if (wanAndroidFragment.getView() != null) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.view_banner,
                    mRecyclerView, false);
            Banner mBanner = view.findViewById(R.id.banner);
            if (mAdapter != null) {
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
                                Intent i = new Intent(getActivity(), WebViewActivity.class);
                                i.putExtra("webUrl", list.get(position).getUrl());
                                startActivity(i);
                            }
                        })
                        .setBannerAnimation(Transformer.DepthPage)
                        .setDelayTime(2000)
                        .start();
                mAdapter.setHeaderView(view);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public int setContentView() {
        return R.layout.frag_wanandroid;
    }

    @Override
    public WanAndroidPresenter setPresenter() {
        return WanAndroidPresenter.getInstance();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mAdapter == null) {
            mPresenter.getArticles("0", true);
        }
    }
}