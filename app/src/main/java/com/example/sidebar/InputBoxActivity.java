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
import com.example.sidebar.DBHelper.NoteDAOService;
import com.example.sidebar.DBHelper.NoteDAOServiceImpl;
import com.example.sidebar.Dao.DataDao;
import com.example.sidebar.Framgnet.FragmentAdd;
import com.example.sidebar.Framgnet.FragmentInputBox;
import com.example.sidebar.Framgnet.FragmentWith;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 黄铿 on 2018/12/4.
 */

public class InputBoxActivity extends AppCompatActivity{
    //@Bind(R.id.et_text) EditText et_text;
    //@Bind(R.id.fanhui)  ImageButton comeback;
   // @Bind(R.id.queren)  ImageButton clean;
    @Bind(R.id.need_to_be_dealt_with) LinearLayout need_to_be_dealt_with;
    protected FragmentAdd  fragmentAdd  = new FragmentAdd();
    protected FragmentWith fragmentWith = new FragmentWith();
    private   int flag=0;
    private String time;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputbox);
        ButterKnife.bind(this);
        FragmentView();

    }
    private void FragmentView(){
        this.getSupportFragmentManager().beginTransaction()
                .add(R.id.input_box,fragmentWith)       //待办页
                .add(R.id.input_box,fragmentAdd)       //普通编辑页
                .commit();
    }
    @OnClick(R.id.fanhui) void ComeBackListener(){
//        判断flag如果为0不是待办，为1就是待办
        if(flag==0){
            String text = fragmentAdd.et_text.getText().toString();
            if(text.equals("")){
                Toast.makeText(this,"备忘录数据不能为空",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,MainActivity.class));
            }else {
                NoteDAOService<DataDao,Integer,String> service = new NoteDAOServiceImpl<>(this, DataDao.class);
                DataDao dataDao = new DataDao();
                InitData();
                dataDao.setText(fragmentAdd.et_text.getText().toString());
                dataDao.setTime(time);
                dataDao.setWith(0);
                try { service.save(dataDao); } catch (SQLException e) { e.printStackTrace(); }
                startActivity(new Intent(this,MainActivity.class));
            }
        }else if (flag==1){
            String text = fragmentWith.et_text1.getText().toString();
            if(text.equals("")){
                Toast.makeText(this,"备忘录数据不能为空",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,MainActivity.class));
            }else {
                NoteDAOService<DataDao,Integer,String> service = new NoteDAOServiceImpl<>(this, DataDao.class);
                DataDao dataDao = new DataDao();
                InitData();
                dataDao.setText(fragmentWith.et_text1.getText().toString());
                dataDao.setTime(time);
                dataDao.setWith(1);
                try { service.save(dataDao); } catch (SQLException e) { e.printStackTrace(); }
                startActivity(new Intent(this,MainActivity.class));
            }
        }
    }
    @OnClick(R.id.queren) void ClearAll(){
        Toast.makeText(this,"清空！",Toast.LENGTH_SHORT).show();
        if (flag==0){
            fragmentAdd.et_text.setText("");
        }else if(flag==1){
            fragmentWith.et_text1.setText("");
        }
    }
    @OnClick(R.id.need_to_be_dealt_with) void HideShowFragment(){
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
    }
    private void InitData() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd HH:mm");
        time = sdf.format(dt);
    }
}
