package com.example.e_wasteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button login,signup;
    EditText user,pass;
    DatabaseReference userinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=(Button)findViewById(R.id.login_app);
        signup=(Button)findViewById(R.id.signup_app);

        userinfo= FirebaseDatabase.getInstance().getReference().child("Users");
        login_info();
        add_data();
    }

    private void login_info(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user=(EditText)findViewById(R.id.user_app);
                pass=(EditText)findViewById(R.id.pass_app);
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(getApplicationContext(),"Please enter all fields",Toast.LENGTH_LONG).show();
                else {
                    isUser();
                }

            }

            private void isUser() {
                String username=user.getText().toString();
                String password=pass.getText().toString();
                DatabaseReference reference=FirebaseDatabase.getInstance().getReference("users");
                Query checkuser=reference.orderByChild("user").equalTo(username);
                checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            user.setError(null);

                            String passfromdb=dataSnapshot.child(username).child("pass").getValue(String.class);
                            if(passfromdb.equals(password)){
                                pass.setError(null);
                                Intent i=new Intent(MainActivity.this,Choose_mode.class);
                                i.putExtra("Name",username);
                                startActivity(i);
                                Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
                            }
                            else {
                                pass.setError("Wrong Password");
                                pass.requestFocus();
                            }
                        }
                        else {
                            user.setError("Wrong Username");
                            user.requestFocus();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void add_data(){
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,NewLogin.class);
                startActivity(i);
            }
        });

    }

    public void forget(View v){
        Intent i=new Intent(MainActivity.this,Pass_forgot.class);
        startActivity(i);
    }
}