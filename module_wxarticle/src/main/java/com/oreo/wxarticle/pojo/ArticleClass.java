package com.oreo.wxarticle.pojo;

import java.util.List;

public class ArticleClass {
    private List<ArticleClassData> data;
    private int errorCode;
    private String errorMsg;

    public List<ArticleClassData> getData() {
        return data;
    }

    public void setData(List<ArticleClassData> data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
