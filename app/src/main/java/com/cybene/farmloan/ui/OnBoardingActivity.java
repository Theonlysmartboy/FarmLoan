package com.cybene.farmloan.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.cybene.farmloan.R;
import com.cybene.farmloan.utils.adapter.WelcomeViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

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
        setContentView(R.layout.activity_on_boarding);
    }
}