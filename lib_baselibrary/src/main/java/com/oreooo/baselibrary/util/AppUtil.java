package com.oreooo.baselibrary.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class AppUtil {

    private static PackageManager pm = null;

    public interface NextListener {
        void onComplete();
    }

    /**
     * 未授予的权限集合
     */
    private static List<String> requestPermissions;
    /**
     * 请求权限时的请求码
     */
    private static final int REQUEST_CODE = 1;
    private static NextListener listener;
    /**
     * 申请权限相关
     */
    private static String[] permissions;

    /**
     * 检查权限
     *
     * @return 是否已全部授权
     */
    private static boolean checkPermission(Context context) {
        requestPermissions = new ArrayList<>();
        // 权限是否授予标志位
        boolean isPermissionGranted = true;
        // 检查权限是否授予
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                requestPermissions.add(permission);
                isPermissionGranted = false;
            }
        }
        return isPermissionGranted;
    }

//    public static void startPermission(Context context, NextListener listener) {
//        AppUtil.listener = listener;
//        permissions = AppUtil.AppPremission();
//
//        if (Build.VERSION.SDK_INT >= 23) {//6.0才用动态权限
//            // 检测权限已全部授予
//            if (checkPermission(context)) {
//                // 下一步
//                if (listener != null) {
//                    listener.onComplete();
//                }
//            } else {
//                // 请求权限
//                ActivityCompat.requestPermissions((Activity) context, requestPermissions.toArray(new String[requestPermissions.size()]), REQUEST_CODE);
//            }
//        } else {
//            if (listener != null) {
//                listener.onComplete();
//            }
//        }
//    }

//    public static void resultPermission(Context context, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case REQUEST_CODE:
//                boolean isAllGranted = true;
//                // 遍历授权结果，一旦检测到未授权，则将isAllGranted置为false,并跳出循环
//                for (int grantResult : grantResults) {
//                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
//                        isAllGranted = false;
//                        break;
//                    }
//                }
//                if (isAllGranted)
//                    // 全部授权通过则进入下一步
//                    if (listener != null) {
//                        listener.onComplete();
//                    } else
//                        // 否则引导用户手动授权
//                        openAppDetails(context);
//                break;
//            default:
//                break;
//        }
//    }
//
//    /**
//     * 获取程序的权限
//     */
//    public static String[] AppPremission() {
//        try {
//            PackageInfo packinfo = getPackageManager().getPackageInfo(getPackageName(),
//                    PackageManager.GET_PERMISSIONS);
//            // 获取到所有的权限
//            return packinfo.requestedPermissions;
//
//        } catch (PackageManager.NameNotFoundException e) {
//            Logger.d(e);
//
//        }
//        return null;
//    }
//
//    static PackageManager getPackageManager() {
//        if (pm == null) {
//            pm = getContext().getPackageManager();
//        }
//        return pm;
//    }
}
