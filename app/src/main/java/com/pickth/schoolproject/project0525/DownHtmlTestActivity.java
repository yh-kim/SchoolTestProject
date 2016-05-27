package com.pickth.schoolproject.project0525;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pickth.schoolproject.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Kim on 2016-05-25.
 */
public class DownHtmlTestActivity extends AppCompatActivity{
    Button downhtmlBtnDown;
    TextView downhtmlTvResult;
    ProgressDialog mProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downhtml_test);

        getSupportActionBar().setTitle(this.getLocalClassName());
        initializeLayout();
        setListener();
    }

    private void initializeLayout(){
        downhtmlBtnDown = (Button)findViewById(R.id.downhtmlBtnDown);
        downhtmlTvResult = (TextView)findViewById(R.id.downhtmlTvResult);
    }

    private void setListener(){
        downhtmlBtnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgress = ProgressDialog.show(DownHtmlTestActivity.this, "wait", "Downloading...");
                DownThread thread = new DownThread("http://www.google.co.kr");
                thread.start();
            }
        });
    }


    class DownThread extends Thread{
        String mAddr;

        DownThread(String addr){
            mAddr = addr;
        }

        Handler mAfterDown = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mProgress.dismiss();
                TextView result = (TextView)findViewById(R.id.downhtmlTvResult);
                result.setText((String)msg.obj);
            }
        };

        @Override
        public void run() {
            super.run();
            String result = DownloadHtml(mAddr);
            Message message = mAfterDown.obtainMessage();
            message.obj = result;
            mAfterDown.sendMessage(message);
        }

        String DownloadHtml(String addr){
            StringBuilder html = new StringBuilder();
            try {
                URL url = new URL(addr);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                if(conn != null){
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);
                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        for(;;){
                            String line = br.readLine();
                            if(line == null) break;
                            html.append(line +"\n");
                        }

                        br.close();
                    }
                    conn.disconnect();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return html.toString();
        }
    }
}