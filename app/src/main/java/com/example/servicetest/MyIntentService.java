package com.example.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";
    public MyIntentService(){
        super("MyIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        for (int i = 0; i < 3; i++){
            Log.d(TAG, "IntentService线程的id:" + Thread.currentThread().getId());
            try {
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestory");
    }
}
