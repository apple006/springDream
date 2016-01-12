package com.imesong.springdream.utils;

import com.imesong.springdream.database.AssetsDatabaseManager;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

import cn.zhaiyifan.init.Flow;
import cn.zhaiyifan.init.Task;

/**
 * 初始化任务调度工具类
 */
public class FlashFlowUtil {

    private static String TAG = "FlashFlowUtil";
    private static HashMap<String, Flow> flowHashMap = new HashMap<>();


    public static Flow getFlashFlow(String flowTag) {
        Flow flow = null;
        if (flowTag != null) {
            flow = flowHashMap.get(flowTag);
        }
        if (flow == null) {
            flow = new Flow(flowTag);
            flowHashMap.put(flowTag, flow);
        }
        return flow;
    }

    public static void startFlow(final Context context, String flowTag) {

        Task initLocalData = new Task("initLocalData", true) {
            @Override
            protected void start() {

                try {
                    AssetsDatabaseManager.getInstance(context).initLoacalDatabase(AssetsDatabaseManager
                            .DB_NAME);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                    EventAgentUtil.reportError(context, e);
                } catch (IOException e) {
                    e.printStackTrace();
                    EventAgentUtil.reportError(context, e);
                }
            }

            @Override
            public boolean runOnProcess(String processName) {
                Log.d(TAG, "runOnProcess:" + processName);
                return super.runOnProcess(processName);
            }

            @Override
            protected void onResult() {
                super.onResult();
            }
        };
        FlashFlowUtil.getFlashFlow(flowTag).addTask(1, initLocalData).start();
    }
}
