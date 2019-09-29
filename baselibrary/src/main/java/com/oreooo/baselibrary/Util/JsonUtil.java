package com.oreooo.baselibrary.Util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Json序列化操作类
 */
public class JsonUtil {
    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
//            .registerTypeAdapter(java.sql.Date.class, new SQLDateTypeAdapter())
//            .registerTypeAdapter(Date.class, new DateAdapter())
//            .registerTypeAdapter(LinkedTreeMap.class, new MapTypeAdapter())
            //.registerTypeAdapter(Double.class, new IntegerTypeAdaptor())
            .serializeNulls().create();

    /**
     * Json数据序列化为对象
     *
     * @param json     Json字符串
     * @param classOfT 对象类型
     * @return 对象
     */
    public static <T> T toObject(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    /**
     * Json数据序列化为对象
     *
     * @param json     Json字符串
     * @param classOfT 对象类型
     * @return 对象
     */
    public static <T> T toObject(String json, java.lang.reflect.Type classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T toObject(String json, TypeToken<T> typeToken) {
        return gson.fromJson(json, typeToken.getType());
    }

    /**
     * 对象序列化Json字符串
     *
     * @param object 要序列化的对象
     * @param <T>
     * @return Json字符串
     */
    public static <T> String toJson(T object) {
        return gson.toJson(object);
    }
}
