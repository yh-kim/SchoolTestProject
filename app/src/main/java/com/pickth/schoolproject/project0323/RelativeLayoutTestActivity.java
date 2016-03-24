package com.pickth.schoolproject.project0323;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pickth.schoolproject.R;

/**
 * Created by Kim on 2016-03-23.
 */
public class RelativeLayoutTestActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relativelayout_test);

        getSupportActionBar().setTitle(this.getLocalClassName());

    }
}
