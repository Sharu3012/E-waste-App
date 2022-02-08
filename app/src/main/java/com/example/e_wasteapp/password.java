package com.example.e_wasteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class password extends AppCompatActivity {
    String username;
    EditText new_pass,confirm_pass;
    Button save;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        Intent in=getIntent();
        username=in.getStringExtra("Name");
        new_pass=(EditText)findViewById(R.id.new_pass);
        confirm_pass=(EditText)findViewById(R.id.confirm_pass);
        save=(Button)findViewById(R.id.save_pass);
        reference= FirebaseDatabase.getInstance().getReference().child("users");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newpass=new_pass.getText().toString();
                String confpass=confirm_pass.getText().toString();

                if(newpass.equals("") && confpass.equals("")){
                    Toast.makeText(getApplicationContext(),"Field Empty",Toast.LENGTH_LONG).show();
                }
                else {
                    if(newpass.equals(confpass)){
                        HashMap hashMap=new HashMap();
                        hashMap.put("pass",newpass);
                        reference.child(username).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                Toast.makeText(getApplicationContext(),"Password Updated..!",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Password doesn't match",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}