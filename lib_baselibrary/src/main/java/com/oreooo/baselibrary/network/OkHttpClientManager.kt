package com.oreooo.baselibrary.network

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import com.google.gson.internal.`$Gson$Types`
import okhttp3.*
import java.io.IOException
import java.lang.Exception
import java.lang.RuntimeException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

object OkHttpClientManager {
    private val mOkHttpClient by lazy {
        OkHttpClient()
    }

    private val mHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    private val mGson by lazy {
        Gson()
    }

    // 同步Get请求
    private fun getSync(url: String): Response {
        val request = Request.Builder()
                .url(url)
                .build()
        val call = mOkHttpClient.newCall(request)
        return call.execute()
    }

    fun getSyncString(url: String): String {
        return getSync(url).body()!!.string()
    }

    // 异步Get请求
    fun getAsync(url: String, callback: ResultCallback<*>) {
        val request = Request.Builder()
                .url(url)
                .build()
        deliverResult(callback, request)
    }

    private fun deliverResult(callback: ResultCallback<*>, request: Request) {
        mOkHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                sendFailedStringCallback(request, e, callback)
            }

            override fun onResponse(call: Call, response: Response) {
                val string = response?.body()!!.string()
                val mObject = mGson.fromJson<Any>(string, callback.mType)
                sendSuccessResultCallback(mObject, callback as ResultCallback<Any>)
            }
        })
    }

    private fun sendFailedStringCallback(request: Request, exception: Exception, callback: ResultCallback<*>) {
        mHandler.post {
            callback.onError(request, exception)
        }
    }

    private fun sendSuccessResultCallback(mObject: Any, callback: ResultCallback<Any>) {
        mHandler.post {
            callback.onResponse(mObject)
        }
    }
}

abstract class ResultCallback<T> {
    val mType: Type by lazy {
        getSuperclassTypeParameter(javaClass)
    }

    companion object {
        fun getSuperclassTypeParameter(subclass: Class<*>): Type {
            val superclass = subclass.genericSuperclass
            if (superclass is Class<*>) {
                throw RuntimeException("Miss type parameter.")
            }
            val parameterizedType = superclass as ParameterizedType
            return `$Gson$Types`.canonicalize(parameterizedType.actualTypeArguments[0])
        }
    }

    abstract fun onError(request: Request, exception: Exception)
    abstract fun onResponse(response: T)
}