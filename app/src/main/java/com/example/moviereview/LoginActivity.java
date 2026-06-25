package com.example.moviereview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.moviereview.database.DatabaseHelper;
import com.example.moviereview.model.User;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private CheckBox cbRemember;
    private Button btnLogin;
    private TextView tvGoRegister;
    private DatabaseHelper dbHelper;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);
        sp = getSharedPreferences("login", MODE_PRIVATE);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        cbRemember = findViewById(R.id.cb_remember);
        btnLogin = findViewById(R.id.btn_login);
        tvGoRegister = findViewById(R.id.tv_go_register);

        // 读取记住的密码
        if (sp.getBoolean("remember", false)) {
            etUsername.setText(sp.getString("username", ""));
            etPassword.setText(sp.getString("password", ""));
            cbRemember.setChecked(true);
        }

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, R.string.input_empty, Toast.LENGTH_SHORT).show();
                return;
            }
            User user = dbHelper.loginUser(username, password);
            if (user != null) {
                // 记住密码
                SharedPreferences.Editor editor = sp.edit();
                if (cbRemember.isChecked()) {
                    editor.putBoolean("remember", true);
                    editor.putString("username", username);
                    editor.putString("password", password);
                } else {
                    editor.clear();
                }
                editor.apply();

                Toast.makeText(this, R.string.login_success, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("user_id", user.getId());
                intent.putExtra("username", user.getUsername());
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, R.string.login_fail, Toast.LENGTH_SHORT).show();
            }
        });

        tvGoRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }
}
