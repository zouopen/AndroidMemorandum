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
import com.example.sidebar.Framgnet.FragmentInputBox;
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
    protected FragmentInputBox fragmentInputBox = new FragmentInputBox();
    protected FragmentWith fragmentWith = new FragmentWith();
    private SimpleDateFormat sdf;
    private Date dt;
    private Integer id;

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
        id = intent.getIntExtra("id",0);
    }
    @OnClick(R.id.fanhui) void ComeBack(){
        NoteDAOServiceImpl<DataDao, Integer, String> noteDAOService = new NoteDAOServiceImpl<>(this, DataDao.class);
        String et_text = fragmentInputBox.et_text.getText().toString();
        try {
            DataDao dataDao = noteDAOService.queryById(id);
            dataDao.setText(et_text);
            dataDao.setTime(sdf.format(dt));
            noteDAOService.update(dataDao);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(updateMainActivity.this, MainActivity.class));
    }
    @OnClick(R.id.queren) void Clear(){
        fragmentInputBox.et_text.setText(" ");
        Toast.makeText(this,"清空！",Toast.LENGTH_SHORT).show();
    }
    //获取到Main传来的id再传入fragmentInputBox
    public Integer setId(){
        return id;
    }
}
