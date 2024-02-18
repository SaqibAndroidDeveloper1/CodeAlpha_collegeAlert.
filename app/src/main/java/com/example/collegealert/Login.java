package com.example.collegealert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.collegealert.databinding.ActivityLoginBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
ActivityLoginBinding binding;
    List<String> items;
    String campusname;
    List<String> item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
         binding= DataBindingUtil.setContentView (this,R.layout.activity_login);
        ProgressDialog progressDialog=new ProgressDialog (this);

        items = new ArrayList<> ();
        items.add ("Select the Campus");
        items.add ("Superior lahore campus");
        items.add ("Superior Karachi campus");
        items.add ("Superior peshawar campus");
        binding.spinner1.setAdapter (new ArrayAdapter<> (this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, items));
        binding.spinner1.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                campusname=items.get (position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






 binding.Imanadmin.setOnClickListener (new View.OnClickListener () {
     @Override
     public void onClick(View v) {
         Intent intent=new Intent (Login.this,AdminLogin.class);
          startActivity (intent);
     }
 });




          binding.loginBtn.setOnClickListener (new View.OnClickListener () {
              @Override
              public void onClick(View v) {


                  String usernme=binding.username.getText ().toString ().trim ();

                  String password_=binding.loginPassword.getText ().toString ().trim ();


                  progressDialog.setTitle ("Login");
                  progressDialog.setMessage ("In progress......");
                  progressDialog.show ();
                  if (!usernme.isEmpty ()){
                      binding.username.setError (null);
                  }
                  else {
                      binding.username.setError ("add correct username");
                  }






                  if(!password_.isEmpty ()){
                     binding.loginPassword.setError (null);

                      final String password2_=binding.loginPassword.getText ().toString ().trim ();



                      FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance ();
                      DatabaseReference databaseReference=firebaseDatabase.getReference ("Users");
                      Query checkusername=databaseReference.orderByChild ("username").equalTo (usernme);

                      checkusername.addListenerForSingleValueEvent (new ValueEventListener () {
                          @Override
                          public void onDataChange(@NonNull DataSnapshot snapshot) {
                              if (snapshot.exists ()){


                                  String passwordcheck=snapshot.child (usernme).child ("password").getValue (String.class);
                                   if(campusname.equals ("Superior lahore campus")&&(passwordcheck.equals (password2_))){
                                       binding.loginPassword.setError (null);

                                       Toast.makeText (Login.this,"Login Successfully",Toast.LENGTH_LONG).show ();
                                       Intent intent=new Intent (Login.this,lahorecampus.class);
                                       startActivity (intent);
                                       finish ();
                                       progressDialog.show ();


                                   } else if(campusname.equals ("Superior Karachi campus")&&(passwordcheck.equals (password2_))){
                                       binding.loginPassword.setError (null);

                                       Toast.makeText (Login.this,"Login Successfully",Toast.LENGTH_LONG).show ();
                                       Intent intent=new Intent (Login.this,KarachiCampus.class);
                                       startActivity (intent);
                                       finish ();
                                       progressDialog.show ();  }
                                   else if(campusname.equals ("Superior peshawar campus")&&(passwordcheck.equals (password2_))){
                                       binding.loginPassword.setError (null);

                                       Toast.makeText (Login.this,"Login Successfully",Toast.LENGTH_LONG).show ();
                                       Intent intent=new Intent (Login.this,peshawarcampus.class);
                                       startActivity (intent);
                                       finish ();
                                       progressDialog.show ();  }


                                        else {
                                     binding.loginPassword.setError ("wrong password");
                                      progressDialog.dismiss ();
                                  }
                              }

                          }

                          @Override
                          public void onCancelled(@NonNull DatabaseError error) {

                          }
                      });

                  }
                  else {
                     binding.loginPassword.setError ("wrong password");
                  }



              }
          });
    }
}