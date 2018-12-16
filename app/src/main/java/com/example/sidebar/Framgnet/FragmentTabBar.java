package com.example.sidebar.Framgnet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.sidebar.R;

/**
 * Created by 黄铿 on 2018/12/16.
 */
//标签栏Fragment
public class FragmentTabBar extends Fragment {
    public LinearLayout need_to_be_dealt_with;
    protected LinearLayout remind;
    protected LinearLayout picture;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab,container,false);
        return view;
    }
    private void initView(View view){
        need_to_be_dealt_with = view.findViewById(R.id.need_to_be_dealt_with);
        remind = view.findViewById(R.id.remind);
        picture = view.findViewById(R.id.picture);

    }



    private void initListrner(){

    }
}
