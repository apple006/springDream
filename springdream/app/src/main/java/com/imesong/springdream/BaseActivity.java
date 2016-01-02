package com.imesong.springdream;

import android.support.v7.app.AppCompatActivity;

import com.imesong.springdream.utils.EventAgentUtil;

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
