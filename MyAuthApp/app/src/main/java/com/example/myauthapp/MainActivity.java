package com.example.myauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnGoogle = findViewById(R.id.btnGoogle);

        TextView tvForgotPassword =
                findViewById(R.id.tvForgotPassword);

        TextView tvSignUp =
                findViewById(R.id.tvSignUp);

        btnLogin.setOnClickListener(v -> loginUser());

        btnGoogle.setOnClickListener(v ->
                Toast.makeText(
                        this,
                        "Google sign-in will be connected later.",
                        Toast.LENGTH_SHORT
                ).show()
        );

        tvForgotPassword.setOnClickListener(v ->
                Toast.makeText(
                        this,
                        "Password recovery will be added later.",
                        Toast.LENGTH_SHORT
                ).show()
        );

        tvSignUp.setOnClickListener(v ->
                Toast.makeText(
                        this,
                        "Sign-up will be added later.",
                        Toast.LENGTH_SHORT
                ).show()
        );
    }

    private void loginUser() {

        String email = etEmail.getText()
                .toString().trim();

        String password = etPassword.getText()
                .toString();

        if (email.isEmpty()) {
            etEmail.setError("Please enter your email");
            etEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter a valid email address");
            etEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            etPassword.setError("Please enter your password");
            etPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            etPassword.setError(
                    "Password must be at least 6 characters"
            );
            etPassword.requestFocus();
            return;
        }

        Intent intent = new Intent(
                MainActivity.this,
                DashboardActivity.class
        );

        intent.putExtra("USER_EMAIL", email);

        startActivity(intent);
    }
}