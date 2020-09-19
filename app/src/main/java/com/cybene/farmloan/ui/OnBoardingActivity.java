package com.cybene.farmloan.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.cybene.farmloan.R;
import com.cybene.farmloan.utils.adapter.WelcomeViewPagerAdapter;
import com.cybene.farmloan.utils.items.WelcomeItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AppCompatActivity {
    private ViewPager viewPager;
    WelcomeViewPagerAdapter pagerAdapter;
    TabLayout layout;
    Button next, btnGetStarted;
    int position = 0 ;
    Animation btnAnim ;
    TextView skip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // make the activity on full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // when this activity is about to be launch we need to check if its openened before or not
        if (restorePrefData()) {
            Intent launcherActivity = new Intent(getApplicationContext(), SplashActivity.class );
            startActivity(launcherActivity);
            finish();
        }
        setContentView(R.layout.activity_on_boarding);
        // hide the action bar
        getSupportActionBar().hide();
        // init views
        viewPager = findViewById(R.id.wPager);
        layout = findViewById(R.id.wTabs);
        btnGetStarted = findViewById(R.id.btn_get_started);
        skip = findViewById(R.id.tv_skip);
        next = findViewById(R.id.btnWNext);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
        // fill list screen
        final List<WelcomeItem> list = new ArrayList<>();
        list.add(new WelcomeItem("Developer","Otema Technologies\n" +
                "The Home Of digital solutions and innovations", R.drawable.logo));
        list.add(new WelcomeItem("Developer","Otema Technologies\n" +
                "The Home Of digital solutions and innovations", R.drawable.logo1));
        list.add(new WelcomeItem("Developer","Otema Technologies\n" +
                "The Home Of digital solutions and innovations", R.drawable.logo));
        // setup viewpager
        pagerAdapter = new WelcomeViewPagerAdapter(this,list);
        viewPager.setAdapter(pagerAdapter);
        // setup tablayout with viewpager
        layout.setupWithViewPager(viewPager);
        // next button click Listner
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = viewPager.getCurrentItem();
                if (position < list.size()) {
                    position++;
                    viewPager.setCurrentItem(position);
                }
                if (position == list.size()-1) { // when we reach to the last screen
                    loaddLastScreen();
                }
            }
        });
        // tablayout add change listener
        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == list.size()-1) {
                    loaddLastScreen();
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // Get Started button click listener

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open main activity
                Intent loginActivity = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(loginActivity);
                //SAVE a boolean value to show that user has already seen the welcome screen
                savePrefsData();
                finish();
            }
        });
        // skip button click listener
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(list.size());
            }
        });

    }
    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend",false);
        return  isIntroActivityOpnendBefore;
    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.apply();
    }

    // show the GETSTARTED Button and hide the indicator and the next button
    private void loaddLastScreen() {
        next.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        skip.setVisibility(View.INVISIBLE);
        layout.setVisibility(View.INVISIBLE);
        // setup animation
        btnGetStarted.setAnimation(btnAnim);
    }
}