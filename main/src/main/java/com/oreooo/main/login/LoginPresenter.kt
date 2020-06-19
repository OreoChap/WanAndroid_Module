package com.oreooo.main.login

import android.util.Log
import com.oreooo.baselibrary.mvpbase.BaseContract
import com.oreooo.baselibrary.network.Network
import com.oreooo.baselibrary.network.OkHttpClientManager
import com.oreooo.baselibrary.network.ResultCallback
import com.oreooo.main.network.Api
import com.oreooo.main.pojo.User
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.*
import okhttp3.Request

class LoginPresenter : LoginContract.Presenter {

    private lateinit var mView: LoginContract.View
    private val co: CompositeDisposable = CompositeDisposable()

    companion object {
        val instance: LoginPresenter by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            LoginPresenter()
        }
    }

    override fun login(userName: String, password: String) {
        Log.d("LoginPresenter", "login: 开始了～")
        GlobalScope.launch {
            val result = async {
                Api.create().loginRequest(userName, password)
            }

            withContext(Dispatchers.Main) {
                mView.loginResult(result.await())
            }
        }
//        val map = HashMap<String,String>()
//        map.put("username", userName)
//        map.put("password", password)
//        OkHttpClientManager.postAsync("https://www.wanandroid.com/user/login",map,object :ResultCallback<User>() {
//            override fun onError(request: Request, exception: Exception) {
//                Log.d("aabbcc","loginError")
//            }
//
//            override fun onResponse(response: User) {
//                mView.loginResult(response)
//            }
//        })
    }

    override fun clearRequest() {
        co.clear()
    }

    override fun skipLogin() {
        mView.loginResult(null)
    }

    override fun setView(view: BaseContract.BaseView?) {
        mView = view as? LoginContract.View ?: return
    }

    fun Disposable.addTo(c: CompositeDisposable) {
        c.add(this)
    }
}