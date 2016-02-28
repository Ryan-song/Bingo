package com.ryan.bingo.ui.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ryan.bingo.R;
import com.ryan.bingo.support.Utils;
import com.ryan.bingo.support.adapter.PageAdapter;
import com.ryan.bingo.ui.AbsTopNavigationFragment;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.InputStream;

/**
 * Created by air on 16/2/28.
 */
public class BaseNewsFragment extends AbsTopNavigationFragment{
    private PageAdapter pageAdapter;
    private String[] name;
    private String[] url;
    @Override
    protected PageAdapter initPageAdapter() {
        InputStream is = Utils.readFileFromRaw(R.raw.news_api);
        Document document = Utils.getDocumentByIS(is);
        NodeList urlList = document.getElementsByTagName("url");
        NodeList nameList = document.getElementsByTagName("name");
        int nodeLength = urlList.getLength();
        name = new String[nodeLength];
        url = new String[nodeLength];

        for (int i = 0;i<nodeLength;i++){
            url[i] = urlList.item(i).getTextContent();
            name[i] = nameList.item(i).getTextContent();
        }
        pageAdapter = new PageAdapter(getFragmentManager(),name) {
            @Override
            public Fragment getItem(int position) {
                NewsFragment fragment = new NewsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("url",url[position]);
                bundle.putString("name",name[position]);
                fragment.setArguments(bundle);
                return null;
            }
        };
        return pageAdapter;
    }
}















