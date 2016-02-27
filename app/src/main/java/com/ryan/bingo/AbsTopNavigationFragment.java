package com.ryan.bingo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

/**
 * Created by air on 16/2/27.
 */
public abstract class AbsTopNavigationFragment extends Fragment{
    protected View parentView;
    private ViewPager viewPager;
    private PageAdapter pageAdapter;
    private SmartTabLayout smartTabLayout;
    protected abstract PageAdapter initPageAdapter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = View.inflate(getContext(),R.layout.layout_top_navigation,null);
        viewPager = (ViewPager) parentView.findViewById(R.id.inner_viewpager);
        smartTabLayout = (SmartTabLayout) parentView.findViewById(R.id.smarttab);
        pageAdapter = initPageAdapter();
        viewPager.setAdapter(pageAdapter);
        smartTabLayout.setViewPager(viewPager);
        return parentView;
    }
}
