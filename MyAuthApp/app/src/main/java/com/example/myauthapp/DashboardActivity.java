package com.example.myauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView tvWelcome = findViewById(R.id.tvWelcome);

        Button btnProfile = findViewById(R.id.btnProfile);
        Button btnSecurity = findViewById(R.id.btnSecurity);
        Button btnActivity = findViewById(R.id.btnActivity);
        Button btnLogout = findViewById(R.id.btnLogout);

        String email = getIntent().getStringExtra("USER_EMAIL");

        tvWelcome.setText("Welcome back!");

        btnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(
                    DashboardActivity.this,
                    ProfileActivity.class
            );

            intent.putExtra("USER_EMAIL", email);
            startActivity(intent);
        });

        btnSecurity.setOnClickListener(v ->
                Toast.makeText(
                        this,
                        "Security settings coming soon.",
                        Toast.LENGTH_SHORT
                ).show()
        );

        btnActivity.setOnClickListener(v ->
                Toast.makeText(
                        this,
                        "No recent activity to display.",
                        Toast.LENGTH_SHORT
                ).show()
        );

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(
                    DashboardActivity.this,
                    MainActivity.class
            );

            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_CLEAR_TASK
            );

            startActivity(intent);
            finish();
        });
    }
}