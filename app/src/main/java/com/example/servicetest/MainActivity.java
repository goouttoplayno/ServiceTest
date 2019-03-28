package com.example.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.nfc.Tag;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button button1_start_service, button2_stop_service,
            button3_start_intentservice, button4_bind_service, button5_unbind_service;
    private MyService.MyBinder myBinder;
    private boolean mBound = false;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder)service;
            myBinder.startDownload();
            myBinder.getProgress();
            mBound = true;
        }
        //异常终止时调用，解除绑定时不会调用
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG,"异常终止了");
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1_start_service = (Button) findViewById(R.id.button1_start_service);
        button2_stop_service = (Button) findViewById(R.id.button2_stop_service);
        button3_start_intentservice = (Button)findViewById(R.id.button3_stop_intentservice);
        button4_bind_service = (Button)findViewById(R.id.button4_bing_service);
        button5_unbind_service = (Button)findViewById(R.id.button5_unbind_service);
        button1_start_service.setOnClickListener(this);
        button2_stop_service.setOnClickListener(this);
        button3_start_intentservice.setOnClickListener(this);
        button4_bind_service.setOnClickListener(this);
        button5_unbind_service.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1_start_service:
                Intent startintent = new Intent(this, MyService.class);
                startService(startintent);
                break;
            case R.id.button2_stop_service:
                Intent stopintent = new Intent(this, MyService.class);
                stopService(stopintent);
                break;
            case R.id.button3_stop_intentservice:
                Log.d(TAG, "主线程的id:" + Thread.currentThread().getId());
                Intent intentservice = new Intent(this, MyIntentService.class);
                startService(intentservice);
            case R.id.button4_bing_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.button5_unbind_service:
                if (mBound) {
                    unbindService(connection);
                    mBound = false;
                }
                break;
            default:
                break;
        }
    }
}
