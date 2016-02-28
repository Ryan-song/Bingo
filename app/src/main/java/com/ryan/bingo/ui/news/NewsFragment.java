package com.ryan.bingo.ui.news;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ryan.bingo.R;
import com.ryan.bingo.modle.NewsBean;
import com.ryan.bingo.support.Utils;
import com.ryan.bingo.support.adapter.NewsAdapter;
import com.ryan.bingo.support.sax.SAXNewsParse;
import com.yalantis.phoenix.PullToRefreshView;

import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by air on 16/2/28.
 */
public class NewsFragment extends Fragment{
    private View parentView;
    private PullToRefreshView refreshView;
    private RecyclerView recyclerView;
    private RequestQueue queue;
    private List<NewsBean> items = new ArrayList<>();
    private NewsAdapter adapter = new NewsAdapter(items);
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = View.inflate(getContext(), R.layout.layout_news,null);
        initData();
        return parentView;
    }

    void initData() {
        refreshView = (PullToRefreshView) parentView.findViewById(R.id.pull_to_refresh);
        recyclerView = (RecyclerView) parentView.findViewById(R.id.recyclerView);
        final String url = getArguments().getString("url");
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        loadNewsFromNet(url);

        refreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNewsFromNet(url);
            }
        });
    }

    private void loadNewsFromNet(String url) {
        queue = Volley.newRequestQueue(getContext());
        final StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(String response) {
                InputStream is =
                        new ByteArrayInputStream(response.getBytes(StandardCharsets.ISO_8859_1));

                try {
                    items.clear();
                    items.addAll(SAXNewsParse.parse(is));
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
                refreshView.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.showToast("网络异常 刷新失败");
                refreshView.setRefreshing(false);
            }
        });
        request.setShouldCache(false);
        queue.add(request);
    }


    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            adapter.notifyDataSetChanged();
            return false;
        }
    });


}
