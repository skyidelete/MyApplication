package com.cockandroid.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Dialog extends Activity {

    EditText UserName, UserEmail;
    EditText UserName_Dialog, UserEmail_Dialog;
    Button btn;
    View dialogView, toastView;
    TextView toastText01;
    String name_shared, email_shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        setTitle("사용자 정보 입력");

        UserName = (EditText) findViewById(R.id.UserName);
        UserEmail = (EditText) findViewById(R.id.UserEmail);

        name_shared = "";
        email_shared = "";

        btn = (Button)findViewById(R.id.Button);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogView=(View)View.inflate(Dialog.this, R.layout.dialog101, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(Dialog.this);
                UserName_Dialog = (EditText) dialogView.findViewById(R.id.UserName_Dialog);
                UserEmail_Dialog = (EditText) dialogView.findViewById(R.id.UserEmail_Dialog);
                dlg.setTitle("사용자 정보 입력");
                dlg.setIcon(R.drawable.star);
                dlg.setView(dialogView);

                name_shared = UserName.getText().toString();
                email_shared = UserEmail.getText().toString();
                UserName_Dialog.setText(name_shared);
                UserEmail_Dialog.setText(email_shared);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        name_shared = UserName_Dialog.getText().toString();
                        email_shared = UserEmail_Dialog.getText().toString();
                        UserName.setText(name_shared);
                        UserEmail.setText(email_shared);
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = new Toast(Dialog.this);
                        Random rand = new Random();
                        toastView = (View) View.inflate(Dialog.this, R.layout.toast01, null);
                        toastText01 = (TextView) toastView.findViewById(R.id.toastText);
                        toastText01.setText("취소했습니다");
                        toast.setView(toastView);
                        toast.setGravity(Gravity.CENTER, rand.nextInt(1000)-500, rand.nextInt(1000)-500);
                        toast.show();
                    }
                });
                dlg.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dialog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
