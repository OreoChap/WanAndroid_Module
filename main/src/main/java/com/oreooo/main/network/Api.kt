package com.oreooo.main.network

import com.oreooo.baselibrary.network.Network

class Api {
    companion object createLoginService {
        fun create(): LoginService {
            return Network.getInstance().retrofit().create(LoginService::class.java)
        }
    }
}