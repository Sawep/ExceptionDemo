package com.mumu.exceptiondemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.mumu.exceptiondemo.exceptionmonitor.ExceptionMonitor;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExceptionMonitor.getInstance().onCreate(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ExceptionMonitor.getInstance().onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ExceptionMonitor.getInstance().onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExceptionMonitor.getInstance().onDestroy(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        ExceptionMonitor.getInstance().onDispatchTouchEvent(this, ev);
        return super.dispatchTouchEvent(ev);
    }
}
