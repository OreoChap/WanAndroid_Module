package com.oreooo.wanandroid.network;

import com.oreooo.wanandroid.network.Service.WanAndroidService;

/**
 * @author Oreo https://github.com/OreoChap
 * @date 2018/12/18
 */
public class Api {
    public static WanAndroidService createWanAndroidService() {
        return Network.getInstance().retrofit().create(WanAndroidService.class);
    }
}
