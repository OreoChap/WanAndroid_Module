package com.oreooo.baselibrary.util.countdown;

import android.os.Handler;
import android.util.Log;

/**
 * 计时工具类
 * @author xuan
 * @date 2020/2/24
 */
public class CountdownUtils {

    private static final String TAG = "CountdownUtils";

    private int mTotal;
    private long mStartTime;
    private int mCurrent;

    private Handler mHandler;
    private ICountdownListener mListener;

    public CountdownUtils(int total, ICountdownListener listener) {
        this.mTotal = total;
        mHandler = new Handler();
        mListener = listener;
    }

    public void start() {
        mStartTime = System.currentTimeMillis();
        if (mListener != null) {
            mListener.onProgress(mTotal - mCurrent);
            mCurrent++;
        }
        mHandler.postDelayed(countdownRunnable, 1000);
    }

    private Runnable countdownRunnable = new Runnable() {
        @Override
        public void run() {
            int t = mTotal - mCurrent;
            if (mListener != null) {
                if (t >= 0) {
                    mListener.onProgress(t);
                    mCurrent++;
                    long interval = (mCurrent * 1000) - ((System.currentTimeMillis() - mStartTime));

                    mHandler.postDelayed(countdownRunnable, interval);
                    Log.e(TAG, "countdownTime : " + t + "next interval : " + interval);
                } else {
                    mListener.onDone();
                    long endTime = System.currentTimeMillis();
                    Log.e(TAG, "total time : " + (endTime - mStartTime));
                }
            }
        }
    };
}
