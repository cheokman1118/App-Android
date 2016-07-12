package com.jjke.baidudelivery;

/**
 * Created by Administrator on 6/13/2016.
 */
public class ShopList{
    public int id_ivShop;
    public int ivBaiduPeisong_show;
    public String name, quan,piao,fu,pei;
    public float ratingBarScore;
    public String yishou,distance , qisong,peisong,time,jian_1,jian_2;

    public ShopList(int id_ivShop,int ivBaiduPeisong_show,String name,String quan,String piao,String fu,String pei,double ratingBarScore ,String yishou,String distance,
    String qisong,String peisong,String time,String jian_1,String jian_2){
        super();
        this.id_ivShop = id_ivShop;
        this.ivBaiduPeisong_show = ivBaiduPeisong_show;
        this.name = name;
        this.quan = quan;
        this.piao = piao;
        this.fu = fu;
        this.pei = pei;
        this.ratingBarScore =(float) ratingBarScore;
        this.yishou = yishou;
        this.distance = distance;
        this.qisong = qisong;
        this.peisong = peisong;
        this.time = time;
        this.jian_1 = jian_1;
        this.jian_2 = jian_2;
    }
}
