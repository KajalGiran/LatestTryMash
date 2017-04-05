package com.trymash.Utils;

import android.os.Handler;

import com.trymash.Activity.MainActivity;


public class Timer {
    Handler thandle = new Handler();
    int i = 0;
    public void myThread() {
        Thread t = new Thread() {
            @Override
            public void run() {

                MainActivity.fetch = 55;
                for (i = 0; i <= 55; i++) {
                    thandle.post(new Runnable() {
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            if (!MainActivity.pause) {
                                MainActivity.fetch = MainActivity.fetch - 1;
                                if (MainActivity.fetch < 10) {
                                    MainActivity.ttv.setText(Constant.ZERO + MainActivity.fetch + "");
                                } else {
                                    MainActivity.ttv.setText(MainActivity.fetch + "");
                                }
                                if (MainActivity.fetch == 0) {
                                    System.exit(0);
                                }
                            } else {
                                i--;
                            }
                        }
                    });
                    try {
                        Thread.sleep(1850);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }
}