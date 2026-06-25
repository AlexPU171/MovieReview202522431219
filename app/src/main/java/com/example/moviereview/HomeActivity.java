package com.example.moviereview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.moviereview.fragment.HomeFragment;
import com.example.moviereview.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private int userId;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userId = getIntent().getIntExtra("user_id", -1);
        username = getIntent().getStringExtra("username");

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);

        // 默认显示首页
        loadFragment(HomeFragment.newInstance(userId));

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                loadFragment(HomeFragment.newInstance(userId));
                return true;
            } else if (id == R.id.nav_profile) {
                loadFragment(ProfileFragment.newInstance(userId, username));
                return true;
            }
            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
