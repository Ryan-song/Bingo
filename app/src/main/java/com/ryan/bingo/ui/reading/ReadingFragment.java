package com.ryan.bingo.ui.reading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.ryan.bingo.support.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by air on 16/3/5.
 */
public class ReadingFragment extends Fragment {
    private View parentView;
    private TextView textView;

    private List<BookBean> items = new ArrayList<>();
    private RequestQueue queue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = View.inflate(getContext(), R.layout.layout_reading_list, null);
        textView = (TextView) parentView.findViewById(R.id.text);
//        textView.setText(getArguments().getInt("pos"));
        loadNewsFromNet(getArguments().getInt("pos"));
        return parentView;
    }

    private void loadNewsFromNet(int pos) {
        queue = Volley.newRequestQueue(getContext());
        String[] tags = ReadingApi.getTags(ReadingApi.getApiTag(pos));
        for (int i = 0; i < 5; i++) {
            String url = ReadingApi.searchByTag + tags[i] + "&count=100";
            Utils.DLog(url);
            StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    Gson gson = new Gson();
                    items.add(gson.fromJson(s, BookBean.class));
                    handler.sendEmptyMessage(0);
                    // refreshView.setRefreshing(false);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Utils.showToast("网络异常 刷新失败");
                    // refreshView.setRefreshing(false);
                }
            });
            request.setShouldCache(false);
            queue.add(request);
        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            //   adapter.notifyDataSetChanged();
            //  Utils.showToast(items.getCount()+"
            textView.setText(items.toString());
            return false;
        }
    });

}
