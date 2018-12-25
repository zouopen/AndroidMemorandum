package com.example.sidebar.Framgnet;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sidebar.DBHelper.NoteDAOServiceImpl;
import com.example.sidebar.Dao.DataDao;
import com.example.sidebar.R;
import com.example.sidebar.updateMainActivity;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 黄铿 on 2018/12/24.
 */

public class FragmentUpWith extends Fragment{
    @Bind(R.id.tv_time1) TextView tv_time;
    @Bind(R.id.et_text1) public EditText et_text1;
    private Integer id;
    public String text;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_with,container,false);
        ButterKnife.bind(this,view);
        try {
            InitData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return view;
    }
    private void InitData() throws SQLException {
        NoteDAOServiceImpl <DataDao,Integer,String> noteDAOService = new NoteDAOServiceImpl<>(getContext(),DataDao.class);
        Date dt = new Date();
        @SuppressWarnings("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd HH:mm");
        String time = sdf.format(dt);
        tv_time.setText(time);
        DataDao dataDao = noteDAOService.queryById(id);
        text = dataDao.getText();
        et_text1.setText(dataDao.getText());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        id = ((updateMainActivity)context).setId();
    }
}
