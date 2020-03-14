package com.oreooo.baselibrary.util.countdown;

/**
 *  计时器接口
 * @author xuan
 */

public interface ICountdownListener {
    void onProgress(int time);
    void onDone();
}
