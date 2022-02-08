package com.example.e_wasteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Main_page extends AppCompatActivity {
    String username;
    ImageView tv,hp,mb,cpu,smtv;
    TextView tv_price,hp_price,mb_price,cpu_price,sm_price;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Intent in=getIntent();
        username=in.getStringExtra("Name");
        tv=(ImageView)findViewById(R.drawable.tv);
        hp=(ImageView)findViewById(R.drawable.head_p);
        mb=(ImageView)findViewById(R.drawable.cm);
        cpu=(ImageView)findViewById(R.drawable.cpu);
        smtv=(ImageView)findViewById(R.drawable.tv_img);

        tv_price=(TextView)findViewById(R.id.price4);
        hp_price=(TextView)findViewById(R.id.price3);
        mb_price=(TextView)findViewById(R.id.price1);
        cpu_price=(TextView)findViewById(R.id.price2);
        sm_price=(TextView)findViewById(R.id.price5);

    }

    public void menu_page(View v){
        Intent i =new Intent(Main_page.this,Menu_home.class);
        i.putExtra("Name",username);
        startActivity(i);
    }

    public void tv(View v){
        String price=tv_price.getText().toString();
        Intent i=new Intent(Main_page.this,Product_view.class);
        i.putExtra("image",R.drawable.tv);
        i.putExtra("P_Name","TV");
        i.putExtra("Category","Electronic");
        i.putExtra("Price",price);
        startActivity(i);
    }

    public void hp(View v){
        String price=hp_price.getText().toString();
        Intent i=new Intent(Main_page.this,Product_view.class);
        i.putExtra("image",R.drawable.head_p);
        i.putExtra("P_Name","HeadPhones");
        i.putExtra("Category","Other");
        i.putExtra("Price",price);
        startActivity(i);
    }

    public void cpu(View v){
        String price=cpu_price.getText().toString();
        Intent i=new Intent(Main_page.this,Product_view.class);
        i.putExtra("image",R.drawable.cpu);
        i.putExtra("P_Name","CPU");
        i.putExtra("Category","Electronic");
        i.putExtra("Price",price);
        startActivity(i);
    }

    public void mb(View v){
        String price=mb_price.getText().toString();
        Intent i=new Intent(Main_page.this,Product_view.class);
        i.putExtra("image",R.drawable.cm);
        i.putExtra("P_Name","MotherBoard");
        i.putExtra("Category","Electronic");
        i.putExtra("Price",price);
        startActivity(i);
    }

    public void cart(View v){
        Intent i=new Intent(Main_page.this,Cart_items.class);
        startActivity(i);
    }

    public void smtv(View v){
        String price=sm_price.getText().toString();
        Intent i=new Intent(Main_page.this,Product_view.class);
        i.putExtra("image",R.drawable.tv_img);
        i.putExtra("P_Name","Smart TV");
        i.putExtra("Category","Electrical/Electronic");
        i.putExtra("Price",price);
        startActivity(i);
    }


}