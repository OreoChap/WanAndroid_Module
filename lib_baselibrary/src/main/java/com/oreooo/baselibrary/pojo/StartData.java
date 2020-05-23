package com.oreooo.baselibrary.pojo;

import java.util.List;

public abstract class StartData<T> {
    private int curPage;
    private int errorCode;
    private String errorMsg;
    private List<BaseData<T>> data;

    public List<BaseData<T>> getData() {
        return data;
    }

    public void setData(List<BaseData<T>> data) {
        this.data = data;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
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
