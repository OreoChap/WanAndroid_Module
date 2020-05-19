package com.oreooo.baselibrary.sql;

import android.content.Context;

import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteOpenHelper;

import java.io.File;

public class SqliteHelper extends SQLiteOpenHelper {
    // 数据库 db 文件名称
    private static String DEFAULT_NAME = "";

    // 默认版本号
    private static final int DEFAULT_VERSION = 3;

    private Context mContext;

    /**
     * 通过父类构造方法创建 AppUI 数据库
     */
    public SqliteHelper(Context context, String dbName) {
        super(context, dbName, null, DEFAULT_VERSION, null);
        this.mContext = context;
        this.DEFAULT_NAME = dbName;
    }

    /**
     * 通过父类构造方法创建 AppUI 数据库
     */
    public SqliteHelper(Context context, String dbName, String password) {
        this(context, dbName, password.getBytes());
        this.mContext = context;
        this.DEFAULT_NAME = dbName;
    }

    /**
     * 通过父类构造方法创建 AppUI 数据库
     */
    public SqliteHelper(Context context, String dbName, byte[] password) {
        super(context, dbName, password, null, DEFAULT_VERSION, null);
        this.mContext = context;
        this.DEFAULT_NAME = dbName;
    }

    /**
     * 表创建
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
//        final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS person (_id INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR(20) , address TEXT)";
//        db.execSQL(SQL_CREATE);
    }

    /**
     * 版本升级
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 删除数据库 db 文件
     */
    public boolean onDelete() {
        File file = mContext.getDatabasePath(DEFAULT_NAME);
        return SQLiteDatabase.deleteDatabase(file);
    }

    public Context getContext() {
        return mContext;
    }

    class Schema {
        final class SCANTable {
            static final String TABLENAME = "ScanApp";

            final class Cols {
                static final String USERNAME = "user";
                static final String FEATURE = "feature";
                static final String JSONVALUE = "jsonValue";
                static final String TIME = "time";
            }
        }
    }

    public final class DataName {

        // List<InventoryTask>
        public static final String InventoryData = "inventory_data";

        // List<ProduceItem>
        public static final String ProduceData = "produce_data";
    }
}
