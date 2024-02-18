package com.example.collegealert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.collegealert.databinding.ActivityAdminLoginBinding;
import com.example.collegealert.databinding.ActivityLoginBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity {
ActivityAdminLoginBinding binding1;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
       binding1= DataBindingUtil.setContentView (this,R.layout.activity_admin_login);
        progressDialog=new ProgressDialog (this);
binding1.AdminloginBtn.setOnClickListener (new View.OnClickListener () {
    @Override
    public void onClick(View v) {
        String username_=binding1.AdminUsername.getText ().toString ().trim ();
        String email_=binding1.AdminloginEmail.getText ().toString ().trim ();
        String password_=binding1.AdminloginPassword.getText ().toString ().trim ();
        progressDialog.setTitle ("Login");
        progressDialog.setMessage ("In progress......");
        progressDialog.show ();

        if (!username_.isEmpty ()){
            binding1.AdminUsername.setError (null);
        }
        else {
            binding1.AdminUsername.setError ("add correct username");
        }

        if (!email_.isEmpty ()){
            binding1.AdminloginEmail.setError (null);
        }
        else {
            binding1.AdminloginEmail.setError ("add correct Email");
        }
        if(!password_.isEmpty ()){
           binding1.AdminloginPassword.setError (null);
            final String user2_=binding1.AdminUsername.getText ().toString ().trim ();
            final String password2_=binding1.AdminloginPassword.getText ().toString ().trim ();
            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance ();
            DatabaseReference databaseReference=firebaseDatabase.getReference ("Admin");
            Query checkusername=databaseReference.orderByChild ("username").equalTo (user2_);

            checkusername.addListenerForSingleValueEvent (new ValueEventListener () {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists ()){
                        binding1.AdminUsername.setError (null);
                        String passwordcheck=snapshot.child (user2_).child ("password").getValue (String.class);
                        if (passwordcheck.equals (password2_)){
                           binding1.AdminloginPassword.setError (null);
                            Toast.makeText (AdminLogin.this,"Login Successfully",Toast.LENGTH_LONG).show ();
                            progressDialog.show ();
                            Intent intent=new Intent (AdminLogin.this,MainActivity.class);
                            startActivity (intent);
                            finish ();
                        } else {
                            binding1.AdminloginPassword.setError ("wrong password");
                            progressDialog.dismiss ();

                        }
                    }
                    else {
                        binding1.AdminUsername.setError ("user doest not esist");
                        progressDialog.dismiss ();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        else {
            binding1.AdminloginPassword.setError ("wrong password");
        }





    }

});}}