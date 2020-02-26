package com.mumu.exceptiondemo.exceptionmonitor.helper;

import android.content.Context;
import android.view.View;

import com.mumu.exceptiondemo.exceptionmonitor.queue.SequenceQueue;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 用户操作步骤
 */
public class StepsHelper {
    private static final SimpleDateFormat TIME_FORMATTER =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);


    private static SequenceQueue<String> stepQueue = new SequenceQueue<>(); //储存用户操作步骤的队列
    private static StringBuilder stepBuilder = new StringBuilder();

    public static void enqueueStep(Context context, String state) {
        String time = TIME_FORMATTER.format(System.currentTimeMillis());
        stepBuilder.append(time)
                .append(" ")
                .append(context.getClass().getName())
                .append(" ")
                .append(state);
        String s = stepBuilder.toString();
        stepQueue.add(s);
        stepBuilder.delete(0, stepBuilder.length());
    }

    public static void enqueueStep(Context context, View view) {
        if (view == null) { return; }
        String time = TIME_FORMATTER.format(System.currentTimeMillis());
        String path = view.getResources().getResourceName(view.getId());
        String viewId = path.substring(path.indexOf("/") + 1);
        stepBuilder.append(time)
                .append(" ")
                .append(context.getClass().getName())
                .append(" Event：viewId：")
                .append(viewId)
                .append("   Type: ")
                .append(view.getClass().getName());
        String s = stepBuilder.toString();
        stepQueue.add(s);
        stepBuilder.delete(0, stepBuilder.length());
    }

    public static String flushString() {
        String s = stepQueue.flushString();
        stepQueue.clear();
        return s;
    }
}
