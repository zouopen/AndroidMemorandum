package com.example.sidebar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sidebar.DBHelper.DataWay;
import com.example.sidebar.Framgnet.FragmentInputBox;
import com.example.sidebar.Framgnet.FragmentTabBar;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 黄铿 on 2018/12/4.
 */

public class InputBoxActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton comeback,clean;
    private DataWay dataWay =new DataWay();
    private FragmentTabBar fragmentTabBar=new FragmentTabBar();
    private FragmentInputBox fragmentInputBox = new FragmentInputBox();

    private LinearLayout linearLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputbox);
        init();
        FragmentView();

    }
    private void FragmentView(){
        this.getSupportFragmentManager().beginTransaction()
                .add(R.id.tab_bar,fragmentTabBar)
                .add(R.id.input_box,fragmentInputBox)
                .commit();
    }
    private  void init(){
        comeback= findViewById(R.id.fanhui);
        clean   = findViewById(R.id.queren);
        linearLayout=findViewById(R.id.need_to_be_dealt_with);

        comeback.setOnClickListener(this);
        clean.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fanhui:
                String text = fragmentInputBox.et_text.getText().toString();
                if (text.equals("")){
                    Toast.makeText(this,"备忘录功能不为空",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(InputBoxActivity.this,MainActivity.class));
                }else {
                    fragmentInputBox.Time();
                    dataWay.addData(this,text,fragmentInputBox.getStr_time());
                    Toast.makeText(this,"添加成功",Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.queren:
                Toast.makeText(this,"清空！",Toast.LENGTH_SHORT).show();
                fragmentInputBox.et_text.setText("");
                break;

        }

    }
}
