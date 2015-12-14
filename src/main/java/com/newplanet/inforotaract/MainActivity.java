package com.newplanet.inforotaract;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.astuetz.PagerSlidingTabStrip;
import com.newplanet.inforotaract.Adapters.DashboardPagerAdapter;
import com.newplanet.inforotaract.Utils.App;


public class MainActivity extends AppCompatActivity
{
    private ViewPager viewPager;
    private PagerSlidingTabStrip tabsStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        App.Intialize(this);
        App.c = this;

        //GET VIEW PAGER
        setupViewPager();

        //SETUP THE TOOLBAR
        setupToolbar();

        //POPULATE THE PAGE SLIDER TAB STRIP
        populateTabStrip();
    }

    private void setupViewPager()
    {
        viewPager = (ViewPager) findViewById(R.id.vpDashboard);
        viewPager.setAdapter(new DashboardPagerAdapter(getSupportFragmentManager()));
    }

    private void populateTabStrip()
    {
        tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabsDashboard);
        tabsStrip.setTextColorResource(R.color.white);
        tabsStrip.setViewPager(viewPager);
    }

    private void setupToolbar()
    {
        final Toolbar toolbar= (Toolbar) findViewById(R.id.customToolbar);
        toolbar.setTitle(R.string.app_title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

    }
}
