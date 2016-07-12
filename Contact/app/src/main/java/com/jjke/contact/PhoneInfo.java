package com.jjke.contact;

/**
 * Created by Administrator on 5/27/2016.
 */
public class PhoneInfo {
    private String name;
    private String number;

    public PhoneInfo(String name,String number){
        setName(name);
        setNumber(number); }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
