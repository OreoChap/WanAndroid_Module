package com.oreooo.main.network

import com.oreooo.baselibrary.network.Network
import com.oreooo.main.MainActivity

class Api {
    companion object createLoginService {
        fun create(): LoginService {
            return Network.getInstance().retrofit(MainActivity.mContext).create(LoginService::class.java)
        }
    }
}