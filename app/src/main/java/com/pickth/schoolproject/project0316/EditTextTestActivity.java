package com.pickth.schoolproject.project0316;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pickth.schoolproject.R;

/**
 * Created by Kim on 2016-03-16.
 */
public class EditTextTestActivity extends AppCompatActivity {
    EditText etValue1,etValue2;
    Button btnPlus,btnSub,btnMul,btnDiv,btnRemainder;
    TextView tvResult;
    String value1, value2;
    InputMethodManager keyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext_test);

        getSupportActionBar().setTitle(this.getLocalClassName());

        initializeLayout();

        setListener();
    }

    private void initializeLayout(){
        //스크린키보드
        keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        etValue1 = (EditText)findViewById(R.id.etValue1);
        etValue2 = (EditText)findViewById(R.id.etValue2);
        btnPlus = (Button)findViewById(R.id.btnPlus);
        btnSub = (Button)findViewById(R.id.btnSub);
        btnMul = (Button)findViewById(R.id.btnMul);
        btnDiv = (Button)findViewById(R.id.btnDiv);
        btnRemainder = (Button)findViewById(R.id.btnRemainder);
        tvResult = (TextView) findViewById(R.id.tvResult);
    }

    private boolean getValue(){
        value1 = etValue1.getText().toString();
        value2 = etValue2.getText().toString();

        if(value2.equals("0")){
            return false;
        }

        return true;
    }

    private boolean isBlank(){
        hideKeyboard();
        if(value1.equals("") || value2.equals("")){
            Toast.makeText(getApplicationContext(),"비어 있습니다", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

    private void setListener(){
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValue();
                if(isBlank())
                    return;
                tvResult.setText(Integer.parseInt(value1) + Integer.parseInt(value2) + "");
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValue();
                if(isBlank())
                    return;

                tvResult.setText(Integer.parseInt(value1) - Integer.parseInt(value2) + "");
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValue();

                if(isBlank())
                    return;
                tvResult.setText(Integer.parseInt(value1) * Integer.parseInt(value2) + "");
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!getValue()){
                    Toast.makeText(getApplicationContext(),"0으로 나눔", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(isBlank())
                    return;
                tvResult.setText(Integer.parseInt(value1) / Integer.parseInt(value2) + "");
            }
        });

        btnRemainder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!getValue()){
                    Toast.makeText(getApplicationContext(),"0으로 나눔", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(isBlank())
                    return;
                tvResult.setText(Integer.parseInt(value1) % Integer.parseInt(value2) + "");
            }
        });
    }

    private void hideKeyboard(){

        //키보드숨기기
        keyboard.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
}
