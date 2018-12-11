package com.example.sidebar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sidebar.DBHelper.DataWay;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 黄铿 on 2018/12/4.
 */

public class InputBoxActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_time;
    private EditText et_text;
    private ImageButton comeback,clean;
    private String content;
    private String id;
    private DataWay dataWay =new DataWay();
    private Date dt;
    private String str_time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputbox);
        init();
        Time();
    }
    private  void init(){
        tv_time = findViewById(R.id.tv_time);
        et_text = findViewById(R.id.et_text);
        comeback= findViewById(R.id.fanhui);
        clean   = findViewById(R.id.queren);
        Intent intent=getIntent();
        content =intent.getStringExtra("text");
        id = intent.getIntExtra("id",1000000000)+"";
        et_text.setText(content);
        comeback.setOnClickListener(this);
        clean.setOnClickListener(this);
    }
    private void Time(){
         dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd HH:mm");
        str_time = sdf.format(dt);
        tv_time.setText(str_time);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fanhui:
                String text = et_text.getText().toString();
                if (text.equals("")){
                    Toast.makeText(this,"备忘录功能不为空",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(InputBoxActivity.this,MainActivity.class));
                }else {
                    dataWay.addData(this,text,str_time);
                    Toast.makeText(this,"添加成功",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(InputBoxActivity.this,MainActivity.class));
                }
                break;
            case R.id.queren:
                Toast.makeText(this,"清空！",Toast.LENGTH_SHORT).show();
                et_text.setText("");
                break;
        }

    }
}
