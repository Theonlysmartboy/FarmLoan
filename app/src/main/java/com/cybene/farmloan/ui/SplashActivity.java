package com.cybene.farmloan.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.cybene.farmloan.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // make the activity on full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        // hide the action bar
        getSupportActionBar().hide();
        splash();
    }
    private void splash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //User has logged in
                //Show the HomeActivity
                Intent home = new Intent(SplashActivity.this, SignInActivity.class);
                startActivity(home);
                //overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_slide_out_left);
                finish();
            }
        },5000);

    }
}