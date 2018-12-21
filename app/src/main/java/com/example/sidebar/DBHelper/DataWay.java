package com.example.sidebar.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sidebar.Dao.DataDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄铿 on 2018/12/7.
 */

public class DataWay {
    public List<DataDao> allQuery(Context context){
        List<DataDao> dataWayList=new ArrayList<DataDao>();

        DateHelper dateHelper=DateHelper.getDatabase(context);
        SQLiteDatabase sqLiteDatabase= dateHelper.getReadableDatabase();
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
    public DataDao idQuery(Context context,String id){
        DateHelper dateHelper=DateHelper.getDatabase(context);
        SQLiteDatabase sqLiteDatabase= dateHelper.getReadableDatabase();
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
    public List<DataDao> textQuery(Context context,String text){
        List<DataDao> dataWayList=new ArrayList<DataDao>();
        DateHelper dateHelper=DateHelper.getDatabase(context);
        SQLiteDatabase sqLiteDatabase= dateHelper.getReadableDatabase();
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
    public void addData(Context context,String text,String time){
        DateHelper dateHelper=DateHelper.getDatabase(context);
        SQLiteDatabase sqLiteDatabase=dateHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DataDao.TEXT,text);
        values.put(DataDao.TIME,time);
        sqLiteDatabase.insert(DataDao.tbName,DataDao.ID,values);
        sqLiteDatabase.close();
    }
    public void upData(Context context,String id,String text,String time){
        DateHelper dateHelper=DateHelper.getDatabase(context);
        SQLiteDatabase sqLiteDatabase=dateHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DataDao.TEXT,text);
        values.put(DataDao.TIME,time);
        sqLiteDatabase.update(DataDao.tbName,values,DataDao.ID +"=?",new String []{id});
        sqLiteDatabase.close();
    }
    public  void  deleteData(Context context,String id){
        DateHelper dateHelper=DateHelper.getDatabase(context);
        SQLiteDatabase sqLiteDatabase =dateHelper.getWritableDatabase();
        sqLiteDatabase.delete(DataDao.tbName,DataDao.ID+"=?",new String[]{id});
        sqLiteDatabase.close();
    }
}
