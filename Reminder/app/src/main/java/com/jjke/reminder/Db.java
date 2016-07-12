package com.jjke.reminder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 5/30/2016.
 */
public class Db extends SQLiteOpenHelper {
    public Db(Context context){
        super(context,"db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE fact("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"time TEXT DEFAULT \"\","+"even TEXT DEFAULT \"\")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
