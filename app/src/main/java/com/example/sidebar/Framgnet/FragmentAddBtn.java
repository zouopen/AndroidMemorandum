package com.example.sidebar.Framgnet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.sidebar.InputBoxActivity;
import com.example.sidebar.R;

/**
 * Created by 黄铿 on 2018/12/15.
 */
//新建按钮Fragment
public class FragmentAddBtn extends Fragment{
    protected LinearLayout add_btn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_btn,container,false);
        initView(view);
        initListrner();
        return view;
    }
    private void  initView(View view){
        add_btn=view.findViewById(R.id.add_btn);
    }
    private void initListrner(){
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),InputBoxActivity.class));
            }
        });
    }
}
