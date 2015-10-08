package com.cockandroid.calc2;

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

import org.w3c.dom.Text;

public class calc2 extends Activity {
    EditText edit1, edit2;
    Button btnAdd, btnMin, btnMul, btnDiv;
    TextView textResult;
    String input1, input2;
    Integer n1, n2;
    Integer result;
    Button[] numButtons = new Button[10];
    Integer[] numBtnIDs = { R.id.BtnNum0, R.id.BtnNum1, R.id.BtnNum2, R.id.BtnNum3, R.id.BtnNum4, R.id.BtnNum5, R.id.BtnNum6, R.id.BtnNum7, R.id.BtnNum8, R.id.BtnNum9 };
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc2_grid);

        setTitle("계산기");

        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);
        textResult = (TextView)findViewById(R.id.TextResult);

        for(i=0;i<numBtnIDs.length;i++)
        {
            numButtons[i] = (Button) findViewById(numBtnIDs[i]);
        }
        for(i=0;i<numBtnIDs.length;i++)
        {
            final int index;
            index = i;

            numButtons[index].setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    if(edit1.isFocused())
                    {
                        input1 = edit1.getText().toString() + numButtons[index].getText().toString();
                        edit1.setText(input1);
                    }
                    else if(edit2.isFocused())
                    {
                        input2 = edit2.getText().toString() + numButtons[index].getText().toString();
                        edit2.setText(input2);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "먼저 에디트텍스트를 선택하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    public void onClick(View view) {
        if(TextUtils.isEmpty(input1) || TextUtils.isEmpty(input2))
        {
            Toast.makeText(getApplicationContext(), "숫자를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        n1 = Integer.parseInt(input1);
        n2 = Integer.parseInt(input2);

        switch(view.getId())
        {
            case R.id.BtnAdd:  result = n1 + n2;   break;
            case R.id.BtnMin: result = n1 - n2;   break;
            case R.id.BtnMul: result = n1 * n2;   break;
            case R.id.BtnDiv:
                if(n2 == 0) {
                    Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                    result = n1 / n2;   break;
        }
        textResult.setText("계산 결과 : " + result.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calc2, menu);
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
