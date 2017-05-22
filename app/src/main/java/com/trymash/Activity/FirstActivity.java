package com.trymash.Activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
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
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.trymash.R;
import com.trymash.Utils.MyBounceInterpolator;

import org.json.JSONException;
import org.json.JSONObject;


public class FirstActivity extends Activity {
    private Button sg;
    //private ImageView playLabel;
    private Animation myAnimHang;
   // private TextView fLabel;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_first);
        sg=(Button) findViewById(R.id.sg);
        //playLabel= (ImageView) findViewById(R.id.iv_play);
        //fLabel= (TextView) findViewById(R.id.tv_facebook);
        ImageView imgTry = (ImageView) findViewById(R.id.iv_try);
        ImageView imgMash = (ImageView) findViewById(R.id.iv_mash);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //facebook_id=f_name= m_name= l_name= gender= profile_image= full_name= email_id="";
                if(AccessToken.getCurrentAccessToken() != null){
                    Log.e("here", "access not null");
                    RequestData();
                    Profile profile = Profile.getCurrentProfile();
                    if (profile != null) {
                        Log.e("test", "not null");
                        /*facebook_id=profile.getId();
                        Log.e("facebook_id", facebook_id);
                        f_name=profile.getFirstName();
                        Log.e("f_name", f_name);
                        m_name=profile.getMiddleName();
                        Log.e("m_name", m_name);
                        l_name=profile.getLastName();
                        Log.e("l_name", l_name);
                        full_name=profile.getName();
                        Log.e("full_name", full_name);
                        profile_image=profile.getProfilePictureUri(400, 400).toString();
                        Log.e("profile_image", profile_image);*/
                        //info.setText("Full Name :- "+full_name);
                    }
                    //share.setVisibility(View.VISIBLE);
                    //details.setVisibility(View.VISIBLE);
                }
                /*info.setText(
                        "User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                );*/
            }

            @Override
            public void onCancel() {

                //info.setText("Login attempt canceled.");
                Toast.makeText(FirstActivity.this, "Login attempt canceled.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException e) {

                //info.setText("Login attempt failed.");
                Toast.makeText(FirstActivity.this, "Login attempt failed.", Toast.LENGTH_SHORT).show();

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //set font to facebook label
        String fontPath = "fonts/GoodDog.otf";
        //String fontPath2 = "Georgia-Regular-font.ttf";
        //Typeface m_typeFace = Typeface.createFromAsset(getAssets(), fontPath);
        //fLabel.setTypeface(m_typeFace);
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
        //playLabel.startAnimation(myAnimHang);
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
    public void RequestData(){
        Log.e("here", "in request data fn ");
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.e("here", "on graph completed");
                JSONObject json = response.getJSONObject();
                //System.out.println("Json data :"+json);
                Log.e("here", "JSON data :- "+json.toString());
                try {
                    if(json != null){
                        //String text = "<b>Name :</b> "+json.getString("name")+"<br><br><b>Email :</b> "+json.getString("email")+"<br><br><b>Profile link :</b> "+json.getString("link");
                        //details_txt.setText(Html.fromHtml(text));
                        //profile.setProfileId(json.getString("id"));
                        //info.setText(json.getString("id")+"\n"+json.getString("name"));
                        Toast.makeText(FirstActivity.this, json.getString("id")+"\n"+json.getString("name"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
