///android gmap api v2
//go first link
//get stratted
//go debug....somthing option......
///copy a cmd into notepad
//then paste the our eclipse debug key store path into "  " and then edited cmd run on cmd prompt

//open google api consol.....
//go net create project
//project name   then go further and google map android api v2
//then go again up and go to API Access ......go to eclipse and create proj for using GApi......Go to ...create new android key....then paste  here shai   into a blang space then add semicolon....and then write hereour package name....  click and then we get our google android Api key... 
//add api key to our aap
//copy  
//go aap ,,manifest......application...copy metadata....copy Api key here in name att
//then copy permissions from net...and paste into manifest....
//copy user feature...
//paste in manifest....
//now for adding map;
//copy fragment tag and paste into the aap.....xml file...
///open android sdk manager...go extra library...go to google play services...if not then import from...folder..
//google play services_lib....import....
//eclips debug store...
package com.trymash.Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.trymash.Activity.MainActivity;
import com.trymash.Activity.ScoreBoardActivity;
import com.trymash.R;
import com.trymash.helper.DatabaseHelper;
import com.trymash.model.ScoreBoard;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;


class Common extends ImageView {
    Handler handle = null;
    Context cont = null;
    public Common(Context context) {
        super(context);
        cont = context;
        //add animation
        Animation myAnimHang = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        startAnimation(myAnimHang);
        //handle object distribution via x-axis
        int r = new Random().nextInt(MainActivity.width - 50);
        setY(0);
        setX(r);

        //handle object motion via y-axis
        handle = new Handler();
        new Thread() {
            @Override
            public void run() {
                for (int i = 1; i <= MainActivity.height / 2 - 60; i++) {
                    if (!MainActivity.pause) {
                        handle.post(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                setY(getY() + 2);
                                if (MainActivity.fetch == 1) {
                                    Toast t = Toast.makeText(cont, Constant.TIME_UP, Toast.LENGTH_SHORT);//////..........not working............///////
                                    t.show();
                                }
                            }

                        });
                    } else {
                        i--;
                    }
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
                if (MainActivity.selectedFruitArr.contains(Common.this)) {
                    handle.post(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.selectedFruitArr.remove(Common.this);
                            MainActivity.selected = null;  //////////for removing select item//
                            if ((MainActivity.selectedFruitArr.size()) == 1) {
                                MainActivity.iv.setBackgroundResource(R.drawable.bg11);
                            }
                            if ((MainActivity.selectedFruitArr.size()) == 2) {
                                MainActivity.iv.setBackgroundResource(R.drawable.bg22);
                            }
                            if ((MainActivity.selectedFruitArr.size()) == 3) {
                                if (MainActivity.level == 3) {
                                    MainActivity.iv.setBackgroundResource(R.drawable.l3bg33);
                                }
                            }
                            if ((MainActivity.selectedFruitArr.size()) == 0) {
                                MainActivity.iv.setBackgroundResource(R.drawable.blankbg);
                                MainActivity.iv.setImageDrawable(null);
                            }

                        }
                    });

                }
                handle.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        Common.this.setVisibility(View.GONE);

                    }
                });
            }
        }.start();

        setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                ImageView img = (ImageView) arg0;
                MainActivity.ssong = MediaPlayer.create(cont, R.raw.blop);
                MainActivity.ssong.start();
                if (MainActivity.selected != null) {
                    if (!(MainActivity.selected.equals(img.getTag().toString()))) {

                        MainActivity.a--;
                        MainActivity.tv.setText(Constant.SCORE + MainActivity.a);

                        for (int i = 0; i < MainActivity.selectedFruitArr.size(); i++) {

                            ImageView chImage = (ImageView) MainActivity.selectedFruitArr.get(i);
                            //chImage.setImageResource(Integer.parseInt(MainActivity.fruitsMap.get(MainActivity.selected).toString()));
                                chImage.setColorFilter(null);
                        }
                        MainActivity.selectedFruitArr.removeAll(MainActivity.selectedFruitArr);
                        MainActivity.selected = img.getTag().toString();

                        MainActivity.selectedFruitArr.add(img);


                    } else {
                        if (MainActivity.selected.equals(img.getTag().toString())) {
                            if (!MainActivity.selectedFruitArr.contains(img)) {
                                MainActivity.selectedFruitArr.add(img);
                            }
                        }
                    }
                } else {
                    MainActivity.selected = img.getTag().toString();

                    if (!MainActivity.selectedFruitArr.contains(img)) {
                        MainActivity.selectedFruitArr.add(img);
                    }
                }
                int acount = MainActivity.selectedFruitArr.size();
                if (acount == 1) {
                    MainActivity.iv.setBackgroundResource(R.drawable.bg11);
                }
                if (acount == 2) {
                    MainActivity.iv.setBackgroundResource(R.drawable.bg22);
                }
                if (acount == 3) {
                    MainActivity.iv.setBackgroundResource(R.drawable.bg33);
                }
                if (acount == 4) {
                    MainActivity.iv.setBackgroundResource(R.drawable.l3bg44);
                }
                MainActivity.iv.setImageResource(Integer.parseInt(MainActivity.fruitsMap.get(MainActivity.selected).toString()));
                if (MainActivity.selectedFruitArr.size() == 3) {
                    MainActivity.selected = null;
                    MainActivity.chImage1 = (ImageView) MainActivity.selectedFruitArr.get(0);
                    MainActivity.chImage2 = (ImageView) MainActivity.selectedFruitArr.get(1);
                    MainActivity.chImage3 = (ImageView) MainActivity.selectedFruitArr.get(2);

                    Animation anim1 = AnimationUtils.loadAnimation(getContext(), R.anim.splash3anim);
                    MainActivity.chImage1.startAnimation(anim1);
                    MainActivity.chImage2.startAnimation(anim1);
                    MainActivity.chImage3.startAnimation(anim1);
                    anim1.setAnimationListener(new AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation arg0) {
                            // TODO Auto-generated method stub
                            MainActivity.iv.setBackgroundResource(R.drawable.bg33);
                            MainActivity.chImage1.setImageResource(R.drawable.crush);
                            MainActivity.chImage2.setImageResource(R.drawable.crush);
                            MainActivity.chImage3.setImageResource(R.drawable.crush);
                        }

                        @Override
                        public void onAnimationRepeat(Animation arg0) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onAnimationEnd(Animation arg0) {
                            // TODO Auto-generated method stub
                            MainActivity.iv.setBackgroundResource(R.drawable.blankbg);
                            MainActivity.iv.setImageDrawable(null);
                            MainActivity.chImage1.setImageDrawable(null);
                            MainActivity.chImage2.setImageDrawable(null);
                            MainActivity.chImage3.setImageDrawable(null);
                        }
                    });

                    MainActivity.selectedFruitArr.removeAll(MainActivity.selectedFruitArr);
                    MainActivity.csong = MediaPlayer.create(cont, R.raw.crushs);
                    MainActivity.csong.start();
                    MainActivity.mcount++;
                    Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.myanim);
                    MainActivity.aiv.startAnimation(anim);
                    anim.setAnimationListener(new AnimationListener() {

                        @Override
                        public void onAnimationStart(Animation arg0) {
                            // TODO Auto-generated method stub
                            MainActivity.aiv.setImageResource(R.drawable.finalarrow);
                        }

                        @Override
                        public void onAnimationRepeat(Animation arg0) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onAnimationEnd(Animation arg0) {
                            // TODO Auto-generated method stub
                            MainActivity.aiv.setImageDrawable(null);
                            MainActivity.a = MainActivity.a + 20;
                            if (MainActivity.mcount == 1) {
                                MainActivity.buciv.setBackgroundResource(R.drawable.one);
                            }
                            if (MainActivity.mcount == 2) {
                                MainActivity.buciv.setBackgroundResource(R.drawable.two);
                            }
                            if (MainActivity.mcount == 3) {
                                MainActivity.buciv.setBackgroundResource(R.drawable.three);
                            }
                            if (MainActivity.mcount == 4) {
                                MainActivity.buciv.setBackgroundResource(R.drawable.four);
                            }
                            if (MainActivity.mcount == 5) {
                                MainActivity.buciv.setBackgroundResource(R.drawable.five);
                            }
                            if (MainActivity.mcount == 6) {
                                MainActivity.buciv.setBackgroundResource(R.drawable.six);
                            }
                            if (MainActivity.mcount == 7) {
                                MainActivity.buciv.setBackgroundResource(R.drawable.seven);
                            }
                            if (MainActivity.mcount == 8) {
                                MainActivity.buciv.setBackgroundResource(R.drawable.eight);
                                Toast t1 = Toast.makeText(cont, Constant.GAME_OVER, Toast.LENGTH_SHORT);
                                t1.show();
                                String dateTime = DateFormat.getDateTimeInstance().format(new Date());
                                Log.d("TryMash","datetime :- "+dateTime);
                                DatabaseHelper db = new DatabaseHelper(getContext());
                                Log.d("Insert: ", "Inserting ..");
                                db.addScoreData(new ScoreBoard(201, dateTime));
                                /*ScoreBoard scoreBoard = new ScoreBoard(MainActivity.a, DateFormat.getDateTimeInstance().format(new Date()));
                                scoreBoard.save();*/
                                //Intent nin = new Intent(cont, EndActivity.class);
                                Intent nin = new Intent(cont, ScoreBoardActivity.class);
                                nin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                nin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                cont.startActivity(nin);
                                Timer.shouldContinue = false;
                                try {
                                    new Timer().t.join();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                MainActivity.act.finish();
                            }
                            MainActivity.tv.setText(Constant.SCORE + MainActivity.a);

                        }
                    });

                }

                if (img.getTag() == Constant.APPLE) {
                    //setImageResource(R.drawable.smallappbck);
                    setColorFilter(Color.parseColor("#ADD8E6"));
                }

                if (img.getTag() == Constant.ORANGE) {
                    //setImageResource(R.drawable.orangebackground);
                    setColorFilter(Color.parseColor("#ADD8E6"));
                }
                if (img.getTag() == Constant.STRAWBERRY) {
                    //setImageResource(R.drawable.strawberrybackground);
                    setColorFilter(Color.parseColor("#ADD8E6"));
                }
                if (img.getTag() == Constant.GRAPES) {
                    //setImageResource(R.drawable.grapesbackground);
                    setColorFilter(Color.parseColor("#ADD8E6"));
                }
                if (img.getTag() == Constant.MANGO) {
                    //setImageResource(R.drawable.mangobackground);
                    setColorFilter(Color.parseColor("#ADD8E6"));
                }
                if (img.getTag() == Constant.PEAR) {
                    //setImageResource(R.drawable.pearback);
                    //MainActivity.iv.setImageResource(R.drawable.apple);
                    setColorFilter(Color.parseColor("#ADD8E6"));
                }
                if (img.getTag() == Constant.LICHEE) {
                    //setImageResource(R.drawable.licheeback);
                    setColorFilter(Color.parseColor("#ADD8E6"));
                }
                if (img.getTag() == Constant.ANAR) {
                    //setImageResource(R.drawable.anarback);
                    setColorFilter(Color.parseColor("#ADD8E6"));
                }
            }
        });
    }
}

