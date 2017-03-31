package com.jinwoo.android.goriversion1;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jinwoo.android.goriversion1.Test.CustomScrollView;
import com.jinwoo.android.goriversion1.Test.CustomView;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "RAPSTAR";
    private static final String[] LIST_MENU = { "탈잉 소개", "MY PAGE", "수업신청서", "LOG OUT", "튜터 등록" };

    OneFragment one;                        // 프래그먼트
    TwoFragment two;
    ThreeFragment three;
    FourFragment four;

    FrameLayout frameLayout;

    ImageView imageView1;                   // 이미지뷰
    RadiusImageView imageView2;

    CheckBox checkbox_wishList;             // 체크박스

    Button btnDrawerMenu;                   // 버튼 드로어
    RelativeLayout drawer_relativeLayout;

    TextView subTitle, txtTitle;
    TabLayout tab, subTab;
    CustomScrollView scrollView;

    float txtTitle_y_position = 0;
    float tab_y_position = 0;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        txtTitle_y_position = txtTitle.getTop();
        tab_y_position = tab.getTop();
        scrollView.setTxtTitleForY(txtTitle_y_position);
        scrollView.setTabForY(tab_y_position);
        super.onWindowFocusChanged(hasFocus);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Drawer 만들기
        ListView listView = (ListView)findViewById(R.id.drawer_listView);
        ArrayAdapter listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, LIST_MENU);
        listView.setAdapter(listAdapter);
        drawer_relativeLayout = (RelativeLayout)findViewById(R.id.drawer_relativelayout);
        btnDrawerMenu = (Button)findViewById(R.id.btnDrawerMenu);
        final DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        btnDrawerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawer_relativeLayout);
            }
        });


        // 체크 박스
        checkbox_wishList = (CheckBox)findViewById(R.id.checkbox_wishList);
        checkbox_wishList.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showMessage(isChecked);
            }
        });

        // 텍스트뷰
        subTitle = (TextView)findViewById(R.id.subTitle);
        txtTitle = (TextView)findViewById(R.id.txtTitle);


        // 프래그먼트 초기화
        one = new OneFragment();
        two = new TwoFragment();
        three = new ThreeFragment();
        four = new FourFragment();

        // 탭 레이아웃 & 뷰페이저 초기화
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        tab = (TabLayout)findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("수업 소개"));
        tab.addTab(tab.newTab().setText("장소/시간"));
        tab.addTab(tab.newTab().setText("리뷰"));
        tab.addTab(tab.newTab().setText("문의"));
        subTab = (TabLayout)findViewById(R.id.subTab);
        subTab.addTab(subTab.newTab().setText("수업 소개"));
        subTab.addTab(subTab.newTab().setText("장소/시간"));
        subTab.addTab(subTab.newTab().setText("리뷰"));
        subTab.addTab(subTab.newTab().setText("문의"));

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        //tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(subTab));
        subTab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        //스크롤 뷰
        scrollView = (CustomScrollView)findViewById(R.id.scrollView);

        scrollView.setOnAppearListener(new CustomScrollView.OnAppearListener() {
            @Override
            public void onAppearReached() {
                subTitle.setVisibility(View.VISIBLE);
            }
        });
        scrollView.setOnDestroyListener(new CustomScrollView.OnDestroyListener(){
            @Override
            public void onDestroyReached() {
                subTitle.setVisibility(View.GONE);
            }
        });
        scrollView.setTabAppearListener(new CustomScrollView.OnTabAppearListener(){
            @Override
            public void onTabAppearReached() {
                subTab.setVisibility(View.VISIBLE);
            }
        });
        scrollView.setTabDestroyListener(new CustomScrollView.OnTabDestroyListener(){
            @Override
            public void onTabDestroyReached(){
                subTab.setVisibility(View.GONE);
            }
        });

        // 커스텀 뷰를 담을 프레임아웃
        CustomView view = new CustomView(this);
        frameLayout = (FrameLayout)findViewById(R.id.framelayout);
        frameLayout.addView(view);

    }

    // 위시리스트 결과를 보여주는 대화상자
    public void showMessage(boolean isChecked){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(isChecked) {
            builder.setTitle("위시 리스트 등록 여부");
            builder.setMessage("위리리스트에 등록 되었습니다.");
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO : 위시 리스트에 등록 하는 연산 추가하기
                }
            });
        } else  {
            builder.setMessage("위리리스트에 등록이 취소 되었습니다.");
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }



    class PagerAdapter extends FragmentStatePagerAdapter {
        final int PAGE_COUNT = 4;

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

    }



}


