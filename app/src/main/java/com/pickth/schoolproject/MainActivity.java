package com.pickth.schoolproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
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
        dataList.add(new ProjectListItem("MusicTest","0608", "간단한 음악 재생 예제"));
        dataList.add(new ProjectListItem("XMLParserTest","0525", "네이버 검색 순위 조회 앱"));
        dataList.add(new ProjectListItem("DownHtmlTest","0525", "간단한 비동기 방식 인터넷 연결"));
        dataList.add(new ProjectListItem("BitmapTest","0511", "간단한 포토샵 예제"));
        dataList.add(new ProjectListItem("TouchEventTest","0511", "간단한 터치 이벤트"));
        dataList.add(new ProjectListItem("AlertDialogTest","0427", "간단한 다이얼로그 예제"));
        dataList.add(new ProjectListItem("ContextMenuTest","0427", "간단한 컨텍스트 메뉴 예제"));
        dataList.add(new ProjectListItem("OptionsMenuTest","0427", "간단한 옵션 메뉴 예제"));
        dataList.add(new ProjectListItem("DatePickerTest","0330", "간단한 예약 앱 예제"));
        dataList.add(new ProjectListItem("TableLayoutTest","0330", "간단한 table 레이아웃 예제(계산기)"));
        dataList.add(new ProjectListItem("RelativeLayoutTest","0323", "간단한 relative 레이아웃 예제"));
        dataList.add(new ProjectListItem("CreateLayout","0323", "간단한 레이아웃 만들기 예제"));
        dataList.add(new ProjectListItem("ImageViewTest","0323", "간단한 이미지, 라디오버튼 예제"));
        dataList.add(new ProjectListItem("EditTextTest","0316", "간단한 계산기 예제"));
        dataList.add(new ProjectListItem("ButtonTest","0316", "간단한 인텐트 예제"));
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

        lvProject.setOnScrollListener(new AbsListView.OnScrollListener() {
            boolean hideToolBar = false;

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if(hideToolBar){
                    mToolBar.setVisibility(View.INVISIBLE);
//                    mToolBar.animate().translationY(-mToolBar.getBottom()).setInterpolator(new AccelerateInterpolator()).start();
                }else{
                    mToolBar.setVisibility(View.VISIBLE);
//                    mToolBar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int dy) {
                if (dy > 20) {
                    hideToolBar = true;

                } else if (dy < -5) {
                    hideToolBar = false;
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