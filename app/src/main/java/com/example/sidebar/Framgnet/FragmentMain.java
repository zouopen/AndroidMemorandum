package com.example.sidebar.Framgnet;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sidebar.Adapter.MyAdapter;
import com.example.sidebar.DBHelper.DataWay;
import com.example.sidebar.Dao.DataDao;
import com.example.sidebar.InputBoxActivity;
import com.example.sidebar.R;
import com.example.sidebar.updateMainActivity;

import java.util.ArrayList;
import java.util.List;

public class FragmentMain extends Fragment {
    private    DataWay   dataWay  = new DataWay();
    private List<DataDao> daoList = new ArrayList<>();
    protected Button btn_search;
    protected ListView listView;
    protected EditText et_search;
    protected MyAdapter myAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgnet_main,container,false);
        //初始化视图
        initView(view);
        //初始化监听器
        InitListener();
        return view;
    }

    private void initView(View view) {
        listView= view.findViewById(R.id.list);
        et_search= view.findViewById(R.id.et_search);
        btn_search=view.findViewById(R.id.btn_search);
        daoList.addAll(dataWay.allQuery(getContext()));
        myAdapter=new MyAdapter(getContext(),daoList);
        listView.setAdapter(myAdapter);
    }
    private void InitListener(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DataDao dataDao = daoList.get(position);
                final String Id= dataDao.getId()+"";
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext()).setTitle("提示")
                        .setMessage("是否删除所选？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataWay.deleteData(getContext(),Id);
                                Refresh();
                            }
                        });
                builder.show();
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataDao dataDao = daoList.get(position);
                Intent intent=new Intent(getActivity(),updateMainActivity.class);
                intent.putExtra("id",dataDao.getId());
                startActivity(intent);
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String et_text= et_search.getText().toString();

                daoList.clear();
                daoList.addAll(dataWay.textQuery(getContext(),et_text));
                myAdapter=new MyAdapter(getContext(),daoList);
                myAdapter.notifyDataSetChanged();
                listView.setAdapter(myAdapter);
            }
        });
    }
    private void Refresh(){
        daoList.clear();
        daoList.addAll(dataWay.allQuery(getContext()));
        myAdapter=new MyAdapter(getContext(),daoList);
        myAdapter.notifyDataSetChanged();
        listView.setAdapter(myAdapter);
    }
}
