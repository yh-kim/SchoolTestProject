package com.pickth.schoolproject.project0330;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.pickth.schoolproject.R;

import java.util.Calendar;

/**
 * Created by Kim on 2016-03-30.
 */
public class DatePickerTestActivity extends AppCompatActivity {
    Chronometer datepicker_test_chronometer;
    LinearLayout datepicker_test_linearLayoutEnd;
    RadioGroup datepicker_test_rg;
    RadioButton datepicker_test_rbDay, datepicker_test_rbTime;
    DatePicker datepicker_test_dp;
    TimePicker datepicker_test_tp;
    TextView datepicker_test_tvYear, datepicker_test_tvMonth, datepicker_test_tvDay, datepicker_test_tvHour,datepicker_test_tvMinute;

    private void setListener(){
        datepicker_test_linearLayoutEnd.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                datepicker_test_rg.setVisibility(View.INVISIBLE);
                datepicker_test_tp.setVisibility(View.INVISIBLE);
                datepicker_test_dp.setVisibility(View.INVISIBLE);
                return true;
            }
        });

        datepicker_test_chronometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datepicker_test_rg.setVisibility(View.VISIBLE);
                datepicker_test_chronometer.setBase(SystemClock.elapsedRealtime());
                datepicker_test_chronometer.start();
                datepicker_test_chronometer.setTextColor(Color.RED);
            }
        });

        datepicker_test_linearLayoutEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 날짜 가져오기
                Calendar curDate = Calendar.getInstance();
                curDate.setTimeInMillis(datepicker_test_dp.getCalendarView().getDate());
                datepicker_test_tvYear.setText(Integer.toString(curDate.get(Calendar.YEAR)));
                datepicker_test_tvMonth.setText(Integer.toString(1 + curDate.get(Calendar.MONTH)));
                datepicker_test_tvDay.setText(Integer.toString(curDate.get(Calendar.DATE)));

                // 시간 가져오기
                datepicker_test_tvHour.setText(Integer.toString(datepicker_test_tp.getCurrentHour()));
                datepicker_test_tvMinute.setText(Integer.toString(datepicker_test_tp.getCurrentMinute()));

                datepicker_test_chronometer.stop();
                datepicker_test_chronometer.setTextColor(Color.BLUE);
            }
        });

        datepicker_test_rbDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datepicker_test_tp.setVisibility(View.INVISIBLE);
                datepicker_test_dp.setVisibility(View.VISIBLE);
            }
        });

        datepicker_test_rbTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datepicker_test_tp.setVisibility(View.VISIBLE);
                datepicker_test_dp.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker_test);
        getSupportActionBar().setTitle(this.getLocalClassName());
        initializeLayout();
        setListener();
    }

    private void initializeLayout(){
        datepicker_test_chronometer = (Chronometer)findViewById(R.id.datepicker_test_chronometer);
        datepicker_test_linearLayoutEnd = (LinearLayout) findViewById(R.id.datepicker_test_linearLayoutEnd);
        datepicker_test_rg = (RadioGroup)findViewById(R.id.datepicker_test_rg);
        datepicker_test_rbDay = (RadioButton)findViewById(R.id.datepicker_test_rbDay);
        datepicker_test_rbTime = (RadioButton)findViewById(R.id.datepicker_test_rbTime);
        datepicker_test_dp = (DatePicker) findViewById(R.id.datepicker_test_dp);
        datepicker_test_tp = (TimePicker)findViewById(R.id.datepicker_test_tp);
        datepicker_test_tvYear = (TextView)findViewById(R.id.datepicker_test_tvYear);
        datepicker_test_tvMonth = (TextView)findViewById(R.id.datepicker_test_tvMonth);
        datepicker_test_tvDay = (TextView)findViewById(R.id.datepicker_test_tvDay);
        datepicker_test_tvHour = (TextView)findViewById(R.id.datepicker_test_tvHour);
        datepicker_test_tvMinute = (TextView)findViewById(R.id.datepicker_test_tvMinute);

        datepicker_test_tp.setVisibility(View.INVISIBLE);
        datepicker_test_dp.setVisibility(View.INVISIBLE);
    }

}
