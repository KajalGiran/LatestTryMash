package com.trymash.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trymash.R;
import com.trymash.Utils.Constant;
import com.trymash.Utils.Fruits;
import com.trymash.Utils.Timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends Activity {

    int ran = 0;
    public static int fetch = 0;
    RelativeLayout rl = null;
    RelativeLayout rl1 = null;
    RelativeLayout rl2 = null;
    public static String selected = null;
    public static ArrayList selectedFruitArr = new ArrayList();
    public static HashMap fruitsMap = new HashMap();
    public static ImageView iv = null;
    public static ImageView chImage1 = null;
    public static ImageView chImage2 = null;
    public static ImageView chImage3 = null;
    public static ImageView chImage4 = null;
    public static ImageView buciv = null;
    public static ImageView aiv = null;
    public static TextView tv = null;
    public static TextView ttv = null;
    public static Boolean pause = false;
    public static int width = 0;
    public static int height = 0;
    public static int mcount = 0;
    public static int level = 0;
    //static int score=0;
    public static int a = 0;
    public static MainActivity act = null;
    public MediaPlayer bgsong = null;
    public static MediaPlayer ssong = null;
    public static MediaPlayer csong = null;
    static Boolean sound = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rl = (RelativeLayout) findViewById(R.id.rl);
        rl1 = (RelativeLayout) findViewById(R.id.rl1);
        rl2 = (RelativeLayout) findViewById(R.id.rl2);
        iv = (ImageView) findViewById(R.id.iv);
        buciv = (ImageView) findViewById(R.id.buciv);
        aiv = (ImageView) findViewById(R.id.aiv);
        tv = (TextView) findViewById(R.id.tv);
        ttv = (TextView) findViewById(R.id.ttv);
        width = getWidth(getBaseContext());
        height = getHeight(getBaseContext());
        if (sound) {
            bgsong = MediaPlayer.create(MainActivity.this, R.raw.bgsound);
            bgsong.start();
        }
        act = this;
        ttv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (!pause) {
                    pause = true;
                    if (sound) {
                        bgsong.stop();
                    }
                } else {
                    pause = false;
                    if (sound) {
                        bgsong = MediaPlayer.create(MainActivity.this, R.raw.bgsound);
                        bgsong.start();
                    }
                }
            }
        });
        fruitsMap.put(Constant.APPLE, R.drawable.apple);
        fruitsMap.put(Constant.ORANGE, R.drawable.orange);
        fruitsMap.put(Constant.STRAWBERRY, R.drawable.strawberry);
        fruitsMap.put(Constant.MANGO, R.drawable.shalu);
        fruitsMap.put(Constant.GRAPES, R.drawable.grapes);
        fruitsMap.put(Constant.PEAR, R.drawable.pear);
        fruitsMap.put(Constant.LICHEE, R.drawable.lichee);
        fruitsMap.put(Constant.ANAR, R.drawable.anar);
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (!pause) {
                        ran = new Random().nextInt(6);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                switch (ran) {
                                    case 0: {
                                        rl1.addView(new Fruits.Apple(getApplicationContext()));
                                        break;
                                    }
                                    case 1: {
                                        rl1.addView(new Fruits.Orange(getApplicationContext()));
                                        break;
                                    }
                                    case 2: {
                                        rl1.addView(new Fruits.Strawberry(getApplicationContext()));
                                        break;
                                    }
                                    case 3: {
                                        rl1.addView(new Fruits.Grapes(getApplicationContext()));
                                        break;
                                    }
                                    case 4: {
                                        rl1.addView(new Fruits.Mango(getApplicationContext()));
                                        break;
                                    }
                                    case 5: {
                                        rl1.addView(new Fruits.Pear(getApplicationContext()));
                                        break;
                                    }
                                    case 6: {
                                        rl1.addView(new Fruits.Lichee(getApplicationContext()));
                                        break;
                                    }
                                    case 7: {
                                        rl1.addView(new Fruits.Anar(getApplicationContext()));
                                        break;
                                    }
                                }
                            }
                        });

                    }
                    try {
                        Thread.sleep(480);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        new Timer().myThread();
    }

    @SuppressLint("NewApi")
    public static int getWidth(Context mContext) {
        int width = 0;

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT > 12) {
            Point size = new Point();
            display.getSize(size);
            width = size.x;
        } else {
            width = display.getWidth();  // Deprecated

        }
        return width;
    }

    @SuppressLint("NewApi")
    public static int getHeight(Context mContext) {

        int height = 0;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT > 12) {
            Point size = new Point();
            display.getSize(size);
            height = size.y;
        } else {
            height = display.getHeight();
        }
        return height;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        int var = item.getItemId();

        switch (var) {
            case R.id.m1: {
                Intent in = new Intent(getApplicationContext(), SoundSettingsActivity.class);
                startActivity(in);
                break;
            }
            case R.id.m2: {
                Intent in2 = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(in2);
                break;
            }
            case R.id.m3: {
                //Intent in=new Intent(getApplicationContext(), AboutUs.class);
                //startActivity(in);
                finish();
                System.exit(0);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
       /* bgsong.release();
        finish();
        ssong.release();
        finish();
        csong.release();
        finish();*/
    }
}
