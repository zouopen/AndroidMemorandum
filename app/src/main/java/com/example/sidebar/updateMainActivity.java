package com.example.sidebar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sidebar.DBHelper.DataWay;
import com.example.sidebar.Framgnet.FragmentInputBox;
import com.example.sidebar.Framgnet.FragmentWith;


import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class updateMainActivity extends AppCompatActivity {
    @Bind(R.id.fanhui)  ImageButton comeback;
    @Bind(R.id.queren)  ImageButton clean;
    private String id;
    protected FragmentInputBox fragmentInputBox = new FragmentInputBox();
    protected FragmentWith fragmentWith = new FragmentWith();
    protected DataWay dataWay = new DataWay();
    private SimpleDateFormat sdf;
    private Date dt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputbox);
        ButterKnife.bind(this);
        init();
        FragmentView();
    }
    private void FragmentView(){
        this.getSupportFragmentManager().beginTransaction()
                .add(R.id.input_box,fragmentWith)       //待办页
                .add(R.id.input_box,fragmentInputBox)   //普通编辑页
                .commit();
    }
    @SuppressLint("SimpleDateFormat")
    private  void init(){
        dt = new Date();
        sdf = new SimpleDateFormat("yyyy年MM月dd HH:mm");
        Intent intent=getIntent();
        id = intent.getIntExtra("id",0)+"";
    }
    @OnClick(R.id.fanhui) void ComeBack(){
        String et_text = fragmentInputBox.et_text.getText().toString();
        dataWay.upData(getApplicationContext(),id,et_text,sdf.format(dt));
        Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(updateMainActivity.this,MainActivity.class));
    }
    @OnClick(R.id.queren) void Clear(){
        fragmentInputBox.et_text.setText(" ");
        Toast.makeText(this,"清空！",Toast.LENGTH_SHORT).show();
    }
    //获取到Main传来的id再传入fragmentInputBox
    public String setId(){
        return id;
    }
}
