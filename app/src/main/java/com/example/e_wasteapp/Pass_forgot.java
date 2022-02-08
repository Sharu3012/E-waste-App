package com.example.e_wasteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class Pass_forgot extends AppCompatActivity {
    EditText phn_no,otp;
    Button otp_send,otp_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_forgot);

        phn_no=(EditText)findViewById(R.id.otp_phn);
        otp=(EditText)findViewById(R.id.otp);
        otp_send=(Button)findViewById(R.id.send_otp);
        otp_submit=(Button)findViewById(R.id.otp_button_s);
        Random rand=new Random();
        int random=rand.nextInt(10000);

        otp_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone_no=phn_no.getText().toString();
                String otp_no= String.valueOf(random);
                String msg="Your OTP for login is : "+otp_no;
                SmsManager sms= SmsManager.getDefault();
                sms.sendTextMessage(phone_no,null,msg,null,null);
                Toast.makeText(getApplicationContext(), "Message Send" + otp_no, Toast.LENGTH_LONG).show();
            }
        });

        otp_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp_no1=String.valueOf(random);
                String enter_otp=otp.getText().toString();
                if(otp_no1.trim().equals(enter_otp.trim())){
                    Intent i=new Intent(Pass_forgot.this,Main_page.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Invalid OTP " +enter_otp + " and "+ otp_no1,Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}