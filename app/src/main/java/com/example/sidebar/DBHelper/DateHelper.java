package com.example.sidebar.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sidebar.Dao.DataDao;


/**
 * Created by 黄铿 on 2018/12/5.
 */

public class DateHelper extends SQLiteOpenHelper{
    private static final String dbName="memorandum.db";
    private static final  int version=1;
    private static DateHelper getDatabase;
    public  static synchronized  DateHelper getDatabase(Context context){
        if (getDatabase==null){
            getDatabase=new DateHelper(context);
        }
        return getDatabase;
    }
    public DateHelper(Context context) {
        super(context, dbName, null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ DataDao.tbName +"("
                    + DataDao.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + DataDao.TEXT + " VARCHAR(20),"
                    + DataDao.TIME + " VARCHAR(20)"
                    +")" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
