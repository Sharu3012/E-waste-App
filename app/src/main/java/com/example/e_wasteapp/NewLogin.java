package com.example.e_wasteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewLogin extends AppCompatActivity {
    Button signin;
    EditText fn,ln,usern,pass,repass,phno;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        fn=(EditText)findViewById(R.id.fn_app);
        ln=(EditText)findViewById(R.id.ln_app);
        usern=(EditText)findViewById(R.id.new_un_app);
        pass=(EditText)findViewById(R.id.nlg_pass_app);
        repass=(EditText)findViewById(R.id.nlg_repass_app);
        phno=(EditText)findViewById(R.id.phn_app);
        signin=(Button)findViewById(R.id.signin_app);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=usern.getText().toString();
                String password=pass.getText().toString();
                String re_enter=repass.getText().toString();
                String first_name=fn.getText().toString();
                String last_name=ln.getText().toString();
                String phone_no=phno.getText().toString();

                if(username.equals("")||password.equals("")||re_enter.equals("")||first_name.equals("")||last_name.equals("")||phone_no.equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter all fields",Toast.LENGTH_LONG).show();
                }

                else{
                    if(password.equals(re_enter)) {
                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference("users");
                        UserinfoClass userinfo = new UserinfoClass(username, password, first_name, last_name, phone_no);
                        reference.child(username).setValue(userinfo);
                        Intent i=new Intent(NewLogin.this,Choose_mode.class);
                        i.putExtra("Name",username);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Password Not Maching",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

}