package com.example.collegealert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.collegealert.databinding.ActivityLahorecampusBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class lahorecampus extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
   Adapter adapter;
    ArrayList<KarachiModel> list;
     ActivityLahorecampusBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

         binding= DataBindingUtil.setContentView (this,R.layout.activity_lahorecampus);
        databaseReference= FirebaseDatabase.getInstance ().getReference("Superior lahore campus");
         binding.userlahoreRecyclerview.setHasFixedSize (true);
      binding.userlahoreRecyclerview.setLayoutManager (new LinearLayoutManager (this));

        list=new ArrayList<> ();
        adapter=new Adapter (this,list);
        binding.userlahoreRecyclerview.setAdapter (adapter);
        databaseReference.addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1:snapshot.getChildren ()){
                KarachiModel adminModel=snapshot1.getValue (KarachiModel.class);
                    list.add (adminModel);

                }
                adapter.notifyDataSetChanged ();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FirebaseMessaging.getInstance ().subscribeToTopic ("LahoreCampus").addOnCompleteListener (new OnCompleteListener<Void> () {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String message="Done";
                if(!task.isSuccessful ()){
                    message="Failed";
                }
            }
        });
    }


}