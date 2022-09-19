package com.ftm.factory.util;

import android.os.SystemProperties;
public class FtmLog {
    private static final int DEBUG = SystemProperties.getInt("customization.sys.ftm.log", 1);
    private static final String TAG = "FtmApk";

    public static void d(String tag , String msg) {
        if(1 == DEBUG){
            android.util.Log.d(TAG, ""+tag+": "+msg+" ");
        }
    }

    public static void d(String tag , String msg, Throwable e) {
        if(1 == DEBUG){
            android.util.Log.d(TAG, ""+tag+": "+msg+" ", e);
        }
    }

    public static void e(String tag , String msg) {
        if(1 == DEBUG){
            android.util.Log.e(TAG+ tag, ": "+msg+" ");
        }
    }

    public static void e(String tag , String msg, Throwable e) {
        if(1 == DEBUG){
            android.util.Log.e(TAG, ""+tag+": "+msg+" ", e);
        }
    }

    public static void i(String tag , String msg) {
        if(1 == DEBUG){
            android.util.Log.i(TAG, ""+tag+": "+msg+" ");
        }
    }

    public static void v(String tag , String msg) {
        if(1 == DEBUG){
            android.util.Log.v(TAG, ""+tag+": "+msg+" ");
        }
    }

    public static void w(String tag , String msg) {
        if(1 == DEBUG){
            android.util.Log.w(TAG, ""+tag+": "+msg+" ");
        }
    }

    public static void w(String tag , String msg, Throwable e) {
        if(1 == DEBUG){
            android.util.Log.w(TAG, ""+tag+": "+msg+" "+ e);
        }
    }
}