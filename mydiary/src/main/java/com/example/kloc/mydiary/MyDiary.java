package com.example.kloc.mydiary;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MyDiary extends Activity {
    final String m_strPath = "sdcard/mydiary";

    TextView textView_Date;
    Button btn_Save;
    EditText editText_Content;
    String m_strFilename;
    int cYear, cMonth, cDay;
    Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary);

        init();

        textView_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    File output = new File(m_strPath + "/" + m_strFilename);
                    FileOutputStream outFs = new FileOutputStream(output);
                    String str = editText_Content.getText().toString();
                    outFs.write(str.getBytes());
                    Toast.makeText(getApplicationContext(), m_strPath + "에 " + m_strFilename + "이 저장됨", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_diary, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_reload:
                loadDiary();
                break;
            case R.id.menu_delete:
                showDeleteDialog();
                break;
            case R.id.font_large:
                editText_Content.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
                break;
            case R.id.font_normal:
                editText_Content.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                break;
            case R.id.font_small:
                editText_Content.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    void showDatePickerDialog() {
        final int tempYear = cYear;
        final int tempMonth = cMonth;
        final int tempDay = cDay;

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                cYear = year;
                cMonth = monthOfYear+1;
                cDay = dayOfMonth;
                loadDiary();
            }
        }, cYear, cMonth-1, cDay);

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface arg0) {
                cYear = tempYear;
                cMonth = tempMonth;
                cDay = tempDay;
            }
        });

        dialog.setTitle("날짜 선택");

        dialog.show();
    }

    void showDeleteDialog() {
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
        alert_confirm.setMessage(cYear + "년 " + cMonth + "월 " + cDay + "일 일기를 삭제하시겠습니까?").setCancelable(false).setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!fileExists(m_strPath + "/" + m_strFilename)) {
                            Toast.makeText(getApplicationContext(), "파일이 존재하지 않음", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (deleteDiary(m_strPath + "/" + m_strFilename)) {
                            Toast.makeText(getApplicationContext(), m_strFilename + " 삭제 완료", Toast.LENGTH_SHORT).show();
                            editText_Content.setText("");
                        }
                        else
                            Toast.makeText(getApplicationContext(), m_strFilename + " 삭제하지 못함", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        return;
                    }
                });
        AlertDialog alert = alert_confirm.create();
        alert.show();
    }

    void init() {
        textView_Date = (TextView)findViewById(R.id.textView_Date);
        btn_Save = (Button)findViewById(R.id.btn_Save);
        editText_Content = (EditText)findViewById(R.id.editText_Content);

        cal = Calendar.getInstance();
        cYear = cal.get(Calendar.YEAR);
        cMonth = cal.get(Calendar.MONTH)+1;
        cDay = cal.get(Calendar.DAY_OF_MONTH);

        loadDiary();
    }


    String readDiary(String fName) {
        String diaryStr = null;

        FileInputStream inFs;
        try {
            if(!fileExists(m_strPath)) {
                File file = new File(m_strPath);
                file.mkdir();
            }
            inFs = new FileInputStream(m_strPath + "/" + fName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
        } catch (IOException e)
        {
            editText_Content.setHint("일기 없음");
        }
        return diaryStr;
    }

    boolean fileExists(String fName) {
        File file = new File(fName);
        return file.exists();
    }

    void loadDiary() {
        textView_Date.setText(cYear + "년 " + cMonth + "월 " + cDay + "일");
        m_strFilename = Integer.toString(cYear) + "_" + Integer.toString(cMonth) + "_" + Integer.toString(cDay) + ".txt";
        editText_Content.setText(readDiary(m_strFilename));
    }

    boolean deleteDiary(String fName) {
        File file = new File(fName);
        return file.delete();
    }
}