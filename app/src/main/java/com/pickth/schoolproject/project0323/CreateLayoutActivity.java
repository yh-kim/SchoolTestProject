package com.pickth.schoolproject.project0323;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Kim on 2016-03-23.
 */
public class CreateLayoutActivity extends AppCompatActivity {
    LinearLayout baseLayout;
    Button btn;
    EditText et;
    TextView tv;

    private void initializeLayout(){

        // 선언
        btn = new Button(this);
        et = new EditText(this);
        tv = new TextView(this);

        // 디자인
        btn.setText("버튼입니다");
        btn.setBackgroundColor(Color.MAGENTA);

        // 레이아웃에 박음
        baseLayout.addView(et);
        baseLayout.addView(btn);
        baseLayout.addView(tv);
    }

    private void createLayout(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        baseLayout = new LinearLayout(this);
        baseLayout.setOrientation(LinearLayout.VERTICAL);
//        baseLayout.setBackgroundColor(Color.rgb(0,255,0));
        setContentView(baseLayout,params);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle(this.getLocalClassName());

        createLayout();

        initializeLayout();

        setListener();
    }

    private void setListener(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText(et.getText());
            }
        });
    }
}
