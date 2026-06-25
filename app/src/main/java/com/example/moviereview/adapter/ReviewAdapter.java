package com.example.moviereview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.moviereview.R;
import com.example.moviereview.model.Review;
import java.util.List;

public class ReviewAdapter extends BaseAdapter {

    private Context context;
    private List<Review> reviewList;
    private int currentUserId;
    private OnDeleteListener deleteListener;

    public interface OnDeleteListener {
        void onDelete(int reviewId);
    }

    public ReviewAdapter(Context context, List<Review> reviewList, int currentUserId, OnDeleteListener listener) {
        this.context = context;
        this.reviewList = reviewList;
        this.currentUserId = currentUserId;
        this.deleteListener = listener;
    }

    @Override
    public int getCount() { return reviewList.size(); }

    @Override
    public Object getItem(int position) { return reviewList.get(position); }

    @Override
    public long getItemId(int position) { return reviewList.get(position).getId(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_review, parent, false);
            holder = new ViewHolder();
            holder.tvUsername = convertView.findViewById(R.id.tv_review_user);
            holder.tvRating = convertView.findViewById(R.id.tv_review_rating);
            holder.tvContent = convertView.findViewById(R.id.tv_review_content);
            holder.tvDate = convertView.findViewById(R.id.tv_review_date);
            holder.tvDelete = convertView.findViewById(R.id.tv_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Review review = reviewList.get(position);
        holder.tvUsername.setText(review.getUsername());
        holder.tvRating.setText("评分：" + review.getRating() + "/10");
        holder.tvContent.setText(review.getContent());
        holder.tvDate.setText(review.getDate());

        if (review.getUserId() == currentUserId) {
            holder.tvDelete.setVisibility(View.VISIBLE);
            holder.tvDelete.setOnClickListener(v -> {
                if (deleteListener != null) {
                    deleteListener.onDelete(review.getId());
                }
            });
        } else {
            holder.tvDelete.setVisibility(View.GONE);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView tvUsername, tvRating, tvContent, tvDate, tvDelete;
    }
}
