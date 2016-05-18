package com.pickth.schoolproject.project0511;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.pickth.schoolproject.R;

/**
 * Created by Kim on 2016-05-11.
 */
public class BitmapTestActivity extends AppCompatActivity{
    //위젯변수선언
    ImageButton bitmap_ibZoomin, bitmap_ibZoomout, bitmap_ibRotate, bitmap_ibBright, bitmap_ibDark, bitmap_ibGray;

    //클래스변수선언
    MyGraphicView bitmap_graphicView;

    //아이콘변수 선언
    static float scaleX = 1, scaleY = 1;
    static float angle = 0;
    static float color = 1;
    static float satur = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_test);
        getSupportActionBar().setTitle(this.getLocalClassName());

        //xml의 pictureLayout을 인플레이트한후 MyGraphic변수첨부
        LinearLayout pictureLayout = (LinearLayout)findViewById(R.id.bitmap_pictureLayout);
        bitmap_graphicView = new MyGraphicView(this);
        pictureLayout.addView(bitmap_graphicView);
        clicklcons(); //확대메소드호출
    }

    //아이콘메소드 생성
    private void clicklcons() {
        //확대 아이콘
        bitmap_ibZoomin = (ImageButton)findViewById(R.id.bitmap_ibZoomin);
        bitmap_ibZoomin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scaleX = scaleX + 0.2f;
                scaleY = scaleY + 0.2f;
                bitmap_graphicView.invalidate();
            }
        });

        bitmap_ibZoomout = (ImageButton)findViewById(R.id.bitmap_ibZoomout);
        bitmap_ibZoomout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scaleX = scaleX - 0.2f;
                scaleY = scaleY - 0.2f;
                bitmap_graphicView.invalidate();
            }
        });

        //회전아이콘
        bitmap_ibRotate = (ImageButton)findViewById(R.id.bitmap_ibRotate);
        bitmap_ibRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                angle = angle + 20;
                bitmap_graphicView.invalidate();
            }
        });

        //밝게하기 아이콘
        bitmap_ibBright = (ImageButton)findViewById(R.id.bitmap_ibBright);
        bitmap_ibBright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = color + 0.2f;
                bitmap_graphicView.invalidate();
            }
        });

        bitmap_ibDark = (ImageButton)findViewById(R.id.bitmap_ibDark);
        bitmap_ibDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = color - 0.2f;
                bitmap_graphicView.invalidate();
            }
        });

        //회색영상
        bitmap_ibGray = (ImageButton)findViewById(R.id.bitmap_ibGray);
        bitmap_ibGray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(satur == 0)
                    satur = 1;
                else
                    satur = 0;
                bitmap_graphicView.invalidate();
            }
        });

    }

    private static class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int cenX = this.getWidth() /2;
            int cenY = this.getHeight() /2;
            canvas.scale(scaleX, scaleY, cenX, cenY);
            canvas.rotate(angle, cenX, cenY);

            Paint paint = new Paint();

            float [] array = {color, 0,0,0,0,0, color, 0,0,0,0,0, color,0,0,0,0,0,1,0};
            ColorMatrix cm = new ColorMatrix(array);

            if(satur ==0)
                cm.setSaturation(satur);

            paint.setColorFilter(new ColorMatrixColorFilter(cm));

            Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.imageview_test_rblol);
            int picX = (this.getWidth() - picture.getWidth()) /2;
            int picY = (this.getHeight() - picture.getHeight()) /2;
            canvas.drawBitmap(picture, picX, picY, paint);
            picture.recycle();
        }
    }

}