public class Fruits {
    public static class Apple extends Common {
        public Apple(Context context) {
            super(context);
            setImageResource(R.drawable.apple);
            setTag(Constant.APPLE);
            // TODO Auto-generated constructor stub
        }
    }

    public static class Orange extends Common {

        public Orange(Context context) {
            super(context);
            setImageResource(R.drawable.orange);
            setTag(Constant.ORANGE);
            // TODO Auto-generated constructor stub
        }
    }

    public static class Strawberry extends Common {

        public Strawberry(Context context) {
            super(context);
            setImageResource(R.drawable.strawberry);
            setTag(Constant.STRAWBERRY);
            // TODO Auto-generated constructor stub
        }
    }

    public static class Grapes extends Common {
        public Grapes(Context context) {
            super(context);
            setImageResource(R.drawable.grapes);
            setTag(Constant.GRAPES);
            // TODO Auto-generated constructor stub
        }
    }

    public static class Mango extends Common {

        public Mango(Context context) {
            super(context);
            setImageResource(R.drawable.shalu);
            setTag(Constant.MANGO);
            // TODO Auto-generated constructor stub
        }
    }

    public static class Anar extends Common {

        public Anar(Context context) {
            super(context);
            setImageResource(R.drawable.anar);
            setTag(Constant.ANAR);
            // TODO Auto-generated constructor stub
        }
    }

    public static class Lichee extends Common {

        public Lichee(Context context) {
            super(context);
            setImageResource(R.drawable.lichee);
            setTag(Constant.LICHEE);
            // TODO Auto-generated constructor stub
        }
    }

    public static class Pear extends Common {

        public Pear(Context context) {
            super(context);
            setImageResource(R.drawable.pear);
            setTag(Constant.PEAR);
            // TODO Auto-generated constructor stub
        }
    }
}




