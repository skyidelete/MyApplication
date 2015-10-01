package com.cockandroid.image;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;



public class Image extends Activity {
    TextView text01, text02;
    Switch start;
    RadioGroup rGroup01;
    RadioButton rdoJ, rdoK, rdoL;
    Button btnExit, btnClear;
    ImageView imgVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        btnExit = (Button) findViewById(R.id.btnExit);
        btnClear = (Button) findViewById(R.id.btnClear);
        start = (Switch) findViewById(R.id.start);
        rGroup01 = (RadioGroup) findViewById(R.id.Rgroup01);
        imgVer = (ImageView) findViewById(R.id.imgVer);
        text02 = (TextView) findViewById(R.id.textView02);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        start.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    text02.setVisibility(View.VISIBLE);
                    rGroup01.setVisibility(View.VISIBLE);
                    imgVer.setVisibility(View.VISIBLE);
                    btnExit.setVisibility(View.VISIBLE);
                    btnClear.setVisibility(View.VISIBLE);
                } else {
                    text02.setVisibility(View.INVISIBLE);
                    rGroup01.setVisibility(View.INVISIBLE);
                    imgVer.setVisibility(View.INVISIBLE);
                    btnExit.setVisibility(View.INVISIBLE);
                    btnClear.setVisibility(View.INVISIBLE);
                }
            }
        });
        rGroup01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (rGroup01.getCheckedRadioButtonId()) {
                    case R.id.RdoJ:
                        imgVer.setImageResource(R.drawable.j);
                        break;
                    case R.id.RdoK:
                        imgVer.setImageResource(R.drawable.k);
                        break;
                    case R.id.RdoL:
                        imgVer.setImageResource(R.drawable.l);
                        break;
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image, menu);
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
