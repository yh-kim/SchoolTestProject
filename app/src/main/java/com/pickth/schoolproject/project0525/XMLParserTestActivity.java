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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Kim on 2016-05-25.
 */
public class XMLParserTestActivity extends AppCompatActivity {
    Button xmlparserBtnGetRankRaw;
    Button xmlparserBtnGetRank;
    ProgressDialog mProgress;
    boolean mRaw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmlparser_test);

        getSupportActionBar().setTitle(this.getLocalClassName());
        initializeLayout();
        setListener();
    }

    /**
     * 화면에 보여줄 정보 초기화
     */
    private void initializeLayout(){
        xmlparserBtnGetRankRaw = (Button)findViewById(R.id.xmlparserBtnGetRankRaw);
        xmlparserBtnGetRank = (Button)findViewById(R.id.xmlparserBtnGetRank);
    }

    /**
     * 리스너 설정
     */
    private void setListener(){
        xmlparserBtnGetRankRaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRaw = true;
                getRank();  // 데이터 가져오기
            }
        });

        xmlparserBtnGetRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRaw = false;
                getRank();  // 데이터 가져오기
            }
        });
    }

    /**
     * API 호출 - 네이버 데이터 가져오기
     */
    private void getRank(){
        mProgress = ProgressDialog.show(this, "wait", "Downloading...");
        DownThread thread = new DownThread("https://openapi.naver.com/v1/search/webkr.xml?query=%EB%8F%84%EA%B5%AC&display=1&start=1"); // 요청URL?변수명=값&변수명=값
        thread.start(); // 쓰레드 실행
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
                TextView result = (TextView)findViewById(R.id.xmlparserTvResult);
                String html = (String)msg.obj;

                if(mRaw){
                    // 첫번째 버튼 눌렀을 때
                    result.setText(html);
                }else{
                    // 두번째 버튼 눌렀을 때
                    String Result = "";

                    try {
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        InputStream is = new ByteArrayInputStream(html.getBytes("utf-8"));
                        Document doc = builder.parse(is);

                        Element root = doc.getDocumentElement();
                        Node item = root.getElementsByTagName("item").item(0);
                        Node rank = item.getFirstChild();

                        // 수정 필요
                        for(int i=1; i<2; i++){
                            Node k=rank.getFirstChild();
                            String sK = k.getFirstChild().getNodeValue();
                            Node s=k.getFirstChild();
                            String sS = s.getFirstChild().getNodeValue();
                            Node v=s.getFirstChild();
                            String sV = v.getFirstChild().getNodeValue();
                            rank = rank.getNextSibling();
                            Result += "" + i + "th : " + sK + ", " +sS + sV + "\n";
                        }

                        result.setText(Result);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
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
                conn.setRequestProperty("X-Naver-Client-Id","4BVe5MKp5l6J_WBCsrGO");    // 네이버 Client ID 입력
                conn.setRequestProperty("X-Naver-Client-Secret","PryrLTbGVX");          // 네이버 Client Secret 입력

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
