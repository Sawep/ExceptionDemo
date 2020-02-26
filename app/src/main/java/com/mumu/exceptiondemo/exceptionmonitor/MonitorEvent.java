package com.mumu.exceptiondemo.exceptionmonitor;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.mumu.exceptiondemo.exceptionmonitor.helper.StepsHelper;

public class MonitorEvent {
    private float lastX;
    private float lastY;

    private MonitorEvent() {}

    private static class Holder{
        private static final MonitorEvent instance = new MonitorEvent();
    }

    static MonitorEvent getInstance() {
        return Holder.instance;
    }

    public void onDispatchTouchEvent(Activity activity, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            this.lastX = event.getRawX();
            this.lastY = event.getRawY();
        } else if(event.getAction() == MotionEvent.ACTION_UP) {
            float currentX = event.getRawX();
            float currentY = event.getRawY();
            if (lastX == currentX && lastY == currentY) {
                View view = this.getView(activity.getWindow().getDecorView(), currentX, currentY);
                StepsHelper.enqueueStep(activity, view);
            }
        }
    }

    private View getView(View decorView, float currentX, float currentY) {
        View targetView = null;
        int[] pos = new int[2];
        decorView.getLocationInWindow(pos);
        if (determinePos(currentX, currentY, pos[0], pos[1], decorView.getWidth(), decorView.getHeight())) {
            if (decorView instanceof ViewGroup) {
                for (int i = 0; i < ((ViewGroup)decorView).getChildCount(); ++i) {
                    View tempView = ((ViewGroup) decorView).getChildAt(i);
                    targetView = getView(tempView, currentX, currentY);
                    if (targetView != null) {
                        break;
                    }
                }
            } else {
                targetView = decorView;

            }
        }
        return targetView;
    }

    private boolean determinePos(float var1, float var2, int var3, int var4, int var5, int var6) {
        return var1 >= (float)var3 && var1 <= (float)(var3 + var5) && var2 >= (float)var4 && var2 <= (float)(var4 + var6);
    }
}
