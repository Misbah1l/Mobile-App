package com.example.myauthapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView tvProfileEmail =
                findViewById(R.id.tvProfileEmail);

        Button btnBackDashboard =
                findViewById(R.id.btnBackDashboard);

        String email = getIntent().getStringExtra("USER_EMAIL");

        if (email != null && !email.isEmpty()) {
            tvProfileEmail.setText(email);
        }

        btnBackDashboard.setOnClickListener(v -> {
            finish();
        });
    }
}