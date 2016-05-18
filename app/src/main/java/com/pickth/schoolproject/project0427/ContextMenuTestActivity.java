package com.pickth.schoolproject.project0427;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.pickth.schoolproject.R;

/**
 * Created by Kim on 2016-04-27.
 */
public class ContextMenuTestActivity extends AppCompatActivity {
    Button contextmenuBtnChangeBackground, contextmenuBtnChangeButton;
    LinearLayout contextmenuLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contextmenu_test);

        getSupportActionBar().setTitle(this.getLocalClassName());

        initializeLayout();
    }

    private void initializeLayout(){
        contextmenuBtnChangeBackground = (Button)findViewById(R.id.contextmenuBtnChangeBackground);
        registerForContextMenu(contextmenuBtnChangeBackground);
        contextmenuBtnChangeButton = (Button)findViewById(R.id.contextmenuBtnChangeButton);
        registerForContextMenu(contextmenuBtnChangeButton);
        contextmenuLayout = (LinearLayout)findViewById(R.id.contextmenuLayout);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if(v == contextmenuBtnChangeBackground){
            menu.setHeaderTitle("배경색 변경");
            menu.setHeaderIcon(R.drawable.imageview_test_rbjel);

            menu.add(0,1,0,"배경색 (빨강)");
            menu.add(0,2,0,"배경색 (초록)");
            menu.add(0,3,0,"배경색 (파랑)");
        }
        if(v == contextmenuBtnChangeButton){
            menu.add(0,4,0, "버튼 45도 회전");
            menu.add(0,5,0, "버튼 2배 확대");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);

        switch (item.getItemId()){
            case 1:
                contextmenuLayout.setBackgroundColor(Color.RED);
                return true;
            case 2:
                contextmenuLayout.setBackgroundColor(Color.GREEN);
                return true;
            case 3:
                contextmenuLayout.setBackgroundColor(Color.BLUE);
                return true;
            case 4:
                contextmenuBtnChangeButton.setRotation(45);
                return true;
            case 5:
                contextmenuBtnChangeButton.setScaleX(2);
                return true;

        }
        return false;
    }
}
