package com.oreooo.baselibrary.pojo;

public class BaseData<T> {
    private int curPage;
    private int errorCode;
    private String errorMsg;
    private InnerData<T> data;

    public InnerData<T> getData() {
        return data;
    }

    public void setData(InnerData<T> data) {
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
