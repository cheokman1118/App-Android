package com.jjke.contact;

/**
 * Created by Administrator on 5/26/2016.
 */
public class GetBean {
    private String name;
    private String phone;
    private long rawContactID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getRawContactID() {
        return rawContactID;
    }

    public void setRawContactID(long rawContactID) {
        this.rawContactID = rawContactID;
    }

    @Override
    public String toString() {
        return "GetBean [name="+ name + ",phone="+phone +",rawContactId="+rawContactID+"]";
    }
}
