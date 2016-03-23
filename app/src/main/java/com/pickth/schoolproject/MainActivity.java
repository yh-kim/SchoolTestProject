package com.pickth.schoolproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Kim on 2016-03-16.
 */
public class MainActivity  extends AppCompatActivity {
    ListView lvProject;
    ProjectAdapter adapter;
    Toolbar mToolBar;
    ArrayList<ProjectListItem> dataList = new ArrayList<>();

    private void addData(){
        dataList.add(new ProjectListItem("ButtonTest","0316", "간단한 인텐트 예제"));
        dataList.add(new ProjectListItem("EditTextTest","0316", "간단한 계산기 예제"));
        dataList.add(new ProjectListItem("ImageViewTest","0323", "간단한 이미지, 라디오버튼 예제"));
        dataList.add(new ProjectListItem("CreateLayout","0323", "간단한 레이아웃 만들기 예제"));
    }

    private void initializeToolbar() {
        //액션바 객체 생성
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        //액션바 설정
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        //액션바 숨김
        actionBar.hide();

        //툴바 설정
        mToolBar = (Toolbar) findViewById(R.id.main_toolbar);
        mToolBar.setContentInsetsAbsolute(0, 0);
    }

    private void initializeLayout(){
        adapter = new ProjectAdapter(getApplicationContext(), R.layout.row_main, dataList);
        lvProject = (ListView)findViewById(R.id.lvProject);
    }

    private void setListener(){

        lvProject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    Intent intent = new Intent(getApplicationContext(), Class.forName(getPackageName() +".project" + dataList.get(i).getDate() + "." + dataList.get(i).getName()+"Activity"));
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    Toast.makeText(getApplicationContext(),"존재하지 않는 프로젝트", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeToolbar();
        initializeLayout();
        setListener();

        lvProject.setAdapter(adapter);

        addData();
        adapter.notifyDataSetChanged();
    }
}
