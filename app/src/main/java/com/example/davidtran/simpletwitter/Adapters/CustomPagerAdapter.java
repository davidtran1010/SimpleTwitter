package com.example.davidtran.simpletwitter.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.PagerAdapter;

import com.example.davidtran.simpletwitter.Fragments.HomeTimelineFragment;
import com.example.davidtran.simpletwitter.Fragments.SearchTweetFragment;

import java.util.List;

/**
 * Created by davidtran on 7/5/17.
 */

public class CustomPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragmentList;
    public CustomPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }
    @Override
    public int getItemPosition(Object object){
        return PagerAdapter.POSITION_NONE;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return fragmentList.get(0);
            case 1: return fragmentList.get(1);
            default: return  new Fragment();
        }

    }

    @Override
    public int getCount() {
        return 4;
    }
}
;