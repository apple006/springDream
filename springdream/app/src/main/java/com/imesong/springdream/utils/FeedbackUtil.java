package com.imesong.springdream.utils;

import com.umeng.fb.FeedbackAgent;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * 用户反馈工具类，基于友盟反馈
 */
public class FeedbackUtil {


    public static void openFeedbackPage(@NonNull Context context) {

        FeedbackAgent agent = new FeedbackAgent(context);
        agent.startFeedbackActivity();
    }
}
