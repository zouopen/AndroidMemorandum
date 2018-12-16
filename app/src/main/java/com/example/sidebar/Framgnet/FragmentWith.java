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

public class FragmentWith extends Fragment {
    private Date dt;
    private String str_time;
    public TextView tv_time;
    public EditText et_text;
    public Fragment fragment=null;
    public String getStr_time() {
        return str_time;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_with,container,false);
        initView(view);
        Time();
        tv_time.setText(str_time);
        return view;
    }
    private void initView(View view){
        tv_time = view.findViewById(R.id.tv_time1);
        et_text = view.findViewById(R.id.et_text1);
    }
    //    获取时间并设置到TextView
    public void Time(){
        dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd HH:mm");
        str_time = sdf.format(dt);

    }
}
