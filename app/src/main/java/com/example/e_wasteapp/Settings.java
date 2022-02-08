package com.example.e_wasteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
    String username;
    TextView about,terms_c,change_pass,privacy_p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent in=getIntent();
        username=in.getStringExtra("Name");
        about=(TextView)findViewById(R.id.about);
        change_pass=(TextView)findViewById(R.id.change_pass_s);
        privacy_p=(TextView)findViewById(R.id.privacy_policy);
        terms_c=(TextView)findViewById(R.id.terms_condi);

        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Settings.this,password.class);
                startActivity(i);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Settings.this,About.class);
                startActivity(i);
            }
        });

        privacy_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Settings.this,Privacy_policy.class);
                startActivity(i);
            }
        });

    }
}