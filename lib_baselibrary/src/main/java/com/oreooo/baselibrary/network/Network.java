package com.oreooo.baselibrary.network;

import android.content.Context;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Oreo https://github.com/OreoChap
 * @date 2018/12/20
 */
public class Network {
    private Retrofit mRetrofit;
    private String userName = "";
    private String password = "";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return password;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public static Network getInstance() {
        return NetworkHolder.instance;
    }

    private Network() {
    }

    private static class NetworkHolder {
        private static Network instance = new Network();
    }

    public Retrofit retrofit(Context context) {
        if (null == mRetrofit) {
//            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//                @Override
//                public void log(String message) {
//                    //打印retrofit日志
//                    Log.i("RetrofitLog", "retrofitBack = " + message);
//                }
//            });
//            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//            httpClient.addInterceptor(new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    Request original = chain.request();
//                    Request request = original.newBuilder()
//                            .header("Content-Type", "application/json")
//                            .header("username", getUserName())
//                            .header("password", getPassWord())
//                            .method(original.method(), original.body())
//                            .build();
//
//                    return chain.proceed(request);
//                }
//            });

            OkHttpClient client = new OkHttpClient.Builder()
                    .cookieJar(new PersistentCookieJar(new SetCookieCache(),
                            new SharedPrefsCookiePersistor(context)))
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .build();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Profile.API_BASE)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(JacksonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return mRetrofit;
    }

//    public class HeaderInterceptor implements Interceptor {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request request = chain.request()
//                    .newBuilder()
//                    .addHeader("appid", "hello")
//                    .addHeader("deviceplatform", "android")
//                    .removeHeader("User-Agent")
//                    .addHeader("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:38.0) Gecko/20100101 Firefox/38.0")
//                    .build();
//            Response response = chain.proceed(request);
//            return response;
//        }
//    }
}
