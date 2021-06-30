package com.oreooo.baselibrary.route;

public class RoutePath {

    private static final String PREFIX_BASELIBRARY = "/baseLibrary/";

    // WanAndroid
    private static final String PREFIX_WANANDROID = "/module_wanandroid/";
    public static final String WANANDROID_ACTIVITY = PREFIX_WANANDROID + "WanAndroidActivity";
    public static final String WEBVIEW_ACTIVITY = PREFIX_WANANDROID + "webview/" + "WebViewActivity";
    public static final String WANDROID_FRAGMENT = PREFIX_WANANDROID + "WanAndroidFragment";

    // WxArticle
    private static final String PREFIX_WXARTICLE = "/wxarticle/";
    public static final String WXARTICLE_FRAGMENT = PREFIX_WXARTICLE + "WxArticleFragment";

    // Search
    private static final String PREFIX_SEARCH = "/search/";
    public static final String SEARCH_ACTIVITY = PREFIX_SEARCH + "SearchActivity";

    // ToDo_list
    private static final String PREFIX_TODO = "/todo/";
    public static final String TODO_ACTIVITY = PREFIX_TODO + "ToDoActivity";

    // User
    private static final String PREFIX_USER = "/user/";
    public static final String USER_ACTIVITY = PREFIX_USER + "UserActivity";

    // Api
    private static final String PREFIX_API = "/api/";
    public static final String API_LOGIN = PREFIX_API + "LoginModel";
}