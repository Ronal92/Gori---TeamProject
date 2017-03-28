package com.jinwoo.android.goriversion1;

<<<<<<< HEAD
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
=======
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
>>>>>>> 9773c8d02c2309535d9f14fb02a6cfb71bd94e24
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jinwoo.android.goriversion1.Test.CustomView;
=======
import android.widget.ImageView;
>>>>>>> 9773c8d02c2309535d9f14fb02a6cfb71bd94e24


public class MainActivity extends AppCompatActivity {

<<<<<<< HEAD
    OneFragment one;                        // 프래그먼트
=======

    OneFragment one;
>>>>>>> 9773c8d02c2309535d9f14fb02a6cfb71bd94e24
    TwoFragment two;
    ThreeFragment three;
    FourFragment four;

<<<<<<< HEAD
    FrameLayout frameLayout;

    ImageView imageView1;                   // 이미지뷰
    RadiusImageView imageView2;

    TextView txtMainTitle;


=======
    ImageView imageView1, imageView2;
>>>>>>> 9773c8d02c2309535d9f14fb02a6cfb71bd94e24

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        txtMainTitle = (TextView)findViewById(R.id.txtMainTitle);

        ScrollView scrollView = (ScrollView)findViewById(R.id.scrollView);



        // 프래그먼트 초기화
=======
        // 프래그먼트 초기화

>>>>>>> 9773c8d02c2309535d9f14fb02a6cfb71bd94e24
        one = new OneFragment();
        two = new TwoFragment();
        three = new ThreeFragment();
        four = new FourFragment();

<<<<<<< HEAD
        // 탭 레이아웃 / 뷰페이저 초기화
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        TabLayout tab = (TabLayout)findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("수업 소개"));
        tab.addTab(tab.newTab().setText("장소/시간"));
        tab.addTab(tab.newTab().setText("리뷰"));
        tab.addTab(tab.newTab().setText("문의"));

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));




        // 커스텀 뷰를 담을 프레임아웃
        CustomView view = new CustomView(this);
        frameLayout = (FrameLayout)findViewById(R.id.framelayout);
        frameLayout.addView(view);

    }





    class PagerAdapter extends FragmentStatePagerAdapter {
        final int PAGE_COUNT = 4;
=======
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        GradientDrawable drawable = (GradientDrawable)this.getDrawable(R.drawable.rounding);
        imageView2.setBackground(drawable);


        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        PagerTabStrip pagerTab = (PagerTabStrip)findViewById(R.id.pagerTab);


        pagerTab.setTabIndicatorColor(Color.RED);
        pagerTab.setTextColor(Color.RED);



         PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);


    }

    private class PageListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }



    class PagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 4;
        private String tabTitle[] = {"수업 소개", "장소/시간", "리뷰", "문의"};

>>>>>>> 9773c8d02c2309535d9f14fb02a6cfb71bd94e24

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0 : fragment = one;
                    break;
                case 1 : fragment = two;
                    break;
                case 2 : fragment = three;
                    break;
                case 3 : fragment = four;
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

<<<<<<< HEAD
=======
        @Override
        public CharSequence getPageTitle(int position){
            return tabTitle[position];
        }
>>>>>>> 9773c8d02c2309535d9f14fb02a6cfb71bd94e24
    }



}


