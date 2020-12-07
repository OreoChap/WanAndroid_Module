package com.oreooo.wanandroid.wanandroid;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.oreooo.baselibrary.list.BaseRecyclerAdapter;
import com.oreooo.baselibrary.pojo.ArticleDatas;
import com.oreooo.module_wanandroid.R;

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
                .setText(item.getSuperChapterName().trim());

        TextView articleAuthor = (TextView) holder.getView(R.id.txt_article_author);
        if (item.getAuthor() != null && !item.getAuthor().trim().equals("")) {
            articleAuthor.setText(item.getAuthor().trim());
            articleAuthor.setVisibility(View.VISIBLE);
        } else {
            articleAuthor.setVisibility(View.GONE);
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
