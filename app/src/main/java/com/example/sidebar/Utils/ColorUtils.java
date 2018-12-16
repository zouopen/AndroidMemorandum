package com.example.sidebar.Utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.design.widget.NavigationView;

/**
 * 设置工具
 */
public class ColorUtils {
    //设置menu类目颜色
    public static void settingColor(int id, NavigationView navigationView, Context context){
        for (int i = 0; i <= 3 ; i++) {
            Resources resource = context.getResources();
            ColorStateList csl = resource.getColorStateList(id);
            navigationView.setItemTextColor(csl);
            navigationView.getMenu().getItem(i).setChecked(true);
        }
    }
}
