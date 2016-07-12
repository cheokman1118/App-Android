package com.jjke.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 5/27/2016.
 */
public class MyAdapter extends BaseAdapter {
    private List<PhoneInfo> lists;
    private Context context;
    private LinearLayout layout;

    public MyAdapter( List<PhoneInfo> lists,Context context){
        this.lists = lists;
        this.context = context;
    }
    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /**  LayoutInflater inflater = LayoutInflater.from(context);
         layout = (LinearLayout) inflater.inflate(R.layout.cell,null);
         TextView nametv = (TextView) layout.findViewById(R.id.textname);
         TextView numtv = (TextView) layout.findViewById(R.id.textnum);
         nametv.setText(lists.get(position).getName());
         numtv.setText(lists.get(position).getNumber()); */
        ViewHolder holder;
        if (convertView ==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
            holder = new ViewHolder();
            holder.nametv = (TextView) convertView.findViewById(R.id.tv_name);
            holder.numbertv =(TextView)  convertView.findViewById(R.id.tv_phone);
            holder.nametv.setText(lists.get(position).getName());
            holder.numbertv.setText(lists.get(position).getNumber());
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    public static class ViewHolder{
        TextView nametv;
        TextView numbertv;
    }
}
