package com.oreooo.baselibrary.util;

import android.app.ActivityManager;
import android.content.Context;

import java.util.ArrayList;

public class ServiceUtils {
    /**
     * 判断服务是否开启
     */
    public static boolean isServiceRunning(Context context, String serviceName) {
        if (("").equals(serviceName)|| serviceName== null) {
            return false;
        }

        ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService =
                (ArrayList<ActivityManager.RunningServiceInfo>)manager.getRunningServices(30);
        for (int i = 0; i<runningService.size();i++) {
            if (runningService.get(i).service.getClassName().equals(serviceName)) {
                return true;
            }
        }
        return false;
    }
}
