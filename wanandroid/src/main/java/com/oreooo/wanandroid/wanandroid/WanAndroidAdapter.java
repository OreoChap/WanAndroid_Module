package com.oreooo.wanandroid.wanandroid;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.oreooo.baselibrary.list.BaseRecyclerAdapter;
import com.oreooo.wanandroid.R;
import com.oreooo.baselibrary.pojo.ArticleDatas;

import java.util.List;

/**
 * @author Oreo https://github.com/OreoChap
 * @date 2019/5/4
 */
public class WanAndroidAdapter extends BaseRecyclerAdapter<ArticleDatas> {

    private List<ArticleDatas> mData;

    public WanAndroidAdapter(Context context, List<ArticleDatas> list,
                             int layoutId, @Nullable BaseRecyclerAdapter.OnViewHolderClickListener listener) {
        super(context, list, layoutId, listener);
        mData = list;
    }

    @Override
    public void bindHolder(BaseViewHolder holder, ArticleDatas item, int position) {
        ((TextView) holder.getView(R.id.txt_article_name))
                .setText((Html.fromHtml("《" + item.getTitle() + "》")));
        ((TextView) holder.getView(R.id.txt_article_super_chapter_name))
                .setText(String.valueOf(item.getSuperChapterName().trim()));
        if (item.getAuthor() != null && !item.getAuthor().trim().equals("")) {
            TextView articleAuthor = (TextView) holder.getView(R.id.txt_article_author);
            articleAuthor.setText(String.valueOf(item.getAuthor().trim()));
            articleAuthor.setVisibility(View.VISIBLE);
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
