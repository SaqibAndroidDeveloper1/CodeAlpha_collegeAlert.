package com.example.collegealert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.example.collegealert.databinding.ActivityRegistrationBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Registration_Activity extends AppCompatActivity {

    List<String> items;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
     String campusname;
      ActivityRegistrationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        binding=DataBindingUtil.setContentView (this,R.layout.activity_registration);

        items = new ArrayList<> ();
        items.add ("Select the Campus");
        items.add ("Superior lahore campus");
        items.add ("Superior Karachi campus");
        items.add ("Superior peshawar campus");
        binding.spinner.setAdapter (new ArrayAdapter<> (this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, items));
 binding.spinner.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
     @Override
     public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         campusname=items.get (position);
     }

     @Override
     public void onNothingSelected(AdapterView<?> parent) {

     }
 });



   binding.Alreadyregistered.setOnClickListener (new View.OnClickListener () {
       @Override
       public void onClick(View v) {
           Intent intent=new Intent (Registration_Activity.this,Login.class);
           startActivity (intent);
       }
   });




binding.registerBtn.setOnClickListener (new View.OnClickListener () {
    @Override
    public void onClick(View v) {
       String emaimatches= "^[a-zA-Z0-9._%-]{1,20}@[a-z0-9]{1,20}.[a-zA-Z]{2,4}$";
         String name=binding.registerUsername.getText ().toString ().trim ();
          String email=binding.registerEmail.getText ().toString ().trim ();
           String password=binding.registerPassword.getText ().toString ().trim ();
            if(!name.isEmpty ()){
                 binding.registerUsername.setError (null);


if(email.matches (emaimatches)) {
    firebaseDatabase = FirebaseDatabase.getInstance ();
    databaseReference = firebaseDatabase.getReference ("Users");
    String username2_ = binding.registerUsername.getText ().toString ().trim ();
    String email_ = binding.registerEmail.getText ().toString ().trim ();
    String password2_ = binding.registerPassword.getText ().toString ().trim ();
    Users storingdata = new Users (username2_, email_,campusname, password2_);
    databaseReference.child (username2_).setValue (storingdata);
    Toast.makeText (Registration_Activity.this, "Registered Successfully", Toast.LENGTH_LONG).show ();
    Intent intent = new Intent (Registration_Activity.this, Login.class);
    startActivity (intent);
    finish ();


}
else{
        binding.registerEmail.setError ("please enter your Email");
    }


        if (!password.isEmpty ()) {
            binding.registerPassword.setError (null);
        }
        else {
binding.registerPassword.setError ("please enter your password");
        }




    }else {
                 binding.registerUsername.setError ("please enter Username");
            }

}



});



    }
}