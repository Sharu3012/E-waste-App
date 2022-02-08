package com.example.e_wasteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Product_view extends AppCompatActivity {
    String username,name,category,price;
    DatabaseReference reference;
    ImageView imageView;
    TextView name_p,category_p,price_p;
    Button add_to_cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        reference= FirebaseDatabase.getInstance().getReference().child("Users");
        name_p=(TextView)findViewById(R.id.name_product);
        category_p=(TextView)findViewById(R.id.cate_product);
        price_p=(TextView)findViewById(R.id.price_product);
        add_to_cart=(Button)findViewById(R.id.add_to_cart);
        imageView=findViewById(R.id.image_product);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            int resId=bundle.getInt("image");
            imageView.setImageResource(resId);
        }

        Intent in=getIntent();
        username=in.getStringExtra("Name");

        Intent name1=getIntent();
        name=name1.getStringExtra("P_Name");
        name_p.setText(name);

        Intent Category=getIntent();
        category=Category.getStringExtra("Category");
        category_p.setText(category);

        Intent Price=getIntent();
        price=Price.getStringExtra("Price");
        price_p.setText(price);

        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_LONG).show();
            }
        });

    }
}