package com.pickth.schoolproject.project0608;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pickth.schoolproject.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Kim on 2016-06-08.
 */
public class MusicTestActivity extends AppCompatActivity{
    ListView musicLvMP3;
    Button musicBtnPlay, musicBtnStop,musicBtnPause;
    TextView musicTvMP3;
    ProgressBar musicPbMP3;
    ArrayList<String> arrMp3;
    String selectedMP3;
    String mp3Path = "/storage/emulated/0/Download/";
    MediaPlayer mPlayer;

    boolean isPause = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_test);

        getSupportActionBar().setTitle(this.getLocalClassName());
        getFileList();

        initializeLayout();
        setListener();
    }

    private void initializeLayout(){

        mPlayer = new MediaPlayer();
        musicLvMP3 = (ListView)findViewById(R.id.musicLvMP3);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, arrMp3);

        musicLvMP3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        musicLvMP3.setAdapter(adapter);
        musicLvMP3.setItemChecked(0,true);
        selectedMP3 = arrMp3.get(0);

        musicBtnPlay = (Button)findViewById(R.id.musicBtnPlay);
        musicBtnStop = (Button)findViewById(R.id.musicBtnStop);
        musicBtnPause = (Button)findViewById(R.id.musicBtnPause);
        musicBtnStop.setClickable(true);
        musicTvMP3 = (TextView)findViewById(R.id.musicTvMP3);
        musicPbMP3 = (ProgressBar)findViewById(R.id.musicPbMP3);
    }

    private void setListener(){
        musicLvMP3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMP3 = arrMp3.get(i);
            }
        });

        musicBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mPlayer.setDataSource(mp3Path + selectedMP3);
                    mPlayer.prepare();
                    mPlayer.start();

                    musicBtnPlay.setClickable(false);
                    musicBtnPause.setClickable(true);
                    musicBtnStop.setClickable(true);

                    musicTvMP3.setText("실행중인 음악 : " +selectedMP3);
                    musicPbMP3.setVisibility(View.VISIBLE);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        musicBtnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 일시 정지 누르면
                if(!isPause){
                    musicBtnPause.setText("이어듣기");

                    mPlayer.pause();
                    musicTvMP3.setText("일시정지 : " + selectedMP3);

                    musicPbMP3.setVisibility(View.INVISIBLE);

                    isPause = true;
                }
                else{
                    musicBtnPause.setText("일시정지");

                    mPlayer.start();

                    musicTvMP3.setText("실행중인 음악 : " +selectedMP3);
                    musicPbMP3.setVisibility(View.VISIBLE);

                    isPause = false;
                }

                musicBtnPlay.setClickable(true);
                musicBtnPause.setClickable(true);
                musicBtnStop.setClickable(true);
            }
        });

        musicBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPause = false;
                mPlayer.stop();
                mPlayer.reset();

                musicBtnPlay.setClickable(true);
                musicBtnStop.setClickable(false);
                musicBtnPause.setClickable(false);
                musicTvMP3.setText("실행중인 음악 : ");
                musicPbMP3.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void getFileList(){
        arrMp3 = new ArrayList<String>();

        File[] listFiles = new File(mp3Path).listFiles();
        String fileName, extName;
        for(File file : listFiles){
            fileName = file.getName();
            extName = fileName.substring(fileName.length() - 3);
            if(extName.equals((String)"mp3"))
                arrMp3.add(fileName);
        }
    }
}
