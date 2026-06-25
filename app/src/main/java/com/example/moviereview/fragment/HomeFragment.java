package com.example.moviereview.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import com.example.moviereview.MovieDetailActivity;
import com.example.moviereview.R;
import com.example.moviereview.adapter.MovieAdapter;
import com.example.moviereview.database.DatabaseHelper;
import com.example.moviereview.model.Movie;
import java.util.List;

public class HomeFragment extends Fragment {

    private int userId;
    private DatabaseHelper dbHelper;
    private ListView lvMovies;
    private MovieAdapter adapter;

    public static HomeFragment newInstance(int userId) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt("user_id", userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        if (getArguments() != null) {
            userId = getArguments().getInt("user_id");
        }

        dbHelper = new DatabaseHelper(getActivity());
        lvMovies = view.findViewById(R.id.lv_movies);

        loadMovies();

        lvMovies.setOnItemClickListener((parent, itemView, position, id) -> {
            Movie movie = (Movie) adapter.getItem(position);
            Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
            intent.putExtra("movie_id", movie.getId());
            intent.putExtra("user_id", userId);
            startActivity(intent);
        });

        return view;
    }

    private void loadMovies() {
        List<Movie> movieList = dbHelper.getAllMovies();
        adapter = new MovieAdapter(getActivity(), movieList);
        lvMovies.setAdapter(adapter);
    }
}
