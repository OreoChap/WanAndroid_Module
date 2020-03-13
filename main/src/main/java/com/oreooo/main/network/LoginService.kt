package com.oreooo.main.network

import com.oreooo.main.pojo.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    @POST("user/login")
    @FormUrlEncoded
    suspend fun loginRequest(@Field("username") username: String?,
                     @Field("password") password: String?): User?
}