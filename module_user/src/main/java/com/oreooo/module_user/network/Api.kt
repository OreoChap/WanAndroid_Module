package com.oreooo.module_user.network

import com.oreooo.baselibrary.network.Network

class Api {
    companion object {
        fun createUserService(): UserService {
            return Network.getInstance().retrofit().create(UserService::class.java)
        }
    }
}