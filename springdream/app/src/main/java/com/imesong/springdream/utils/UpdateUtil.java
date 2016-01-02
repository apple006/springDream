package com.imesong.springdream.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.umeng.update.UmengUpdateAgent;

/**
 * 自动升级工具类
 */
public class UpdateUtil {

    public static void update(@NonNull Context context){

        UmengUpdateAgent.update(context);
    }

}
