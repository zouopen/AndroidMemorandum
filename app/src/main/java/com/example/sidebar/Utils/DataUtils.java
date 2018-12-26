package com.example.sidebar.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class DataUtils {
    public static final String LOGINPASSWORAD = "LONGSWORD";
    public static final String USERNAME       = "USERNAME";
    public static final String PASSWROD       = "PASSWORD";
    //保存数据
    public static void StoragePass(Context context,String user,String password){
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOGINPASSWORAD,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME,user);
        editor.putString(PASSWROD,password);
        editor.apply();
    }
}
