package com.ryan.bingo.support.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.Arrays;

/**
 * Created by air on 16/2/27.
 */
public abstract class PageAdapter extends FragmentStatePagerAdapter{

    private String[] TITLES;
    private String[] TestURL;

    public PageAdapter(FragmentManager fm,String[] titles,String[] TestURL) {
        super(fm);
        this.TITLES = titles;
        this.TestURL = TestURL;
//        Log.d("F_PageAdapter", "PageAdapter()");
        Log.d("F_PageAdapter", "PageAdapter的构造方法");
        Log.d("F_PageAdapter", "构造参数String[] titles:"+Arrays.toString(this.TITLES));
    }

    @Override
    public int getCount() {
//        Log.d("F_PageAdapter", "getCount()");
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        Log.d("F_PageAdapter", "getPageTitle()");
        Log.d("F_PageAdapter", "getPageTitle [ "+position+" ]");
        Log.d("F_PageAdapter", "getPageTitle [ "+TITLES[position].toString()+" ]");
        Log.d("F_PageAdapter", "getPageURL [ "+TestURL[position].toString()+" ]");
        return TITLES[position];
    }
}
