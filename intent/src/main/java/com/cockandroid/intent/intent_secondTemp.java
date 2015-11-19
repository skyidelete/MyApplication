package com.cockandroid.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by com on 2015-11-19.
 */
public class intent_secondTemp extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_second);
        setTitle("Second Activity");
        int value = 0;
        boolean divByZero = false;
        Intent inIntent = getIntent();

        switch(inIntent.getStringExtra("Type")) {
            case "plus":
                value = inIntent.getIntExtra("Num1", 0) + inIntent.getIntExtra("Num2", 0);
                break;
            case "minus":
                value = inIntent.getIntExtra("Num1", 0) - inIntent.getIntExtra("Num2", 0);
                break;
            case "mul":
                value = inIntent.getIntExtra("Num1", 0) * inIntent.getIntExtra("Num2", 0);
                break;
            case "div":
                if(inIntent.getIntExtra("Num2", 0) == 0) {
                    value = 0;
                    divByZero = true;
                }
                else
                    value = inIntent.getIntExtra("Num1", 0) / inIntent.getIntExtra("Num2", 0);
                break;
        }

        Intent outIntent = new Intent(getApplicationContext(), intentTemp.class);
        outIntent.putExtra("Value", value);
        if(divByZero)   setResult(100, outIntent);
        else            setResult(RESULT_OK, outIntent);

        finish();
    }


}
