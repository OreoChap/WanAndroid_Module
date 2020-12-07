package com.oreooo.baselibrary.network;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 *  很久之前的网络请求框架了？
 */
public class Net {
    private Retrofit mRetrofit;
    public static String token = "";
    public static String IP;
    public static String PORT;

    public static Net getInstance() {
        return NetworkHolder.instance;
    }

    private Net(){}

    private static class NetworkHolder {
        private static Net instance = new Net();
    }

    public Retrofit retrofit() {
         //拦截器：打印网络请求
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog","retrofitBack = "+message);
            }
        });

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
                                      @Override
                                      public Response intercept(Chain chain) throws IOException {
                                          Request original = chain.request();
                                          Request request = original.newBuilder()
                                                  .header("token", token)
                                                  .header("Content-Type","application/json")
                                                  .method(original.method(), original.body())
                                                  .build();

                                          return chain.proceed(request);
                                      }
                                  });
        Log.i("netWorkLog","token = "+ token);
        OkHttpClient client = httpClient.addInterceptor(loggingInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        // 构建网络连接
        String loginIp = "http://" + IP + ":" + PORT + "/bmdl/";
        if (mRetrofit == null || ("").equals(token)) {
            if (IP != null && PORT != null) {
                mRetrofit = new Retrofit.Builder()
                        .baseUrl(loginIp)
                        .client(client)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
            } else {
                mRetrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.10:8080/bmdl/")
                        .client(client)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
            }
        }
        return mRetrofit;
    }
}
