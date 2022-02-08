package com.example.e_wasteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Choose_mode extends AppCompatActivity {

    String username;
    Button sell,buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mode);

        Intent in=getIntent();
        username=in.getStringExtra("Name");
        sell=(Button)findViewById(R.id.sellewaste_but);
        buy=(Button)findViewById(R.id.buyewaste_but);

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category="sell";
                Intent i=new Intent(Choose_mode.this,uplode_p.class);
                i.putExtra("Name",username);
                startActivity(i);
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Choose_mode.this,Main_page.class);
                i.putExtra("Name",username);
                startActivity(i);
            }
        });

    }
}