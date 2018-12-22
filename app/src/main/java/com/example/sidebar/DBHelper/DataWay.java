package com.example.sidebar.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sidebar.Dao.DataDao;

import java.util.ArrayList;
import java.util.List;

/** 旧版数据库操作
 * Created by 黄铿 on 2018/12/7.
 * @deprecated 该方法已被重构,不推荐使用
 */
public class DataWay {
    /**
     * 查询所有
     * @param context
     * @return
     * @deprecated 该方法已被重构,不推荐使用
     */
    public List<DataDao> allQuery(Context context){
        List<DataDao> dataWayList=new ArrayList<DataDao>();
        DataSQLHelper dataSQLHelper = DataSQLHelper.getDatabase(context);
        SQLiteDatabase sqLiteDatabase= dataSQLHelper.getReadableDatabase();
        Cursor cursor =sqLiteDatabase.rawQuery("select * from "+DataDao.tbName,null);
        DataDao dataDao=null;
        while (cursor.moveToNext()){
            dataDao=new DataDao();
            dataDao.setText(cursor.getString(cursor.getColumnIndex(DataDao.TEXT)));
            dataDao.setTime(cursor.getString(cursor.getColumnIndex(DataDao.TIME)));
            dataDao.setId(cursor.getInt(cursor.getColumnIndex(DataDao.ID)));
            dataWayList.add(dataDao);
        }
        sqLiteDatabase.close();
        return  dataWayList;
    }

    /**
     * 查询单个ID
     * @param context
     * @param id
     * @return
     * @deprecated 该方法已被重构,不推荐使用
     */
    public DataDao idQuery(Context context,String id){
        DataSQLHelper dataSQLHelper = DataSQLHelper.getDatabase(context);
        SQLiteDatabase sqLiteDatabase= dataSQLHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + DataDao.tbName +" where "+ DataDao.ID +" =? ",
                new String [] {id});                                                                            //=?
        DataDao dataDao = null;
        while (cursor.moveToNext()){
            dataDao=new DataDao();
            dataDao.setText(cursor.getString(cursor.getColumnIndex(DataDao.TEXT)));
            dataDao.setTime(cursor.getString(cursor.getColumnIndex(DataDao.TIME)));
            dataDao.setId(cursor.getInt(cursor.getColumnIndex(DataDao.ID)));
            return dataDao;
        }
        sqLiteDatabase.close();
        return null;
    }

    /**
     * 模糊查询
     * @param context
     * @param text
     * @return
     * @deprecated 该方法已被重构,不推荐使用
     */
    public List<DataDao> textQuery(Context context,String text){
        List<DataDao> dataWayList=new ArrayList<DataDao>();
        DataSQLHelper dataSQLHelper = DataSQLHelper.getDatabase(context);
        SQLiteDatabase sqLiteDatabase= dataSQLHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + DataDao.tbName +" where "+ DataDao.TEXT +" like ?",
                new String [] {"%"+text+"%"});                                                                            //=?
        DataDao dataDao = null;
        while (cursor.moveToNext()){
            dataDao=new DataDao();
            dataDao.setText(cursor.getString(cursor.getColumnIndex(DataDao.TEXT)));
            dataDao.setTime(cursor.getString(cursor.getColumnIndex(DataDao.TIME)));
            dataDao.setId(cursor.getInt(cursor.getColumnIndex(DataDao.ID)));
            dataWayList.add(dataDao);
        }
        sqLiteDatabase.close();
        return  dataWayList;
    }

    /**
     * 添加数据
     * @param context
     * @param text
     * @param time
     * @deprecated 该方法已被重构,不推荐使用
     */
    public void addData(Context context,String text,String time){
        DataSQLHelper dataSQLHelper = DataSQLHelper.getDatabase(context);
        SQLiteDatabase sqLiteDatabase= dataSQLHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DataDao.TEXT,text);
        values.put(DataDao.TIME,time);
        sqLiteDatabase.insert(DataDao.tbName,DataDao.ID,values);
        sqLiteDatabase.close();
    }

    /**
     * 更新数据
     * @param context
     * @param id
     * @param text
     * @param time
     * @deprecated 该方法已被重构,不推荐使用
     */
    public void upData(Context context,String id,String text,String time){
        DataSQLHelper dataSQLHelper = DataSQLHelper.getDatabase(context);
        SQLiteDatabase sqLiteDatabase= dataSQLHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DataDao.TEXT,text);
        values.put(DataDao.TIME,time);
        sqLiteDatabase.update(DataDao.tbName,values,DataDao.ID +"=?",new String []{id});
        sqLiteDatabase.close();
    }

    /**
     * 删除数据
     * @param context
     * @param id
     * @deprecated 该方法已被重构,不推荐使用
     */
    public  void  deleteData(Context context,String id){
        DataSQLHelper dataSQLHelper = DataSQLHelper.getDatabase(context);
        SQLiteDatabase sqLiteDatabase = dataSQLHelper.getWritableDatabase();
        sqLiteDatabase.delete(DataDao.tbName,DataDao.ID+"=?",new String[]{id});
        sqLiteDatabase.close();
    }
}
