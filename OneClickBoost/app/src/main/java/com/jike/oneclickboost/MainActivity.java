package com.jike.oneclickboost;

import android.app.ActivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int level = ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long old_memory = SystemUtil.getAvailableMemory(this);
        int process = SystemUtil.clearMemory(this, level);
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long new_memory = SystemUtil.getAvailableMemory(this);
        String result = String.format(getString(R.string.toastText), process, old_memory, new_memory, old_memory - new_memory, level);
        Toast.makeText(this,result,Toast.LENGTH_LONG);
        finish();
    }
}
