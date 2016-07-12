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
 * Created by Administrator on 5/26/2016.
 */
public class ContactAdapter extends BaseAdapter {
    private Context context;
    private List<GetBean> contacts;
    private LinearLayout layout;

    public ContactAdapter(Context context, List<GetBean> contacts
    ){
        this.context = context;
        this.contacts = contacts;
    }
    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
            //convertView = View.inflate(context,R.layout.item,null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
            holder.tv_name.setText(contacts.get(position).getName());
            holder.tv_phone.setText(contacts.get(position).getPhone());
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
       return convertView;
    }
    static class ViewHolder{
        TextView tv_name;
        TextView tv_phone;
    }
}
