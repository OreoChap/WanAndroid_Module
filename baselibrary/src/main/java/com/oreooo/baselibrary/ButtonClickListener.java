package com.oreooo.baselibrary;

import android.view.View;

public abstract class ButtonClickListener implements View.OnClickListener {
    private long mLastClickTime;
    private long timeInterval = 2000L;

    public ButtonClickListener() {}

    public ButtonClickListener(long interval) {
        this.timeInterval = interval;
    }

    @Override
    public void onClick(View v) {
        long nowTime = System.currentTimeMillis();
        if (nowTime - mLastClickTime > timeInterval) {
            // 单次点击事件
            onSingleClick();
            mLastClickTime = nowTime;
        } else {
            // 快速点击事件
            onFastClick();
        }
    }

    protected abstract void onSingleClick();
    protected void onFastClick(){}
}
