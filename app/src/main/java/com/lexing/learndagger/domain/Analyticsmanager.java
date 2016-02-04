package com.lexing.learndagger.domain;

import android.app.Application;
import android.util.Log;

/**
 * Author: mopel(amosbake@outlook.com)
 * Date : 2016/2/4
 * TIME : 10:50
 */
public class Analyticsmanager {
    private static final String TAG = "Analyticsmanager";
    private Application app;

    public Analyticsmanager(Application app) {
        this.app = app;
    }

    public void registerAppEnter() {
        //应用启动时做的第三方插件初始化
        Log.i(TAG, "registerAppEnter: "+"complete");
    }
}
