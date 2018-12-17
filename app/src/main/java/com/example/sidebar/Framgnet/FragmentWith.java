package com.example.sidebar.Framgnet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sidebar.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;

public class FragmentWith extends Fragment {
    @Bind(R.id.tv_time1) TextView tv_time;
    @Bind(R.id.et_text) EditText et_text;
    private String str_time;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_with,container,false);
        Time();
        tv_time.setText(str_time);
        return view;
    }
    //    获取时间并设置到TextView
    public void Time(){
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd HH:mm");
        str_time = sdf.format(dt);
    }
}
