package com.jjke.baidudelivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 6/13/2016.
 */
public class Adapter extends BaseAdapter {
    private List<ShopList> shopLists;
    private LayoutInflater mInflater;

    public Adapter(Context context,List<ShopList> shopLists){
        mInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.shopLists = shopLists;
    }
    @Override
    public int getCount() {
        return (shopLists == null)?0:shopLists.size();
    }

    @Override
    public Object getItem(int position) {
        return shopLists.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ShopList shopList =(ShopList)getItem(position);

        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_cell_home,null);
            holder.ivShop = (ImageView) convertView.findViewById(R.id.ivShop);
            holder.ivBaiduPaisong =(ImageView) convertView.findViewById(R.id.ivBaiduPeisong);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvQuan = (TextView) convertView.findViewById(R.id.tvQuan);
            holder.tvPiao = (TextView) convertView.findViewById(R.id.tvPiao);
            holder.tvFu = (TextView) convertView.findViewById(R.id.tvFu);
            holder.tvPei = (TextView) convertView.findViewById(R.id.tvPei);

            holder.ratingBar =(RatingBar) convertView.findViewById(R.id.ratingBar);

            holder.tvYishou = (TextView)convertView.findViewById(R.id.tvYishou);
            holder.tvDistance = (TextView)convertView.findViewById(R.id.tvDistance);
            holder.tvQisong = (TextView)convertView.findViewById(R.id.tvQisong);
            holder.tvPeisong = (TextView)convertView.findViewById(R.id.tvPeisong);
            holder.tvTime = (TextView)convertView.findViewById(R.id.tvTime);

            holder.tvJian_1 = (TextView)convertView.findViewById(R.id.tvJian_1);
            holder.tvJian_2 = (TextView)convertView.findViewById(R.id.tvJian_2);

            holder.list_layoutHome_3 = convertView.findViewById(R.id.list_layoutHome_3);
            holder.list_itmeHome_1 = convertView.findViewById(R.id.list_itemHome1);
            holder.list_itemHome_2 = convertView.findViewById(R.id.list_itemHome2);
            convertView.setTag(holder);

        }else {
            holder =(ViewHolder)convertView.getTag();
        }
        holder.ivShop.setImageResource(shopList.id_ivShop);
        holder.tvName.setText(shopList.name);
        holder.tvQuan.setText(shopList.quan);
        holder.tvPiao.setText(shopList.piao);
        holder.tvFu.setText(shopList.fu);
        holder.tvPei.setText(shopList.pei);

        holder.ratingBar.setRating((Float)shopList.ratingBarScore);
        holder.tvYishou.setText("己售"+shopList.yishou+"份");
        holder.tvDistance.setText(shopList.distance);
        holder.tvQisong.setText("起送￥"+shopList.qisong);
        holder.tvPeisong.setText("配送￥"+shopList.peisong);
        holder.tvTime.setText("平均"+shopList.time+"分鈡");

        holder.tvJian_1.setText(shopList.jian_1);
        holder.tvJian_2.setText(shopList.jian_2);

        if (holder.tvQuan.getText()=="")holder.tvQuan.setVisibility(View.GONE);
        if (holder.tvPiao.getText()=="")holder.tvPiao.setVisibility(View.GONE);
        if (holder.tvFu.getText()=="")holder.tvFu.setVisibility(View.GONE);
        if (holder.tvPei.getText()=="")holder.tvPei.setVisibility(View.GONE);

        if (holder.tvJian_1.getText()==""&&holder.tvJian_1.getText()==""){
            holder.list_layoutHome_3.setVisibility(View.GONE);
        }else  if(holder.tvJian_1.getText()==""){
            holder.list_itmeHome_1.setVisibility(View.GONE);
        }else if (holder.tvJian_2.getText()==""){
            holder.list_itemHome_2.setVisibility(View.GONE);
        }
        if (shopList.ivBaiduPeisong_show==0){
            holder.ivBaiduPaisong.setVisibility(View.GONE);
        }
        return convertView;
    }
    class ViewHolder{
        ImageView ivShop;
        ImageView ivBaiduPaisong;
        TextView tvName;
        TextView tvQuan,tvPiao,tvFu,tvPei;
        RatingBar ratingBar;
        TextView tvJian_1,tvJian_2;
        TextView tvQisong, tvPeisong, tvTime;
        TextView tvYishou,tvDistance;
        View list_layoutHome_3;
        View list_itmeHome_1,list_itemHome_2;
    }
}
