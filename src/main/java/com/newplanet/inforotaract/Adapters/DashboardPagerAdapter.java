package com.newplanet.inforotaract.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.newplanet.inforotaract.AboutPage;
import com.newplanet.inforotaract.NewsPage;

/**
 * Created on 12/14/2015
 * By : $(USER)<suchan211@gmail.com>
 */
public class DashboardPagerAdapter extends FragmentPagerAdapter
{

    final int PAGE_COUNT = 4;
    private String tabTitles[] = {"About", "News", "Events", "Gallery"};

    public DashboardPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        if (position == 0)
            return AboutPage.newInstance(); // pass the class of activity where we are supposed to navigate to on click of gridView item
        else if (position == 1)
            return NewsPage.newInstance();
        else
        {
            return AboutPage.newInstance();
        }

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
