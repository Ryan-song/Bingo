package com.ryan.bingo.ui.reading;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ryan.bingo.api.ReadingApi;
import com.ryan.bingo.support.adapter.PageAdapter;
import com.ryan.bingo.ui.AbsTopNavigationFragment;

/**
 * Created by air on 16/3/5.
 */
public class BaseReadingFragment extends AbsTopNavigationFragment {

    private PageAdapter pageAdapter;

    @Override
    protected PageAdapter initPageAdapter() {

        pageAdapter = new PageAdapter(getFragmentManager(),ReadingApi.Tag_Titles) {
            @Override
            public Fragment getItem(int position) {
                ReadingFragment readingFragment = new ReadingFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("pos",position);
                readingFragment.setArguments(bundle);
                return readingFragment;
            }
        };
        return pageAdapter;
    }
}
