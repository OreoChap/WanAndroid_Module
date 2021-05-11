package com.oreooo.baselibrary.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Oreo https://github.com/OreoChap
 * @date 2019/4/29
 */
public class ArticleData {
    @SerializedName("curPage") private String page;
    private List<ArticleDatas> datas;
    private String offset;
    private boolean over;
    private String pageCount;
    private String size;
    private String total;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<ArticleDatas> getDatas() {
        return datas;
    }

    public void setDatas(List<ArticleDatas> datas) {
        this.datas = datas;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
