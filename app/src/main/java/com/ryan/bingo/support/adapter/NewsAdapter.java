package com.ryan.bingo.support.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ryan.bingo.R;
import com.ryan.bingo.modle.NewsBean;
import com.ryan.bingo.ui.news.NewsDetailsActivity;

import java.util.List;

/**
 * Created by air on 16/2/28.
 */

/**
 *
 *  News的recyclerView的adapter
 *  item布局: R.layout.item_news
 *
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<NewsBean> items;
    private Context mContext ;

    public NewsAdapter(Context context,List<NewsBean> items) {
        this.items = items;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Log.d("F_NewsAdapter","onCreateViewHolder()");
        View view = View.inflate(parent.getContext(),R.layout.item_news,null);
        final ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("F_NewsAdapter","OnClickListener");
                Intent intent = new Intent(mContext, NewsDetailsActivity.class);
                intent.putExtra("url",getItem(vh.position).getLink());
                mContext.startActivity(intent);
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        Log.d("F_NewsAdapter","onBindViewHolder()");
        NewsBean newsBean = getItem(position);
        holder.description.setText(newsBean.getDescription());
        holder.title.setText(newsBean.getTitle());
        holder.date.setText(newsBean.getPubTime());
        holder.position = position;
    }


    @Override
    public int getItemCount() {
//        Log.d("F_NewsAdapter","getItemCount()");
        return items.size();
    }

    private NewsBean getItem(int position) {
//        Log.d("F_NewsAdapter","getItem()");
        return items.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView date;
        int position;
        ViewHolder(View itemView) {
            super(itemView);
            Log.d("F_NewsAdapter","NewsAdapter--ViewHolder");
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
