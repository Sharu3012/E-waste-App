package com.example.e_wasteapp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class uplode_p extends AppCompatActivity {

    Button submit;
    int id;
    String username;
    EditText disp,brand,model,quanti,price,address,phno;
    ImageView profilepic;
    ActivityResultLauncher<String> launcher;
    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference reference;
    RadioGroup group1,group2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uplode_p);

        disp=(EditText)findViewById(R.id.discription);
        brand=(EditText)findViewById(R.id.bd_name);
        model=(EditText)findViewById(R.id.model);
        quanti=(EditText)findViewById(R.id.quantity);
        price=(EditText)findViewById(R.id.price);
        address=(EditText)findViewById(R.id.address);
        phno=(EditText)findViewById(R.id.phno);
        submit=(Button)findViewById(R.id.sub_but);
        group1=(RadioGroup)findViewById(R.id.radioGroup1);
        group2=(RadioGroup)findViewById(R.id.radioGroup2);
        id=1;

        Intent in=getIntent();
        username=in.getStringExtra("Name");

        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();
        profilepic=(ImageView)findViewById(R.id.product_img);
        launcher=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                profilepic.setImageURI(uri);
                StorageReference reference=storage.getReference("Products").child("image");
                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                database.getReference(String.valueOf(id)).child("image").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
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

        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePic();
            }
        });

        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton1=(RadioButton)group.findViewById(checkedId);
            }
        });

        group2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton2=(RadioButton)group.findViewById(checkedId);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = null,condition=null;
                String discription=disp.getText().toString();
                String brand_n=brand.getText().toString();
                String model_p=model.getText().toString();
                int quantity=Integer.parseInt(quanti.getText().toString());
                double price_p=Double.parseDouble(price.getText().toString());
                String ph_no=phno.getText().toString();
                String address_p=address.getText().toString();
                String.format("%.2f",price_p);

                int selectedId1=group1.getCheckedRadioButtonId();
                int selectedId2=group2.getCheckedRadioButtonId();
                if(selectedId1==-1 && selectedId2==-1){
                    Toast.makeText(getApplicationContext(),"Select the type of Product",Toast.LENGTH_LONG).show();
                }
                else {
                    RadioButton radioButton1=(RadioButton)group1.findViewById(selectedId1);
                    RadioButton radioButton2=(RadioButton)group2.findViewById(selectedId2);
                    type=radioButton1.getText().toString();
                    condition=radioButton2.getText().toString();
                }

                if(discription.equals("") || brand_n.equals("") || model_p.equals("") || ph_no.equals("") || address_p.equals("") || quanti.equals("") || price.equals("")){
                    Toast.makeText(getApplicationContext(),"Enter all fields..!",Toast.LENGTH_LONG).show();
                }
                else {
                    String type_p=type.toString();
                    String condition_p=condition.toString();
                    database=FirebaseDatabase.getInstance();
                    reference = database.getReference("Products");
                    Product_info product_info = new Product_info(type_p,username,condition_p,ph_no,brand_n,model_p,quantity,id,price_p);
                    reference.child(String.valueOf(id)).setValue(product_info);
                    Toast.makeText(getApplicationContext(),"Upload Completed",Toast.LENGTH_LONG).show();
                    id=id+1;
                }

            }
        });
    }



    public void choosePic(){
        launcher.launch("image/*");
    }

}