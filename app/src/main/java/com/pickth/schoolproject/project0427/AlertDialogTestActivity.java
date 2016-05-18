package com.pickth.schoolproject.project0427;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pickth.schoolproject.R;

/**
 * Created by Kim on 2016-04-27.
 */
public class AlertDialogTestActivity extends AppCompatActivity {
    EditText alertdialogEditName, alertdialogEditEmail;
    Button alertdialogBtnSubmit;
    View dialogView, toastView;
    TextView toast_0427Textview;

    EditText dialogEditName, dialogEditEmail;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertdialog_test);

        getSupportActionBar().setTitle(this.getLocalClassName());

        initializeLayout();

        setListener();
    }

    private void initializeLayout(){
        alertdialogEditName = (EditText)findViewById(R.id.alertdialogEditName);
        alertdialogEditEmail = (EditText)findViewById(R.id.alertdialogEditEmail);
        alertdialogBtnSubmit = (Button)findViewById(R.id.alertdialogBtnSubmit);
    }

    private void setListener(){
        alertdialogBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView = View.inflate(AlertDialogTestActivity.this, R.layout.dialog_item_0427, null);
                AlertDialog.Builder dialog = new AlertDialog.Builder(AlertDialogTestActivity.this);

                dialog.setTitle("사용자 정보 입력");
                dialog.setIcon(R.drawable.imageview_test_rbjel);
                dialog.setView(dialogView);

                dialogEditName = (EditText)dialogView.findViewById(R.id.dialog_item_0427_EditName);
                dialogEditEmail = (EditText)dialogView.findViewById(R.id.dialog_item_0427_EditEmail);

                dialogEditName.setText(alertdialogEditName.getText().toString());
                dialogEditEmail.setText(alertdialogEditEmail.getText().toString());

                dialog.setPositiveButton("확인",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertdialogEditName.setText(dialogEditName.getText());
                        alertdialogEditEmail.setText(dialogEditEmail.getText());
                    }
                });
                dialog.setNegativeButton("취소",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = new Toast(AlertDialogTestActivity.this);
                        toastView = (View) View.inflate(AlertDialogTestActivity.this, R.layout.toast_0427, null);
                        toast_0427Textview = (TextView) toastView.findViewById(R.id.toast_0427Textview);
                        toast_0427Textview.setText("취소했습니다");
                        toast.setView(toastView);

                        // 토스트 위치 랜덤으로
                        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                        int x = (int)(Math.random()*display.getWidth());
                        int y = (int)(Math.random()*display.getHeight());
                        toast.setGravity(Gravity.TOP|Gravity.LEFT, x, y);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();

                    }
                });
                dialog.show();
            }
        });
    }
}
