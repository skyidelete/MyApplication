package com.cockandroid.calc;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Calc extends Activity {
    EditText input1, input2;
    Button plusBtn, minusBtn, multiBtn, divBtn, remainderBtn;
    TextView resultText;
    Double num1, num2, result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        setTitle("초간단 계산기");

        input1 = (EditText)findViewById(R.id.Input1);
        input2 = (EditText)findViewById(R.id.Input2);
        //plusBtn = (Button)findViewById(R.id.PlusBtn);
        //minusBtn = (Button)findViewById(R.id.MinusBtn);
        //multiBtn = (Button)findViewById(R.id.MultiBtn);
        //divBtn = (Button)findViewById(R.id.DivBtn);
        //remainderBtn = (Button)findViewById(R.id.RemainderBtn);
        resultText = (TextView)findViewById(R.id.ResultText);


    }

    public void onClick(View view) {
        if(TextUtils.isEmpty(input1.getText().toString()) || TextUtils.isEmpty(input2.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "숫자를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        num1 = Double.parseDouble(input1.getText().toString());
        num2 = Double.parseDouble(input2.getText().toString());

        switch(view.getId())
        {
            case R.id.PlusBtn:  result = num1 + num2;   break;
            case R.id.MinusBtn: result = num1 - num2;   break;
            case R.id.MultiBtn: result = num1 * num2;   break;
            case R.id.DivBtn:
                if(num2 == 0) {
                    Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                    result = num1 / num2;   break;
            case R.id.RemainderBtn:
                if(num2 == 0) {
                    Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                    result = num1 % num2;   break;
        }
        resultText.setText("계산 결과 : " + result.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calc, menu);
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
