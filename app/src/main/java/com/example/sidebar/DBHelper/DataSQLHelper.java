package com.example.sidebar.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.sidebar.Dao.DataDao;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;


/**
 * Created by 黄铿 on 2018/12/5.
 */

public class DataSQLHelper extends OrmLiteSqliteOpenHelper{
    private static final String dbName="memorandum.db";
    private static final  int version = 3;
    private static DataSQLHelper getDatabase;
    public  static synchronized DataSQLHelper getDatabase(Context context){
        if (getDatabase==null){
            getDatabase=new DataSQLHelper(context.getApplicationContext());
        }
        return getDatabase;
    }
    private DataSQLHelper(Context context) {
        super(context, dbName, null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,DataDao.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,DataDao.class,true);
            onCreate(database);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
