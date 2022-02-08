package com.example.e_wasteapp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

public class Menu_home extends AppCompatActivity {
    String username;
    TextView name,profile,contact_us,change_pass,settings;
    ImageView profile_img;
    Button logout;
    ActivityResultLauncher<String> launcher;
    FirebaseDatabase database;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_home);

        Intent in=getIntent();
        username=in.getStringExtra("Name");
        name=(TextView)findViewById(R.id.urname_menu);
        name.setText("Welcome "+username);
        profile=(TextView)findViewById(R.id.profile_m);
        contact_us=(TextView)findViewById(R.id.contact_us_m);
        change_pass=(TextView)findViewById(R.id.change_pass_m);
        settings=(TextView)findViewById(R.id.settings_m);
        profile_img=(ImageView)findViewById(R.id.profile_pic);

        logout=(Button)findViewById(R.id.logout_b);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Menu_home.this,MainActivity.class);
                startActivity(i);
            }
        });

        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Menu_home.this,password.class);
                i.putExtra("Name",username);
                startActivity(i);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Menu_home.this,Settings.class);
                i.putExtra("Name",username);
                startActivity(i);
            }
        });

        profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePic();
            }
        });

        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();

        launcher=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                profile_img.setImageURI(uri);
                StorageReference reference=storage.getReference("Profiles").child("images");
                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                database.getReference("users").child(username).child("image").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getApplicationContext(),"Image Uploaded",Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

    }
    public void choosePic(){
        launcher.launch("image/*");
    }
}