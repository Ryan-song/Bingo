package com.ryan.bingo.ui.reading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.ryan.bingo.R;
import com.ryan.bingo.api.ReadingApi;
import com.ryan.bingo.modle.reading.BookBean;
import com.ryan.bingo.modle.reading.ReadingBean;
import com.ryan.bingo.support.Utils;
import com.ryan.bingo.support.adapter.DividerItemDecoration;
import com.ryan.bingo.support.adapter.ReadingAdapter;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by air on 16/3/5.
 */
public class ReadingFragment extends Fragment {
    private View parentView;
    private PullToRefreshView refreshView;
    private RecyclerView recyclerView;
    //    private TextView textView;
    private List<ReadingBean> readingData = new ArrayList<>();
    private List<BookBean> items = new ArrayList<>();
    private RequestQueue queue;
    private RecyclerView.LayoutManager mLayoutManager;
    private ReadingAdapter adapter;
    private int pos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = View.inflate(getContext(), R.layout.layout_reading_list, null);
//        textView = (TextView) parentView.findViewById(R.id.text);
//        textView.setText(getArguments().getInt("pos"));
//        loadNewsFromNet(getArguments().getInt("pos"));
        initData();
        return parentView;
    }

    private void initData() {
        pos = getArguments().getInt("pos");
        recyclerView = (RecyclerView) parentView.findViewById(R.id.recyclerView);
        refreshView = (PullToRefreshView) parentView.findViewById(R.id.pull_to_refresh);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST));
        adapter = new ReadingAdapter(items, getContext());
        recyclerView.setAdapter(adapter);
        refreshView.setRefreshing(true);
        loadNewsFromNet(pos);
        refreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNewsFromNet(pos);
            }
        });
    }

    private void loadNewsFromNet(int pos) {
        queue = Volley.newRequestQueue(getContext());
        String[] tags = ReadingApi.getTags(ReadingApi.getApiTag(pos));
        for (int i = 0; i < ReadingApi.TAG_LEN; i++) {
            String url = ReadingApi.searchByTag + tags[i];
            Utils.DLog(url);
            StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    Gson gson = new Gson();
                    BookBean[] bookBeans = gson.fromJson(s, ReadingBean.class).getBooks();
                    for (BookBean bookBean : bookBeans) {
                        items.add(bookBean);
                    }
                    handler.sendEmptyMessage(0);
                    refreshView.setRefreshing(false);
                    // refreshView.setRefreshing(false);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Utils.showToast("网络异常 刷新失败");
                    refreshView.setRefreshing(false);
                }
            });
            request.setShouldCache(false);
            queue.add(request);
        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
               adapter.notifyDataSetChanged();
            //  Utils.showToast(items.getCount()+"
//            textView.setText(items.toString());
            return false;
        }
    });

}
