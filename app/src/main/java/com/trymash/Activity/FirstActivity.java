package com.trymash.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.trymash.R;
import com.trymash.Utils.MyBounceInterpolator;


public class FirstActivity extends Activity {
    static Button sg=null;
    private ImageView hang;
    /* static Button hs=null;
    static Button eg=null;
    static Button ss=null;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        sg=(Button) findViewById(R.id.sg);
        hang = (ImageView) findViewById(R.id.hang);
        final Animation myAnimHang = AnimationUtils.loadAnimation(this, R.anim.hanging_effect);
        hang.startAnimation(myAnimHang);
        animateButton();
        /*hs=(Button) findViewById(R.id.hs);
        eg=(Button) findViewById(R.id.eg);
        ss=(Button) findViewById(R.id.ss);
*/
        //here======================
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
       // LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(60, 60);
// existing height is ok as is, no need to edit it

        ImageView item1 = new ImageView(this);

        item1.setImageResource(R.drawable.ic_help);
       // item1.setLayoutParams(layoutParams);

        ImageView item2 = new ImageView(this);
        item2.setImageResource(R.drawable.ic_score);
       // item2.setLayoutParams(layoutParams);


        ImageView item3 = new ImageView(this);
        item3.setImageResource(R.drawable.ic_volume);
        //item3.setLayoutParams(layoutParams);


        SubActionButton button1 = itemBuilder.setContentView(item1).build();
        SubActionButton button2 = itemBuilder.setContentView(item2).build();
        SubActionButton button3 = itemBuilder.setContentView(item3).build();
        //attach the sub buttons
        new FloatingActionMenu.Builder(this)
                /*.setStartAngle(-45)
                .setEndAngle(-135)*/
                .setRadius(getResources().getDimensionPixelSize(R.dimen.radius_medium))
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .attachTo(fab)
                .build();


        //here======================



        sg.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent in2=new Intent(FirstActivity.this, MainActivity.class);
                startActivity(in2);
            }
        });
        /*hs.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent in=new Intent(FirstActivity.this, HighScoreActivity.class);
                startActivity(in);
            }
        });*/
       /* ss.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent in1=new Intent(FirstActivity.this, SoundSettingsActivity.class);
                startActivity(in1);
            }
        });
        eg.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });*/
    }
    private void animateButton() {
        //********************-----add animation on button
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        //additional code**********
        double animationDuration = 2.00 * 1000;
        myAnim.setDuration((long)animationDuration);
        //*************
        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        sg.startAnimation(myAnim);

        // Run button animation again after it finished
        myAnim.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation arg0) {}

            @Override
            public void onAnimationRepeat(Animation arg0) {}

            @Override
            public void onAnimationEnd(Animation arg0) {
                animateButton();
            }
        });
        //********************-----add animation on button
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first, menu);
        return true;
    }

}
