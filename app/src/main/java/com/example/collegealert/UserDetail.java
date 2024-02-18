package com.example.collegealert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.collegealert.databinding.ActivityUserDetailBinding;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class UserDetail extends AppCompatActivity {
ActivityUserDetailBinding binding;
FirebaseDatabase firebaseDatabase,firebaseDatabase1;
 Task<Void> databaseReference,DatabaseReference1;
  String objective;


    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
         binding= DataBindingUtil.setContentView (this,R.layout.activity_user_detail);
        Bundle bundle=getIntent ().getExtras ();
      String spinner=bundle.getString ("Spinner");
        Toast.makeText (this, ""+spinner, Toast.LENGTH_SHORT).show ();
        binding.objective.setText (bundle.getString ("objective"));
      objective=bundle.getString ("objective");
        Glide.with (this).load (bundle.getString ("img1")).into (binding.Detailimg);
        binding.description.setText (bundle.getString ("description"));

binding.Deletebtn.setOnClickListener (new View.OnClickListener () {
    @Override
    public void onClick(View v) {



         if(spinner.equals ("Superior peshawar campus")){

       firebaseDatabase1=FirebaseDatabase.getInstance ();

              DatabaseReference1=firebaseDatabase1.getReference ("Superior peshawar campus").child (objective).setValue (null);
             firebaseDatabase=FirebaseDatabase.getInstance ();
             databaseReference=firebaseDatabase.getReference ("All campus").child (objective).setValue (null);
             Intent intent=new Intent (UserDetail.this,MainActivity.class);
             startActivity (intent);
         }


        else if(spinner.equals ("Superior lahore campus")){
             firebaseDatabase=FirebaseDatabase.getInstance ();
             databaseReference=firebaseDatabase.getReference ("All campus").child (objective).setValue (null);
            firebaseDatabase1=FirebaseDatabase.getInstance ();
            DatabaseReference1=firebaseDatabase1.getReference ("Superior lahore campus").child (objective).setValue (null);

            Intent intent=new Intent (UserDetail.this,MainActivity.class);
            startActivity (intent);
        }
         else if(spinner.equals ("Superior Karachi campus")){
             firebaseDatabase=FirebaseDatabase.getInstance ();
             databaseReference=firebaseDatabase.getReference ("All campus").child (objective).setValue (null);
             firebaseDatabase1=FirebaseDatabase.getInstance ();
             DatabaseReference1=firebaseDatabase1.getReference ("Superior Karachi campus").child (objective).setValue (null);

             Intent intent=new Intent (UserDetail.this,MainActivity.class);
             startActivity (intent);
         }


    }
});


    }
}