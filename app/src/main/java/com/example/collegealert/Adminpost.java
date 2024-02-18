package com.example.collegealert;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.collegealert.databinding.ActivityAdminpostBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class Adminpost extends AppCompatActivity {

ActivityAdminpostBinding binding;
    List<String> items;
     String spinnerposition;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,databaseReference1;


    Uri ImageUri;

    String imageURL,imageURL2;
    Uri uri,uri2;
    private StorageReference ProductImagesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_adminpost);
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
         binding= DataBindingUtil.setContentView (this,R.layout.activity_adminpost);
        items = new ArrayList<> ();
        items.add ("Select the Campus");
        items.add ("Superior lahore campus");
        items.add ("Superior Karachi campus");
        items.add ("Superior peshawar campus");
        databaseReference= FirebaseDatabase.getInstance ().getReference("users");

        Toast.makeText (this, ""+databaseReference, LENGTH_SHORT).show ();
        binding.spinner.setAdapter (new ArrayAdapter<> (this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, items));
         binding.spinner.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 spinnerposition=items.get (position);
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });



        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult (
                new ActivityResultContracts.StartActivityForResult (),
                new ActivityResultCallback<ActivityResult> () {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode () == Activity.RESULT_OK) {
                            Intent data = result.getData ();
                            uri = data.getData ();
                            binding.uploadimg.setImageURI (uri);
                        } else {
                            Toast.makeText (Adminpost.this, "please add ad image", Toast.LENGTH_SHORT).show ();
                        }
                    }
                }
        );
       binding.uploadimg.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent (Intent.ACTION_PICK);
                photoPicker.setType ("image/*");
                activityResultLauncher.launch (photoPicker);
            }
        });




         binding.uploadbtn.setOnClickListener (new View.OnClickListener () {
             @Override
             public void onClick(View v) {
                 String objective_=binding.AajnewsName.getText ().toString ().trim ();
                 String Description_=binding.Description.getText ().toString ().trim ();
                 if (TextUtils.isEmpty (binding.AajnewsName.getText ().toString ())) {
                     Toast.makeText (Adminpost.this, "Please Provide Objective", Toast.LENGTH_SHORT).show ();
                 } else if (TextUtils.isEmpty (binding.Description.getText ().toString() )) {
                     Toast.makeText (Adminpost.this, "Please Provide Your descriptopn", Toast.LENGTH_SHORT).show ();
                 }

store ();
             }
         });
    }



    public void store(){

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("AlertImages")
                .child(uri.getLastPathSegment());

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot> () {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageURL = urlImage.toString();
                if (TextUtils.isEmpty (imageURL)) {
                    Toast.makeText (Adminpost.this, "please ad image", LENGTH_SHORT).show ();

                }
                check ();

            }
        }).addOnFailureListener(new OnFailureListener () {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }











    public void check(){
        if(spinnerposition.equals ("Superior lahore campus")){
            firebaseDatabase=FirebaseDatabase.getInstance ();
            databaseReference=firebaseDatabase.getReference ("Superior lahore campus");
             databaseReference1=firebaseDatabase.getReference ("All campus");
            String objective_=binding.AajnewsName.getText ().toString ().trim ();
            String Description_=binding.Description.getText ().toString ().trim ();
            Donepost donepost=new Donepost (objective_,Description_,spinnerposition,imageURL);
             Donepost donepost1=new Donepost (objective_,Description_,spinnerposition,imageURL);
            databaseReference.child (objective_).setValue (donepost);
databaseReference1.child (objective_).setValue (donepost1);
            Toast.makeText (Adminpost.this,"Your data successfully add",Toast.LENGTH_LONG).show ();
            Intent intent=new Intent (Adminpost.this,MainActivity.class);
            startActivity (intent);


        }
       else if(spinnerposition.equals ("Superior Karachi campus")){
            firebaseDatabase=FirebaseDatabase.getInstance ();
            databaseReference=firebaseDatabase.getReference ("Superior karachi campus");
            databaseReference1=firebaseDatabase.getReference ("All campus");
            String objective_=binding.AajnewsName.getText ().toString ().trim ();
            String Description_=binding.Description.getText ().toString ().trim ();
            Donepost donepost=new Donepost (objective_,Description_,spinnerposition,imageURL);
            databaseReference.child (objective_).setValue (donepost);
            Donepost donepost1=new Donepost (objective_,Description_,spinnerposition,imageURL);
            databaseReference1.child (objective_).setValue (donepost1);
            Toast.makeText (Adminpost.this,"Your data successfully add",Toast.LENGTH_LONG).show ();
            Intent intent=new Intent (Adminpost.this,MainActivity.class);
            startActivity (intent);


        }
       else if(spinnerposition.equals ("Superior peshawar campus")){
            firebaseDatabase=FirebaseDatabase.getInstance ();
            databaseReference=firebaseDatabase.getReference ("Superior peshawar campus");
            databaseReference1=firebaseDatabase.getReference ("All campus");
            String objective_=binding.AajnewsName.getText ().toString ().trim ();
            String Description_=binding.Description.getText ().toString ().trim ();
            Donepost donepost=new Donepost (objective_,Description_,spinnerposition,imageURL);
            Donepost donepost1=new Donepost (objective_,Description_,spinnerposition,imageURL);
            databaseReference.child (objective_).setValue (donepost);
            databaseReference1.child (objective_).setValue (donepost1);
            Toast.makeText (Adminpost.this,"Your data successfully add",Toast.LENGTH_LONG).show ();
            Intent intent=new Intent (Adminpost.this,MainActivity.class);
            startActivity (intent);


        }
       else {
            Toast.makeText (Adminpost.this,"please select campus",Toast.LENGTH_LONG).show ();
        }


    }


    }
