package com.jjke.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootMonitor extends BroadcastReceiver {
    public BootMonitor() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("這是流氓軟件");
        Intent startServiceIntent = new Intent(context, MyService.class);
        context.startService(startServiceIntent);
    }
}
