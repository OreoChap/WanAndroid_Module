package com.oreooo.wanandroid.wanAndroid;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.oreooo.baselibrary.ListBase.BaseRecyclerAdapter;
import com.oreooo.wanandroid.R;
import com.oreooo.wanandroid.pojo.ArticleDatas;

import java.util.List;

/**
 * @author Oreo https://github.com/OreoChap
 * @date 2019/5/4
 */
public class WanAndroidAdapter extends BaseRecyclerAdapter<ArticleDatas> {

    public List<ArticleDatas> mData;
    private Context mContext;

    public WanAndroidAdapter(Context context, List<ArticleDatas> list,
                             int layoutId, @Nullable BaseRecyclerAdapter.OnViewHolderClickListener listener) {
        super(context, list, layoutId, listener);
        mData = list;
        mContext = context;
    }

    @Override
    public void bindHolder(BaseViewHolder holder, ArticleDatas item, int position) {
        ((TextView) holder.getView(R.id.txt_article_name))
                .setText((Html.fromHtml("《" + item.getTitle() + "》")));
        ((TextView) holder.getView(R.id.txt_article_super_chapter_name))
                .setText(String.valueOf(item.getSuperChapterName().trim()));
        if (item.getAuthor() != null && !item.getAuthor().trim().equals("")) {
            TextView article_author = (TextView) holder.getView(R.id.txt_article_author);
            article_author.setText(String.valueOf(item.getAuthor().trim()));
            article_author.setVisibility(View.VISIBLE);
        }
        ((TextView) holder.getView(R.id.txt_article_nice_date))
                .setText(Html.fromHtml("<font color='#008577'>" + "发布时间：" +
                        "</font>" +
                        item.getNiceDate()));
        ((TextView) holder.getView(R.id.txt_chapter_name))
                .setText(item.getChapterName());
    }

    public void addData(List<ArticleDatas> list) {
        mData.addAll(list);
    }
}
