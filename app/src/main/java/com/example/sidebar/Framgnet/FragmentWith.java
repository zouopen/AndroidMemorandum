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
import butterknife.ButterKnife;

public class FragmentWith extends Fragment {
    @Bind(R.id.tv_time1) TextView tv_time;
    @Bind(R.id.et_text1) public EditText et_text1;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_with,container,false);
        ButterKnife.bind(this,view);
        InitData();
        return view;
    }

    private void InitData() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd HH:mm");
        tv_time.setText(sdf.format(dt));
    }
}

