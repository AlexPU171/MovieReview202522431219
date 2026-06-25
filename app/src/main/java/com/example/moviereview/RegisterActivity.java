package com.example.moviereview;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.moviereview.database.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsername, etPassword, etConfirmPassword;
    private Button btnRegister;
    private TextView tvGoLogin;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DatabaseHelper(this);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnRegister = findViewById(R.id.btn_register);
        tvGoLogin = findViewById(R.id.tv_go_login);

        btnRegister.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirm = etConfirmPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, R.string.input_empty, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!password.equals(confirm)) {
                Toast.makeText(this, R.string.password_not_match, Toast.LENGTH_SHORT).show();
                return;
            }
            long result = dbHelper.registerUser(username, password);
            if (result != -1) {
                Toast.makeText(this, R.string.register_success, Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "注册失败，用户名可能已存在", Toast.LENGTH_SHORT).show();
            }
        });

        tvGoLogin.setOnClickListener(v -> finish());
    }
}
