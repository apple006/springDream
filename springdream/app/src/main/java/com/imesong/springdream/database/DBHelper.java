package com.imesong.springdream.database;

import android.content.Context;

/**
 * 数据库操作工具类
 */
public class DBHelper {
    public static DBHelper instance = null;

    private DBHelper(Context context) {
        //        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        //
        //        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        //
        //        daoSession = daoMaster.newSession();
        //        asyncSession = daoSession.startAsyncSession();
    }

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }

        return instance;
    }
}
