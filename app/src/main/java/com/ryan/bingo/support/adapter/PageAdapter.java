package com.ryan.bingo.support.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by air on 16/2/27.
 */
public abstract class PageAdapter extends FragmentStatePagerAdapter{

    private String[] TITLES;

    public PageAdapter(FragmentManager fm,String[] titles) {
        super(fm);
        this.TITLES = titles;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
