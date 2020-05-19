package com.oreooo.baselibrary.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.CursorWrapper;

import com.oreooo.baselibrary.util.StringUtil;
import com.tencent.wcdb.Cursor;
import com.tencent.wcdb.database.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.util.Log.d;
import static com.oreooo.baselibrary.sql.SqliteHelper.Schema.SCANTable.Cols.FEATURE;
import static com.oreooo.baselibrary.sql.SqliteHelper.Schema.SCANTable.Cols.JSONVALUE;
import static com.oreooo.baselibrary.sql.SqliteHelper.Schema.SCANTable.Cols.TIME;
import static com.oreooo.baselibrary.sql.SqliteHelper.Schema.SCANTable.Cols.USERNAME;
import static com.oreooo.baselibrary.sql.SqliteHelper.Schema.SCANTable.TABLENAME;

public class SqliteDBManager {
    private SqliteHelper mDBHelper;
    private SQLiteDatabase mDB;
    private volatile static SqliteDBManager sqliteDBManager;

    // 单例模式调用
    public static SqliteDBManager getInstance(Context context) {
        if (sqliteDBManager == null) {
            synchronized (SqliteDBManager.class) {
                if (sqliteDBManager == null) {
                    sqliteDBManager = new SqliteDBManager(context);
                }
            }
        }
        return sqliteDBManager;
    }

    private SqliteDBManager(Context context) {
        mDBHelper = new SqliteHelper(context, "AppUI.db", "ScanAppFFF");
        mDB = mDBHelper.getReadableDatabase();
        createTable();
    }

    /**
     * 批量写入数据
     *
     * @param tableName
     * @param maps
     */
    private void insertData(String tableName, List<Map<String, Object>> maps) {
        try {
            // 开启事务
            mDB.beginTransaction();
            for (Map<String, Object> map : maps) {
                insertDate(tableName, map);
            }
            // 设置事务完成成功
            mDB.setTransactionSuccessful();
        } finally {
            // 关闭事务
            mDB.endTransaction();
        }
    }

    /**
     * 写入数据
     *
     * @param tableName
     * @param map
     */
    private void insertDate(String tableName, Map<String, Object> map) {
        boolean inTransaction = false;
        if (!mDB.inTransaction()) {
            inTransaction = true;
            mDB.beginTransaction();
        }
        if (map != null) {
            String columnsSQL = "";
            String valuesSQL = "";
            Object[] values = new Object[map.size()];
            int index = 0;
            for (String key : map.keySet()) {
                if (StringUtil.isNotEmpty(columnsSQL)) {
                    columnsSQL += ",";
                }
                columnsSQL += key;

                if (StringUtil.isNotEmpty(valuesSQL)) {
                    valuesSQL += ",";
                }
                valuesSQL += "?";

                values[index] = map.get(key);
                index += 1;
            }
            String sql = "Insert Into " + tableName + "(" + columnsSQL + ")values(" + valuesSQL + ")";
            mDB.execSQL(sql, values);
        }
        if (inTransaction) {
            mDB.setTransactionSuccessful();
        }
    }

