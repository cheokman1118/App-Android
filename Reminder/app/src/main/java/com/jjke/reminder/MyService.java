package com.jjke.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyService extends Service {
    private Db db;
    private SQLiteDatabase dbRead;
    private int id;
    private int hour;
    private String even;
    private long timeToAlarm;


    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("service start!");



        //System.out.println("service start"+System.currentTimeMillis());
        db = new Db(this);
        dbRead = db.getReadableDatabase();
        Cursor c = dbRead.query("fact",null,null,null,null,null,null);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent it = new Intent(this,AlarmReceiver.class);


        while (c.moveToNext()){
            Calendar calendar = Calendar.getInstance();
            int calHour = calendar.get(Calendar.HOUR_OF_DAY);
            int calMin = calendar.get(Calendar.MINUTE);
            int calSec = calendar.get(Calendar.SECOND);
            id = c.getInt(c.getColumnIndex("_id"));
            even = c.getString(c.getColumnIndex("even"));
            hour = Integer.parseInt(c.getString(c.getColumnIndex("time")));
            long millMin = calMin*60000L;
            long milli = calSec*1000L;

            if (hour>calHour){
                timeToAlarm = (hour-calHour)*3600000L;
            }else{
                timeToAlarm = (24-calHour+hour)*3600000L;
            }

            long timeinmill = System.currentTimeMillis()- millMin- milli +timeToAlarm;


            it.putExtra("data",even);
            PendingIntent pi = PendingIntent.getBroadcast(this,id,it,PendingIntent.FLAG_UPDATE_CURRENT);
            am.set(AlarmManager.RTC,timeinmill,pi);
        }


        return super.onStartCommand(intent, flags, startId);
    }
}
