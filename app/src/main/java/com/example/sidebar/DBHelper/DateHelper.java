package com.example.sidebar.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sidebar.Dao.DataDao;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;


/**
 * Created by 黄铿 on 2018/12/5.
 */

public class DateHelper extends OrmLiteSqliteOpenHelper{
    private static final String dbName="memorandum.db";
    private static final  int version=1;
    private static DateHelper getDatabase;
    public  static synchronized  DateHelper getDatabase(Context context){
        if (getDatabase==null){
            getDatabase=new DateHelper(context);
        }
        return getDatabase;
    }
    private DateHelper(Context context) {
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
