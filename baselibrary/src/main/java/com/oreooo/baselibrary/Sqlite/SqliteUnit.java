package com.oreooo.baselibrary.Sqlite;

public class SqliteUnit {
    private String UserName;
    private String Feature;
    private String JsonValue;
    private Integer Time;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFeature() {
        return Feature;
    }

    public void setFeature(String feature) {
        Feature = feature;
    }

    public String getJsonValue() {
        return JsonValue;
    }

    public void setJsonValue(String jsonValue) {
        JsonValue = jsonValue;
    }

    public Integer getTime() {
        return Time;
    }

    public void setTime(Integer time) {
        Time = time;
    }
}
