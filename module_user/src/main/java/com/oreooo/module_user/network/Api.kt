package com.oreooo.module_user.network

import com.oreooo.baselibrary.network.Network
import com.oreooo.module_user.UserAct

class Api {
    companion object {
        fun createUserService(): UserService {
            return Network.getInstance().retrofit(UserAct.mContext)
                    .create(UserService::class.java)
        }
    }
}