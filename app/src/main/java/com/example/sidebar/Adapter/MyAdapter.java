package com.example.sidebar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.sidebar.Dao.DataDao;
import com.example.sidebar.R;

import java.util.List;

/**
 * Created by 黄铿 on 2018/12/6.
 */

public class MyAdapter extends BaseAdapter{
    private List<DataDao> datalist;
    private Context context;
    private LayoutInflater inflater;

    public MyAdapter(Context context, List<DataDao> datalist){
        this.context=context;
        this.datalist=datalist;
        this.inflater=LayoutInflater.from(context.getApplicationContext());
    }
    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.mText=(TextView)convertView.findViewById(R.id.mtext);
            viewHolder.mTime=(TextView)convertView.findViewById(R.id.mtime);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.mText.setText(datalist.get(position).getText());
        viewHolder.mTime.setText(datalist.get(position).getTime());
        return convertView;
    }
    class ViewHolder{
        TextView mText;
        TextView mTime;
    }
}
