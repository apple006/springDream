package com.imesong.springdream;

import com.imesong.springdream.utils.EventAgentUtil;

import android.support.v7.app.AppCompatActivity;

/**
 *
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        EventAgentUtil.onResume(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        EventAgentUtil.onPause(this);
    }
}
