package com.pickth.schoolproject.project0330;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pickth.schoolproject.R;

/**
 * Created by Kim on 2016-03-30.
 */
public class TableLayoutTestActivity extends AppCompatActivity{
    EditText tablelayout_test_editNum1, tablelayout_test_editNum2;
    Button tablelayout_test_btnAdd, tablelayout_test_btnSub, tablelayout_test_btnMul, tablelayout_test_btnDiv;
    Button[] numButtons = new Button[10];
    Integer[] numBtnIDs = {R.id.tablelayout_test_btnNum0,
            R.id.tablelayout_test_btnNum1,
            R.id.tablelayout_test_btnNum2,
            R.id.tablelayout_test_btnNum3,
            R.id.tablelayout_test_btnNum4,
            R.id.tablelayout_test_btnNum5,
            R.id.tablelayout_test_btnNum6,
            R.id.tablelayout_test_btnNum7,
            R.id.tablelayout_test_btnNum8,
            R.id.tablelayout_test_btnNum9
    };

    String num1, num2;

    TextView tablelayout_test_tvResult;

    private void initializeLayout(){
        tablelayout_test_editNum1 = (EditText)findViewById(R.id.tablelayout_test_editNum1);
        tablelayout_test_editNum2 = (EditText)findViewById(R.id.tablelayout_test_editNum2);

        tablelayout_test_editNum1.setInputType(0);
        tablelayout_test_editNum2.setInputType(0);

        tablelayout_test_btnAdd = (Button)findViewById(R.id.tablelayout_test_btnAdd);
        tablelayout_test_btnSub = (Button)findViewById(R.id.tablelayout_test_btnSub);
        tablelayout_test_btnMul = (Button)findViewById(R.id.tablelayout_test_btnMul);
        tablelayout_test_btnDiv = (Button)findViewById(R.id.tablelayout_test_btnDiv);
        tablelayout_test_tvResult = (TextView)findViewById(R.id.tablelayout_test_tvResult);

        for(int i=0; i<numBtnIDs.length; i++){
            numButtons[i] = (Button)findViewById(numBtnIDs[i]);
        }
    }

    private void setListener(){
        tablelayout_test_btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValue();
                if(isBlank())
                    return;
                tablelayout_test_tvResult.setText("계산결과 : " + (Integer.parseInt(num1) + Integer.parseInt(num2)));
            }
        });

        tablelayout_test_btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValue();
                if(isBlank())
                    return;
                tablelayout_test_tvResult.setText("계산결과 : " + (Integer.parseInt(num1) - Integer.parseInt(num2)));
            }
        });

        tablelayout_test_btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValue();
                if(isBlank())
                    return;
                tablelayout_test_tvResult.setText("계산결과 : " + (Integer.parseInt(num1) * Integer.parseInt(num2)));
            }
        });

        tablelayout_test_btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!getValue()){
                    Toast.makeText(getApplicationContext(),"0으로 나누면 안됩니다", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(isBlank())
                    return;
                tablelayout_test_tvResult.setText("계산결과 : " + (Integer.parseInt(num1) / Integer.parseInt(num2)));
            }
        });


        for(int i=0; i<numBtnIDs.length; i++){
            final int index;
            index = i;

            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(tablelayout_test_editNum1.isFocused() == true){
                        num1 = tablelayout_test_editNum1.getText().toString() + numButtons[index].getText().toString();
                        tablelayout_test_editNum1.setText(num1);
                    }else if(tablelayout_test_editNum2.isFocused() == true){
                        num2 = tablelayout_test_editNum2.getText().toString() + numButtons[index].getText().toString();
                        tablelayout_test_editNum2.setText(num2);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "먼저 에디트텍스트를 선택하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablelayout_test);

        getSupportActionBar().setTitle(this.getLocalClassName());

        initializeLayout();

        setListener();
    }

    private boolean getValue(){
        num1 = tablelayout_test_editNum1.getText().toString();
        num2 = tablelayout_test_editNum2.getText().toString();

        if(num2.equals("0")){
            return false;
        }

        return true;
    }

    private boolean isBlank(){
        if(num1.equals("") || num2.equals("")){
            Toast.makeText(getApplicationContext(),"비어 있습니다", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }
}
