package com.example.sidebar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sidebar.Framgnet.FragmentInputBox;
import com.example.sidebar.Framgnet.FragmentWith;


import butterknife.Bind;
import butterknife.ButterKnife;

public class updateMainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.fanhui)  ImageButton comeback;
    @Bind(R.id.queren)  ImageButton clean;
    private String id;
    protected FragmentInputBox fragmentInputBox = new FragmentInputBox();
    protected FragmentWith fragmentWith = new FragmentWith();
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
    private  void init(){
        Intent intent=getIntent();
        id = intent.getIntExtra("id",0)+"";
        comeback.setOnClickListener(this);
        clean.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fanhui:
                Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.queren:
                fragmentInputBox.et_text.setText(" ");
                Toast.makeText(this,"清空！",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    //获取到Main传来的id再传入fragmentInputBox
    public String setId(){
        return id;
    }
}
