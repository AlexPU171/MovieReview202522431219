package com.example.moviereview;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.moviereview.adapter.ReviewAdapter;
import com.example.moviereview.database.DatabaseHelper;
import com.example.moviereview.model.Review;
import java.util.Date;
import java.util.List;

public class ReviewListActivity extends AppCompatActivity {

    private ListView lvReviews;
    private Button btnAddReview;
    private DatabaseHelper dbHelper;
    private ReviewAdapter adapter;
    private List<Review> reviewList;
    private int movieId;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);

        dbHelper = new DatabaseHelper(this);
        movieId = getIntent().getIntExtra("movie_id", -1);
        userId = getIntent().getIntExtra("user_id", -1);

        lvReviews = findViewById(R.id.lv_reviews);
        btnAddReview = findViewById(R.id.btn_add_review);

        loadReviews();

        btnAddReview.setOnClickListener(v -> showAddReviewDialog());
    }

    private void loadReviews() {
        reviewList = dbHelper.getReviewsByMovieId(movieId);
        adapter = new ReviewAdapter(this, reviewList, userId, reviewId -> {
            dbHelper.deleteReview(reviewId);
            loadReviews();
        });
        lvReviews.setAdapter(adapter);
    }

    private void showAddReviewDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_review, null);
        RatingBar rbRating = dialogView.findViewById(R.id.rb_rating);
        EditText etContent = dialogView.findViewById(R.id.et_review_content);

        new AlertDialog.Builder(this)
                .setTitle("写影评")
                .setView(dialogView)
                .setPositiveButton("提交", (dialog, which) -> {
                    String content = etContent.getText().toString().trim();
                    float rating = rbRating.getRating();
                    if (content.isEmpty()) {
                        Toast.makeText(this, "请输入影评内容", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String date = DateFormat.format("yyyy-MM-dd", new Date()).toString();
                    dbHelper.addReview(movieId, userId, content, rating, date);
                    loadReviews();
                })
                .setNegativeButton("取消", null)
                .show();
    }
}
