package com.example.moviereview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.moviereview.database.DatabaseHelper;
import com.example.moviereview.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView ivPoster;
    private TextView tvTitle, tvInfo, tvRating, tvDescription;
    private Button btnViewReviews;
    private DatabaseHelper dbHelper;
    private int movieId;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        dbHelper = new DatabaseHelper(this);
        movieId = getIntent().getIntExtra("movie_id", -1);
        userId = getIntent().getIntExtra("user_id", -1);

        ivPoster = findViewById(R.id.iv_poster);
        tvTitle = findViewById(R.id.tv_title);
        tvInfo = findViewById(R.id.tv_info);
        tvRating = findViewById(R.id.tv_rating);
        tvDescription = findViewById(R.id.tv_description);
        btnViewReviews = findViewById(R.id.btn_view_reviews);

        Movie movie = dbHelper.getMovieById(movieId);
        if (movie != null) {
            tvTitle.setText(movie.getTitle());
            tvInfo.setText(movie.getYear() + " / " + movie.getDirector());
            tvRating.setText(String.valueOf(movie.getRating()));
            tvDescription.setText(movie.getDescription());
            int resId = getResources().getIdentifier(movie.getPoster(), "drawable", getPackageName());
            ivPoster.setImageResource(resId != 0 ? resId : R.drawable.default_poster);
        }

        btnViewReviews.setOnClickListener(v -> {
            Intent intent = new Intent(this, ReviewListActivity.class);
            intent.putExtra("movie_id", movieId);
            intent.putExtra("user_id", userId);
            startActivity(intent);
        });
    }
}
