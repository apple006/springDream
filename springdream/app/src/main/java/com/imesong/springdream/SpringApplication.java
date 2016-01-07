package com.imesong.springdream;

import android.app.Application;

import com.imesong.springdream.utils.FlashFlowUtil;
import com.squareup.leakcanary.LeakCanary;

/**
 * MainApplication for SpringDream
 * block task should not do here
 */
public class SpringApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initThirdLib();
        FlashFlowUtil.startFlow(this,"initDataFlow");
    }
    private void initThirdLib(){
        LeakCanary.install(this);
    }
}
