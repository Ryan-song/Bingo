package com.ryan.bingo.ui.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.ryan.bingo.modle.Entity;
import com.ryan.bingo.support.Utils;
import com.ryan.bingo.support.adapter.PageAdapter;
import com.ryan.bingo.ui.AbsTopNavigationFragment;

import java.util.Arrays;

/**
 * Created by air on 16/2/28.
 */
public class BaseNewsFragment extends AbsTopNavigationFragment{
    private PageAdapter pageAdapter;
    private String[] name ;
    private String[] url ;
    @Override
    protected PageAdapter initPageAdapter() {
        Log.d("F_BaseNewsFragment","initPageAdapter()");
        name = Entity.getNewsTitle();
        url = Entity.getNewsUrl();
        Log.d("F_BaseNewsFragment", "name From Entity: "+Arrays.toString(name));
        Log.d("F_BaseNewsFragment","url From Entity : "+Arrays.toString(url));

        pageAdapter = new PageAdapter(getFragmentManager(),name,url) {
            @Override
            public Fragment getItem(int position) {
                Log.d("F_BaseNewsFragment","new pageAdapter() + getItem ["+position+"]");
                Utils.DLog("news_api:"+name.length+" "+url.length);
                NewsFragment fragment = new NewsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("url",url[position]);
                Log.d("F_BaseNewsFragment","Bundle_url:   "+url[position].toString());
                fragment.setArguments(bundle);
                return fragment;
            }
        };
        return pageAdapter;
    }
}















