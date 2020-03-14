package com.oreo.wxarticle.network

import com.oreooo.baselibrary.network.Network

class Api {
    companion object createWxArticleService {
        fun create():WxArticleService{
            return Network.getInstance().retrofit().create(WxArticleService::class.java)
        }
    }
}