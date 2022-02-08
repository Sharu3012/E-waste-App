package com.example.e_wasteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Cart_items extends AppCompatActivity {
    TextView name,price;
    Integer total=0;
    TextView total_p;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_items);

        name=(TextView)findViewById(R.id.name_cart1);
        price=(TextView)findViewById(R.id.price_cart1);
        total_p=(TextView)findViewById(R.id.total);
        submit=(Button)findViewById(R.id.check_out);

        total=200;
        total_p.setText(total.toString());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Cart_items.this,Billing.class);
                startActivity(i);
            }
        });

    }
}