package com.imesong.springdream.utils;

import com.umeng.analytics.MobclickAgent;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * 统计工具相关
 */
public class EventAgentUtil {


    public static void onResume(@NonNull Context context) {
        MobclickAgent.onResume(context);
    }

    public static void onPause(@NonNull Context context) {
        MobclickAgent.onPause(context);
    }


    public static void reportError(Context context, String errorInfo) {
        MobclickAgent.reportError(context, errorInfo);
    }

    public static void reportError(Context context, Throwable throwable) {
        MobclickAgent.reportError(context, throwable);
    }
}
