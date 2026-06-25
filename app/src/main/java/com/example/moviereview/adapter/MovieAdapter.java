package com.example.moviereview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.moviereview.R;
import com.example.moviereview.model.Movie;
import java.util.List;

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private List<Movie> movieList;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public int getCount() { return movieList.size(); }

    @Override
    public Object getItem(int position) { return movieList.get(position); }

    @Override
    public long getItemId(int position) { return movieList.get(position).getId(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
            holder = new ViewHolder();
            holder.ivPoster = convertView.findViewById(R.id.iv_movie_poster);
            holder.tvTitle = convertView.findViewById(R.id.tv_movie_title);
            holder.tvYear = convertView.findViewById(R.id.tv_movie_year);
            holder.tvRating = convertView.findViewById(R.id.tv_movie_rating);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Movie movie = movieList.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvYear.setText(movie.getYear());
        holder.tvRating.setText(String.valueOf(movie.getRating()));

        int resId = context.getResources().getIdentifier(
                movie.getPoster(), "drawable", context.getPackageName());
        if (resId != 0) {
            holder.ivPoster.setImageResource(resId);
        } else {
            holder.ivPoster.setImageResource(R.drawable.default_poster);
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView ivPoster;
        TextView tvTitle, tvYear, tvRating;
    }
}
