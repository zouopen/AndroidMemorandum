package com.example.sidebar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.sidebar.DBHelper.NoteDAOServiceImpl;
import com.example.sidebar.Dao.DataDao;
import com.example.sidebar.Framgnet.FragmentUpWith;
import com.example.sidebar.Framgnet.FragmentUpdate;
import com.example.sidebar.Framgnet.FragmentWith;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class updateMainActivity extends AppCompatActivity {
    @Bind(R.id.fanhui)  ImageButton comeback;
    @Bind(R.id.queren)  ImageButton clean;
    protected FragmentUpdate fragmentUpdate = new FragmentUpdate();
    protected FragmentUpWith fragmentUpWith = new FragmentUpWith();
    private SimpleDateFormat sdf;
    private Date dt;
    private Integer id;
    private Integer with;
    private int flag;
    private String text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputbox);
        ButterKnife.bind(this);
        init();
        FragmentView();
    }
    private void FragmentView(){
        if (with==0){
            this.getSupportFragmentManager().beginTransaction()
                    .add(R.id.input_box,fragmentUpWith)
                    .add(R.id.input_box,fragmentUpdate)
                    .commit();
        }else if(with==1){
            this.getSupportFragmentManager().beginTransaction()
                    .add(R.id.input_box,fragmentUpdate)
                    .add(R.id.input_box,fragmentUpWith)
                    .commit();
        }

    }
    @SuppressLint("SimpleDateFormat")
    private  void init(){
        dt = new Date();
        sdf = new SimpleDateFormat("yyyy年MM月dd HH:mm");
        Intent intent=getIntent();
        id = intent.getIntExtra("id",0);
        with = intent.getIntExtra("with",2);
        flag = with;
    }
    @OnClick(R.id.fanhui) void ComeBack(){
        NoteDAOServiceImpl<DataDao, Integer, String> noteDAOService = new NoteDAOServiceImpl<>(this, DataDao.class);
        if (flag==0){
            String et_text = fragmentUpdate.et_text.getText().toString();
            if (!et_text.equals(fragmentUpdate.text)||with==flag){
                try {
                    DataDao dataDao = noteDAOService.queryById(id);
                    dataDao.setText(et_text);
                    dataDao.setTime(sdf.format(dt));
                    dataDao.setWith(0);
                    noteDAOService.update(dataDao);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            }
        }else if(flag==1){
            String et_text = fragmentUpWith.et_text1.getText().toString();
            if (!et_text.equals(fragmentUpWith.text)||with==flag){
                try {
                    DataDao dataDao = noteDAOService.queryById(id);
                    dataDao.setText(et_text);
                    dataDao.setTime(sdf.format(dt));
                    dataDao.setWith(1);
                    noteDAOService.update(dataDao);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            }
        }
        startActivity(new Intent(updateMainActivity.this, MainActivity.class));
    }
    @OnClick(R.id.queren) void Clear(){
        fragmentUpdate.et_text.setText(" ");
        Toast.makeText(this,"清空！",Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.to_with) void HideShowFragment(){
        if(flag==0){
            String text = fragmentUpdate.et_text.getText().toString();
            fragmentUpWith.et_text1.setText(text);
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .show(fragmentUpWith)
                    .hide(fragmentUpdate)
                    .commit();

        }else if(flag==1){
            text = fragmentUpWith.et_text1.getText().toString();
            fragmentUpdate.et_text.setText(text);
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .show(fragmentUpdate)
                    .hide(fragmentUpWith)
                    .commit();
        }
        flag=(flag+1)%2;
    }
    //获取到Main传来的id再传入fragmentInputBox
    public Integer setId(){
        return id;
    }
}
