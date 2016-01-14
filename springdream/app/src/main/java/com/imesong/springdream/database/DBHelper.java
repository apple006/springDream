package com.imesong.springdream.database;

import android.content.Context;

import de.greenrobot.dao.async.AsyncSession;

/**
 * 数据库操作工具类
 */
public class DBHelper {
    private static final String DB_NAME = "zhgjm.sqlite";
    private static DBHelper instance = null;
    private DaoSession daoSession = null;
    private AsyncSession asyncSession = null;
    private CategoryDao categoryDao;
    private DreamDao dreamDao;

    private DBHelper(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, AssetsDatabaseManager.DB_NAME, null);

        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());

        daoSession = daoMaster.newSession();
        asyncSession = daoSession.startAsyncSession();
        categoryDao = daoSession.getCategoryDao();
        dreamDao = daoSession.getDreamDao();
    }

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    public CategoryDao getCategoryDao() {
        if (categoryDao == null) {
            categoryDao = daoSession.getCategoryDao();
        }
        return categoryDao;
    }

    public DreamDao getDreamDao() {
        if (dreamDao == null) {
            dreamDao = daoSession.getDreamDao();
        }
        return dreamDao;
    }
}
