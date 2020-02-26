package com.mumu.exceptiondemo.exceptionmonitor;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;

import com.mumu.exceptiondemo.exceptionmonitor.helper.StepsHelper;

public class ExceptionMonitor {

    private static final String CREATE = "onCreate";
    private static final String RESUME = "onResume";
    private static final String PAUSE = "onPause";
    private static final String DESTROY = "onDestroy";

    private ExceptionMonitor() { }

    private static class Holder {
        private static final ExceptionMonitor instance = new ExceptionMonitor();
    }

    public static ExceptionMonitor getInstance() {
        return Holder.instance;
    }

    public void init() {
        CrashHandler crashHandler = new CrashHandler();
    }

    public void onDispatchTouchEvent(Activity activity, MotionEvent event) {
        MonitorEvent.getInstance().onDispatchTouchEvent(activity,event);
    }

    public void onCreate(Context context) {
        StepsHelper.enqueueStep(context, CREATE);
    }

    public void onResume(Context context) {
        StepsHelper.enqueueStep(context, RESUME);
    }

    public void onPause(Context context) {
        StepsHelper.enqueueStep(context, PAUSE);
    }

    public void onDestroy(Context context) {
        StepsHelper.enqueueStep(context, DESTROY);
    }
}
