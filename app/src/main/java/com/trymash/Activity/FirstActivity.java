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
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.trymash.R;
import com.trymash.Utils.MyBounceInterpolator;


public class FirstActivity extends Activity {
    private Button sg=null;
    private Animation myAnimHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        sg=(Button) findViewById(R.id.sg);
        ImageView imgTry = (ImageView) findViewById(R.id.iv_try);
        ImageView imgMash = (ImageView) findViewById(R.id.iv_mash);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        myAnimHang = AnimationUtils.loadAnimation(this, R.anim.hanging_effect);
        imgTry.startAnimation(myAnimHang);
        imgMash.startAnimation(myAnimHang);
        animateButton();
        //create sub floating buttons
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(80,80);
        itemBuilder.setLayoutParams(params);
        itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_circle));
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

        sg.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent in2=new Intent(FirstActivity.this, MainActivity.class);
                startActivity(in2);
            }
        });
    }
    private void animateButton() {
        myAnimHang = AnimationUtils.loadAnimation(this, R.anim.bounce);
        double animationDuration = 2.00 * 1000;
        myAnimHang.setDuration((long)animationDuration);
        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnimHang.setInterpolator(interpolator);
        sg.startAnimation(myAnimHang);
        // Run button animation again after it finished
        myAnimHang.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation arg0) {}
            @Override
            public void onAnimationRepeat(Animation arg0) {}
            @Override
            public void onAnimationEnd(Animation arg0) {
                animateButton();
            }
        });
    }

}
