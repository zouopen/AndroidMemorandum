package com.example.sidebar.Framgnet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sidebar.DBHelper.DataWay;
import com.example.sidebar.DBHelper.NoteDAOService;
import com.example.sidebar.DBHelper.NoteDAOServiceImpl;
import com.example.sidebar.Dao.DataDao;
import com.example.sidebar.InputBoxActivity;
import com.example.sidebar.MainActivity;
import com.example.sidebar.R;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/** @author Evilsay
 * 新增页面
 */
public class FragmentAdd extends Fragment{
    @Bind(R.id.tv_time) TextView tv_time;
    @Bind(R.id.et_text) public EditText et_text;
    private ImageButton comeback;
    private ImageButton clean;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_box,container,false);
        ButterKnife.bind(this,view);
        init();
        initListener();
        InitData();

        return view;
    }
    private void  init(){
        comeback = getActivity().findViewById(R.id.fanhui);
        clean    = getActivity().findViewById(R.id.queren);
    }

    private void initListener(){
        comeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et_text.getText().toString();
                if(text.equals("")){
                    Toast.makeText(getContext(),"备忘录数据不能为空",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(),MainActivity.class));
                }else {
                    NoteDAOService<DataDao,Integer,String> service = new NoteDAOServiceImpl<>(getContext(), DataDao.class);
                    DataDao dataDao = new DataDao();
                    dataDao.setText(et_text.getText().toString());
                    dataDao.setTime(tv_time.getText().toString());
                    try { service.save(dataDao); } catch (SQLException e) { e.printStackTrace(); }
                    startActivity(new Intent(getContext(),MainActivity.class));
                }

            }
        });
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"清空！",Toast.LENGTH_SHORT).show();
                et_text.setText("");
            }
        });
    }
    private void InitData() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd HH:mm");
        tv_time.setText(sdf.format(dt));
    }
}
