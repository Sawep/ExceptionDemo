package com.mumu.exceptiondemo;

import android.app.Application;

import com.mumu.exceptiondemo.exceptionmonitor.ExceptionMonitor;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ExceptionMonitor.getInstance().init();
    }
}
