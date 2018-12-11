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
import com.example.sidebar.Dao.DataDao;
import java.text.SimpleDateFormat;
import java.util.Date;

public class updateMainActivity extends AppCompatActivity implements View.OnClickListener {
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
        queryById_AND_update();
        Time();
    }
    private  void init(){
        tv_time = findViewById(R.id.tv_time);
        et_text = findViewById(R.id.et_text);
        comeback= findViewById(R.id.fanhui);
        clean   = findViewById(R.id.queren);
        Intent intent=getIntent();
        content =intent.getStringExtra("text");
        id = intent.getIntExtra("id",0)+"";
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
    private String queryById_AND_update(){
        DataDao dataDao = dataWay.idQuery(this, id);
        et_text.setText(dataDao.getText());
        return dataDao.getText();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fanhui:
                String text = et_text.getText().toString();
                if (text.equals(queryById_AND_update())){
                    Toast.makeText(this,"怎么什么都没有修改？人要有远规划呀",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this,MainActivity.class));
                }else {
                    dataWay.upData(this,id,et_text.getText().toString(),str_time);
                    Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this,MainActivity.class));
                }
                break;
            case R.id.queren:
                Toast.makeText(this,"清空！",Toast.LENGTH_SHORT).show();
                et_text.setText("");
                break;
        }
    }
}
