package com.pickth.schoolproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Kim on 2016-03-18.
 */
public class LoadingActivity extends Activity {
    private long splashDelay = 1200;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        myTimerTask();
    }

    public void myTimerTask() {
        // Logo를 보여주는 쓰레드 (타이머)
        // 0.2초 후에 run메소드가 실행됨
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                // 메인 activity 이동
                Intent itMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(itMain);

                finish();
            }

        };
        //Timer 객체 생성
        Timer timer = new Timer();
        //몇초 후에 실행해라(splashDelay 초 후에 task를 실행해라)
        timer.schedule(task, splashDelay);
    }
}