    /**
     * @param keyColumns 数据列名
     * @param jsonData   JSON数据
     * @return
     */
    public boolean insertData(String keyColumns, String jsonData) {
        try {
            boolean inTransaction = false;
            if (!mDB.inTransaction()) {
                inTransaction = true;
                mDB.beginTransaction();
            }

            if (inTransaction) {
                mDB.setTransactionSuccessful();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查询整张表的数据
     *
     * @param tableName 表名
     * @return
     */
    private List<Map<String, Object>> find(String tableName) {
        String sql = "SELECT * FROM " + tableName;
        return find(sql, null);
    }

    /**
     * 查询数据
     *
     * @param sql       SQL命令
     * @param selection 查询条件值
     * @return
     */
    private List<Map<String, Object>> find(String sql, Object[] selection) {
        List<Map<String, Object>> listData = new ArrayList<>();
        Cursor c = querySQL(sql, selection);
        while (c.moveToNext()) {
            Map<String, Object> map = new HashMap<>();
            int count = c.getColumnCount();
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    String column = c.getColumnName(i);
                    Object value = null;
                    if (!c.isNull(i)) {
                        int typeId = c.getType(i);
                        switch (typeId) {
                            case Cursor.FIELD_TYPE_NULL:
                                break;
                            case Cursor.FIELD_TYPE_INTEGER:
                                value = c.getInt(i);
                                break;
                            case Cursor.FIELD_TYPE_FLOAT:
                                value = c.getFloat(i);
                                break;
                            case Cursor.FIELD_TYPE_STRING:
                                value = c.getString(i);
                                break;
                            case Cursor.FIELD_TYPE_BLOB:
                                value = c.getBlob(i);
                                break;
                            default:
                                value = c.getString(i);
                                break;
                        }
                    }
                    map.put(column, value);
                }
            }
            listData.add(map);
        }
        c.close();
        return listData;
    }

    /**
     * 查询整张表的数据
     *
     * @param tableName 表名
     * @return
     */
    private Cursor getTable(String tableName) {
        String sql = "SELECT * FROM " + tableName;
        return querySQL(sql, null);
    }

    /**
     * 查询数据
     *
     * @param sql       SQL命令
     * @param selection 查询条件值
     * @return
     */
    private Cursor querySQL(String sql, Object[] selection) {
        return mDB.rawQuery(sql, selection);
    }

    /**
     * 关闭  database；
     */
    public void closeDB() {
        mDB.close();
    }

    /**
     * 删除数据库
     */
    public Boolean dropDB() {
        return mDBHelper.onDelete();
    }

    private void CreateSqlite() {

    }

    public void insert(String featureKey, String jsonData) {
        boolean inTransaction = false;
        if (!mDB.inTransaction()) {
            inTransaction = true;
            mDB.beginTransaction();
        }
        try {
//            featureKey = featureKey + "-" + AppUtil.ResId + "-" + AppUtil.BookId + "-" + AppUtil.ProjectId;
            mDB.insert(TABLENAME, null, getContentValue(featureKey, jsonData));
            if (inTransaction) {
                mDB.setTransactionSuccessful();
            }

            d("SqliteDB", "insertSucceed!");
        } catch (Exception e) {
            d("SqliteDB", e.toString());
        } finally {
            mDB.endTransaction();
        }
    }

    public void update(String featureKey, String jsonValue) {
        try {
//            featureKey = featureKey + "-" + AppUtil.ResId + "-" + AppUtil.BookId + "-" + AppUtil.ProjectId;
            ContentValues values = getContentValue(featureKey, jsonValue);
            mDB.update(TABLENAME, values, FEATURE + "= ?", new String[]{featureKey});

            d("SqliteDB", "updateSucceed!");
        } catch (Exception e) {
            d("SqliteDB", e.toString());
        }
    }

    public SqliteUnit findDate(String featureKey) {
        try {
//            featureKey = featureKey + "-" + AppUtil.ResId + "-" + AppUtil.BookId + "-" + AppUtil.ProjectId;
            List<SqliteUnit> data = new ArrayList<>();
            Cursor cursor = mDB.query(TABLENAME, null, FEATURE + " = ?", new String[]{featureKey},
                    null, null, null);
            JSONCursorWrapper cursorWrapper = new JSONCursorWrapper(cursor);
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                data.add(cursorWrapper.getUnit());
                cursorWrapper.moveToNext();
            }
            int position = 0;
            if (data.size() > 1) {
                Integer time = data.get(0).getTime();
                for (int i = 1; i < data.size(); i++) {
                    if (data.get(i).getTime() > time) {
                        time = data.get(i).getTime();
                        position = i;
                    }
                }
            }
            if (data.size() == 0) {
                return null;
            }

            d("SqliteDB", "findDateSucceed!");
            return data.get(position);
        } catch (Exception e) {
            d("SqliteDB", e.toString());
            return null;
        }
    }

    public class JSONCursorWrapper extends CursorWrapper {

        JSONCursorWrapper(Cursor cursor) {
            super(cursor);
        }

        public SqliteUnit getUnit() {
            SqliteUnit unit = new SqliteUnit();
            unit.setFeature(getString(getColumnIndex(FEATURE)));
            unit.setUserName(getString(getColumnIndex(USERNAME)));
            unit.setJsonValue(getString(getColumnIndex(JSONVALUE)));
            unit.setTime(getInt(getColumnIndex(TIME)));
            return unit;
        }
    }

    private void createTable() {
        try {
            final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLENAME + "("
                    + " _id INTEGER PRIMARY KEY AUTOINCREMENT , " + USERNAME + ", " + FEATURE + " TEXT " + ", "
                    + JSONVALUE + ", " + TIME + " TINYINT(20)" + ")";
            mDB.execSQL(SQL_CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ContentValues getContentValue(String featureKey, String jsonValue) {
        ContentValues contentValues = new ContentValues();
        SqliteUnit unit = findDate(featureKey);
        if (unit != null) {
            contentValues.put(USERNAME, unit.getUserName());
            contentValues.put(FEATURE, unit.getFeature());
            contentValues.put(JSONVALUE, jsonValue);
            contentValues.put(TIME, System.currentTimeMillis());
        } else {
            String userId = mDBHelper.getContext().getSharedPreferences("", Context.MODE_PRIVATE).getString("userName", "");
            contentValues.put(USERNAME, userId);
            contentValues.put(FEATURE, featureKey);
            contentValues.put(JSONVALUE, jsonValue);
            contentValues.put(TIME, System.currentTimeMillis());
        }
        return contentValues;
    }
}
