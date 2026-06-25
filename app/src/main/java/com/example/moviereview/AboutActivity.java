package com.example.moviereview;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView tvAbout = findViewById(R.id.tv_about);
        tvAbout.setText("MovieReview 影评App v1.0\n\n"
                + "一款简洁的电影影评浏览与管理应用。\n"
                + "浏览热门电影，查看详情，发表你的影评。\n\n"
                + "开发环境：Android Studio\n"
                + "开发语言：Java\n"
                + "数据库：SQLite\n\n"
                + "© 2026 MovieReview");
    }
}
