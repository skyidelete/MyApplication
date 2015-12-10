package com.example.kloc.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


public class Adapter extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        setTitle("그리드뷰 영화 포스터");

        final GridView gv = (GridView) findViewById(R.id.gridView01);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adapter, menu);
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

    public class MyGridAdapter extends BaseAdapter {
        Context context;
        Integer[] posterID = {R.drawable.movie_image_00, R.drawable.movie_image_01, R.drawable.movie_image_02,
                R.drawable.movie_image_03, R.drawable.movie_image_04, R.drawable.movie_image_05,
                R.drawable.movie_image_06, R.drawable.movie_image_07, R.drawable.movie_image_08,
                R.drawable.movie_image_09, R.drawable.movie_image_00, R.drawable.movie_image_01,
                R.drawable.movie_image_02, R.drawable.movie_image_03, R.drawable.movie_image_04,
                R.drawable.movie_image_05, R.drawable.movie_image_06, R.drawable.movie_image_07,
                R.drawable.movie_image_08, R.drawable.movie_image_09, R.drawable.movie_image_00,
                R.drawable.movie_image_01, R.drawable.movie_image_02, R.drawable.movie_image_03,
                R.drawable.movie_image_04, R.drawable.movie_image_05, R.drawable.movie_image_06,
                R.drawable.movie_image_07, R.drawable.movie_image_08, R.drawable.movie_image_09 };

        public MyGridAdapter(Context context) {
            this.context = context;
        }

        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View movieList = getLayoutInflater().inflate(R.layout.gridview01, null);
            movieList.setPadding(5, 5, 5, 5);
            //movieList.setLayoutParams(new GridView.LayoutParams(100, 150));
            ImageView imageView = (ImageView) movieList.findViewById(R.id.gridImage);
            TextView textTitle = (TextView) movieList.findViewById(R.id.gridTitle);
            //ImageView imageView = new ImageView(context);
            //imageView.setLayoutParams(new GridView.LayoutParams(100, 150));
            //imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            //imageView.setPadding(5, 5, 5, 5);
            imageView.setImageResource(posterID[position]);

            textTitle.setText(getTitle(posterID[position]));

            final int pos = position;
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    View dialogView = (View) View.inflate(Adapter.this, R.layout.dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(Adapter.this);
                    ImageView ivPoster = (ImageView) dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterID[pos]);
                    dlg.setTitle(getTitle(posterID[pos]));
                    dlg.setIcon(R.drawable.movie_icon);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기", null);
                    dlg.show();

                }
            });
            return movieList;
        }
    }

    public String getTitle(Integer posterID) {
        String str = "";
        switch (posterID) {
            case R.drawable.movie_image_00:
                str = "헝거게임 더 파이널";
                break;
            case R.drawable.movie_image_01:
                str = "내부자들";
                break;
            case R.drawable.movie_image_02:
                str = "검은 사제들";
                break;
            case R.drawable.movie_image_03:
                str = "열정같은 소리하고 있네";
                break;
            case R.drawable.movie_image_04:
                str = "도리화가";
                break;
            case R.drawable.movie_image_05:
                str = "리틀보이";
                break;
            case R.drawable.movie_image_06:
                str = "괴물의 아이";
                break;
            case R.drawable.movie_image_07:
                str = "해에게서 소년에게";
                break;
            case R.drawable.movie_image_08:
                str = "대호";
                break;
            case R.drawable.movie_image_09:
                str = "히말라야";
                break;
        }
        return str;
    }
}
