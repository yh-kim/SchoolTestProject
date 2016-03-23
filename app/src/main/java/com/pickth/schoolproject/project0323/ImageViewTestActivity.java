package com.pickth.schoolproject.project0323;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.pickth.schoolproject.R;

/**
 * Created by Kim on 2016-03-23.
 */
public class ImageViewTestActivity extends AppCompatActivity {
    Switch imageview_test_switch;
    RadioGroup imageview_test_rg;
    ImageView imageview_test_img;
    Button imageview_test_btnClose;
    Button imageview_test_btnReset;
    RadioButton imageview_test_rbJel, imageview_test_rbKit, imageview_test_rbLol;

    boolean isStart = false;

    private void initializeLayout(){
        imageview_test_switch = (Switch)findViewById(R.id.imageview_test_switch);
        imageview_test_rg = (RadioGroup)findViewById(R.id.imageview_test_rg);
        imageview_test_img = (ImageView)findViewById(R.id.imageview_test_img);
        imageview_test_btnClose = (Button)findViewById(R.id.imageview_test_btnClose);
        imageview_test_btnReset = (Button)findViewById(R.id.imageview_test_btnReset);
        imageview_test_rbJel = (RadioButton)findViewById(R.id.imageview_test_rbJel);
        imageview_test_rbKit = (RadioButton)findViewById(R.id.imageview_test_rbKit);
        imageview_test_rbLol = (RadioButton)findViewById(R.id.imageview_test_rbLol);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview_test);

        getSupportActionBar().setTitle(this.getLocalClassName());

        initializeLayout();

        setListener();

        imageview_test_switch.setChecked(false);
    }

    private void setListener(){
        imageview_test_btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imageview_test_btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itThis = new Intent(getApplicationContext(), ImageViewTestActivity.class);
                startActivity(itThis);
                finish();
            }
        });

        imageview_test_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(imageview_test_switch.isChecked()){
                    isStart = true;
                }
                else{
                    isStart = false;
                    imageview_test_img.setImageResource(0);
                }
            }
        });

        imageview_test_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (imageview_test_switch.isChecked()) {
                    switch (imageview_test_rg.getCheckedRadioButtonId()) {
                        case R.id.imageview_test_rbJel:
                            imageview_test_img.setImageResource(R.drawable.imageview_test_rbjel);
                            break;
                        case R.id.imageview_test_rbKit:
                            imageview_test_img.setImageResource(R.drawable.imageview_test_rbkit);
                            break;
                        case R.id.imageview_test_rbLol:
                            imageview_test_img.setImageResource(R.drawable.imageview_test_rblol);
                            break;
                    }
                }
            }
        });
    }
}
