package com.oreo.wxarticle.network

import com.oreo.wxarticle.WxArticleAct
import com.oreooo.baselibrary.network.Network

class Api {
    companion object createWxArticleService {
        fun create():WxArticleService{
            return Network.getInstance().retrofit(WxArticleAct.mContext).create(WxArticleService::class.java)
        }
    }
}