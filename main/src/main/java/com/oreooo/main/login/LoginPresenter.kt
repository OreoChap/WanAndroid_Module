package com.oreooo.main.login

import android.util.Log
import com.oreooo.baselibrary.mvp.BaseContract
import com.oreooo.main.network.Api
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.*

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
    }

    override fun clearRequest() {
        co.dispose()
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