package com.jike.oneclickboost;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by Administrator on 7/1/2016.
 */
public class SystemUtil {
    public static long getAvailableMemory(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem/(1024*1024);
    }

    public static int clearMemory(Context context, int level) {
        int counter = 0;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list = activityManager.getRunningAppProcesses();
        if (list != null){
            for (int i = 0;i<list.size();i++){
                ActivityManager.RunningAppProcessInfo apinfo = list.get(i);
                String[] pkgList = apinfo.pkgList;
                if (apinfo.importance > level) {
                    for (int j = 0; j < pkgList.length; j++) {
                        activityManager.killBackgroundProcesses(pkgList[j]);
                        counter++;
                    }
                }
            }
        }
        return counter;
    }
}
