package com.ryan.bingo.support.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ryan.bingo.R;
import com.ryan.bingo.modle.NewsBean;

import java.util.List;

/**
 * Created by air on 16/2/28.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<NewsBean> items;

    public NewsAdapter(List<NewsBean> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_news, null);
        RecyclerView.ViewHolder vh = new ViewHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsBean newsBean = getItem(position);
        holder.description.setText(newsBean.getDescription());
        holder.title.setText(newsBean.getTitle());
        holder.date.setText(newsBean.getPubTime());
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    private NewsBean getItem(int position) {
        return items.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
