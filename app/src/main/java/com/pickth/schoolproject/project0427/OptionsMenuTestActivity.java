package com.pickth.schoolproject.project0427;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.pickth.schoolproject.R;

/**
 * Created by Kim on 2016-04-27.
 */
public class OptionsMenuTestActivity extends AppCompatActivity {
    EditText optionsmenuTestEditAngle;
    ImageView optionsmenuTestImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optionsmenu_test);

        getSupportActionBar().setTitle(this.getLocalClassName());

        initializeLayout();
    }

    private void initializeLayout(){
        optionsmenuTestEditAngle = (EditText)findViewById(R.id.optionsmenuTestEditAngle);
        optionsmenuTestImageView = (ImageView)findViewById(R.id.optionsmenuTestImageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu0427, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.menu0427Item1:
                optionsmenuTestImageView.setImageResource(R.drawable.imageview_test_rbjel);
                item.setChecked(true);
                return true;
            case R.id.menu0427Item2:
                optionsmenuTestImageView.setImageResource(R.drawable.imageview_test_rbkit);
                item.setChecked(true);
                return true;
            case R.id.menu0427Item3:
                optionsmenuTestImageView.setImageResource(R.drawable.imageview_test_rblol);
                item.setChecked(true);
                return true;
            default:
                if(optionsmenuTestEditAngle.getText().length() != 0){
                    int angle = Integer.parseInt(optionsmenuTestEditAngle.getText().toString());
                    if(angle >0 && angle<360){
                        optionsmenuTestImageView.setRotation(angle);
                    }else{
                        Toast.makeText(getApplicationContext(), "잘못된 값을 입력하셨습니다", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "각도를 입력해주세요", Toast.LENGTH_SHORT).show();
                }
        }
        return false;
    }

}