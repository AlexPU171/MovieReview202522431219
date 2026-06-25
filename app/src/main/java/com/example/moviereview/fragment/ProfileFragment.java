package com.example.moviereview.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.example.moviereview.AboutActivity;
import com.example.moviereview.LoginActivity;
import com.example.moviereview.R;
import com.example.moviereview.database.DatabaseHelper;

public class ProfileFragment extends Fragment {

    private int userId;
    private String username;

    public static ProfileFragment newInstance(int userId, String username) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putInt("user_id", userId);
        args.putString("username", username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        if (getArguments() != null) {
            userId = getArguments().getInt("user_id");
            username = getArguments().getString("username");
        }

        TextView tvNickname = view.findViewById(R.id.tv_nickname);
        TextView tvUsername = view.findViewById(R.id.tv_username);
        TextView tvLogout = view.findViewById(R.id.tv_logout);
        TextView tvAbout = view.findViewById(R.id.tv_about);
        TextView tvDeleteAccount = view.findViewById(R.id.tv_delete_account);

        tvNickname.setText(username);
        tvUsername.setText("用户名：" + username);

        tvDeleteAccount.setOnClickListener(v -> {
            new AlertDialog.Builder(getActivity())
                    .setTitle("注销账号")
                    .setMessage("确定要注销当前账号吗？注销后账号将被永久删除，无法恢复。")
                    .setPositiveButton("确认注销", (dialog, which) -> {
                        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
                        dbHelper.deleteUser(userId);

                        SharedPreferences sp = getActivity().getSharedPreferences("login", 0);
                        sp.edit().clear().apply();

                        Toast.makeText(getActivity(), "账号已注销", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    })
                    .setNegativeButton("取消", null)
                    .show();
        });

        tvLogout.setOnClickListener(v -> {
            new AlertDialog.Builder(getActivity())
                    .setTitle("提示")
                    .setMessage(R.string.logout_confirm)
                    .setPositiveButton("确定", (dialog, which) -> {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    })
                    .setNegativeButton("取消", null)
                    .show();
        });

        tvAbout.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AboutActivity.class));
        });

        return view;
    }
}
