package com.mumu.exceptiondemo.exceptionmonitor;

import com.mumu.exceptiondemo.exceptionmonitor.helper.StepsHelper;
import com.mumu.exceptiondemo.utils.DeviceUtil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mUncaughtHandler;

    CrashHandler() {
        mUncaughtHandler = Thread.getDefaultUncaughtExceptionHandler(); //不覆盖系统的KillApplicationHandler
        Thread.setDefaultUncaughtExceptionHandler(this); //使用自定义的异常处理器
    }

    /**
     * 当程序中有未捕获的异常，系统会自动调用这个方法
     * @param t 出现未捕获异常的线程
     * @param e 未捕获的异常，可以从中获取异常信息
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //处理异常信息 上传异常信息

        System.out.printf("\n手机品牌：%s", DeviceUtil.getDeviceBrand());
        System.out.printf("\n手机型号：%s", DeviceUtil.getSystemModel());
        System.out.printf("\n系统型号：%s\n\n", DeviceUtil.getSystemVersion());

        Writer eInfo = new StringWriter();
        PrintWriter printWriter = new PrintWriter(eInfo);
        e.printStackTrace(printWriter);
        System.out.printf("%s\n", eInfo.toString());

        System.out.println("步骤：");
        System.out.println(StepsHelper.flushString());

        // 看需要是否要杀死进程
//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(0);

        //若要执行系统的异常处理器，执行以下语句
        mUncaughtHandler.uncaughtException(t, e);
    }
}
