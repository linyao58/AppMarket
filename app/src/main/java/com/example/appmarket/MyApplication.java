package com.example.appmarket;

import android.app.Application;

import cn.bmob.v3.Bmob;

public class MyApplication extends Application {
    private  final String APP_ID = "9cc4a488427b825fe712252f2de18ec3";

    public void onCreate(){
        super.onCreate();
        Bmob.initialize(this, APP_ID);
    }
}
