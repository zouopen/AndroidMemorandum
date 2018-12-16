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
import com.example.sidebar.Framgnet.FragmentInputBox;
import com.example.sidebar.Framgnet.FragmentWith;

import java.text.SimpleDateFormat;
import java.util.Date;

public class updateMainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_text;
    private ImageButton comeback,clean;
    private String id;
    private DataWay dataWay =new DataWay();
    protected FragmentInputBox fragmentInputBox = new FragmentInputBox();
    protected FragmentWith fragmentWith = new FragmentWith();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputbox);
        init();
        FragmentView();
//        queryById_AND_update();
    }
    @Override
    protected void onStart() {
        super.onStart();
        et_text =findViewById(R.id.et_text);

    }
    private void FragmentView(){
        this.getSupportFragmentManager().beginTransaction()
                .add(R.id.input_box,fragmentWith)       //待办页
                .add(R.id.input_box,fragmentInputBox)   //普通编辑页
                .commit();
    }
    private  void init(){

        comeback= findViewById(R.id.fanhui);
        clean   = findViewById(R.id.queren);
        Intent intent=getIntent();
        id = intent.getIntExtra("id",0)+"";
        comeback.setOnClickListener(this);
        clean.setOnClickListener(this);
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
                String text = fragmentInputBox.et_text.getText().toString();
                if (text.equals(queryById_AND_update())){
                    Toast.makeText(this,"怎么什么都没有修改？人要有远规划呀",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this,MainActivity.class));
                }else {
                    fragmentInputBox.Time();
                    dataWay.upData(this,id,fragmentInputBox.et_text.getText().toString(),fragmentInputBox.getStr_time());
                    Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this,MainActivity.class));
                }
                break;
            case R.id.queren:
                Toast.makeText(this,"清空！",Toast.LENGTH_SHORT).show();
               fragmentInputBox.et_text.setText("");
                break;
        }
    }
}
