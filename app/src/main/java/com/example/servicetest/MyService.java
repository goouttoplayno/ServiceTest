package com.example.servicetest;

import android.app.Person;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";
    private MyBinder mBinder = new MyBinder();

    //创建服务时调用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }
    //服务执行的操作
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                stopSelf();
//            }
//        }).start();
        Log.i(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }
    //销毁服务时调用
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestory");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class MyBinder extends Binder{
        public void startDownload(){
            Log.d(TAG, "startDownload() executed");
        }

        public int getProgress(){
            Log.d(TAG, "getProgress() executed");
            return 0;
        }
    }
}
