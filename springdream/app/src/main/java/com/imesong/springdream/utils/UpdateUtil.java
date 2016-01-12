package com.imesong.springdream.utils;

import com.umeng.update.UmengUpdateAgent;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * 自动升级工具类
 */
public class UpdateUtil {

    public static void update(@NonNull Context context) {

        UmengUpdateAgent.update(context);
    }
}
