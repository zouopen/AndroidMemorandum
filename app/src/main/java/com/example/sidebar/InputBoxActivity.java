package com.example.sidebar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sidebar.DBHelper.DataWay;
import com.example.sidebar.Framgnet.FragmentAdd;
import com.example.sidebar.Framgnet.FragmentInputBox;
import com.example.sidebar.Framgnet.FragmentWith;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 黄铿 on 2018/12/4.
 */

public class InputBoxActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.et_text) EditText et_text;
    @Bind(R.id.fanhui)  ImageButton comeback;
    @Bind(R.id.queren)  ImageButton clean;
    @Bind(R.id.need_to_be_dealt_with) LinearLayout need_to_be_dealt_with;
    protected FragmentAdd  fragmentAdd  = new FragmentAdd();
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
                .add(R.id.input_box,fragmentAdd)       //普通编辑页
                .commit();
    }
    private  void init(){
        comeback.setOnClickListener(this);
        clean.setOnClickListener(this);
        need_to_be_dealt_with.setOnClickListener(this);
    }
    int flag=0;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fanhui:
                startActivity(new Intent(InputBoxActivity.this,MainActivity.class));
                break;
            case R.id.queren:
                Toast.makeText(this,"清空！",Toast.LENGTH_SHORT).show();
                fragmentAdd.et_text.setText("");
                break;
            case R.id.need_to_be_dealt_with:    //跳转待办页
                if(flag==0){
                    this.getSupportFragmentManager()
                            .beginTransaction()
                            .show(fragmentWith)
                            .hide(fragmentAdd)
                            .commit();

                }else if(flag==1){
                    this.getSupportFragmentManager()
                            .beginTransaction()
                            .show(fragmentAdd)
                            .hide(fragmentWith)
                            .commit();
                }
                flag=(flag+1)%2;
                break;

        }

    }
}
