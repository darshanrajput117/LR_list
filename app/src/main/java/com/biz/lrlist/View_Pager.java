package com.biz.lrlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class View_Pager extends AppCompatActivity{

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager view_pager;
    @BindView(R.id.vp_toolbar)
    Toolbar toolbar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__pager);

        init();
        setupViewPagerAndTabs();
    }

    private void init() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar1);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setupViewPagerAndTabs() {
        view_pager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(view_pager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0)
            super.onBackPressed();
        else
            getSupportFragmentManager().popBackStack();
    }
}