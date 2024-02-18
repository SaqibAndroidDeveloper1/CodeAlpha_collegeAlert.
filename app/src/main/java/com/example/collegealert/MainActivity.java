package com.example.collegealert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.collegealert.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    myAdapter adapter;
    ArrayList<KarachiModel> list;

ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

         binding= DataBindingUtil.setContentView (this,R.layout.activity_main);
        databaseReference= FirebaseDatabase.getInstance ().getReference("All campus");
        binding.MAinRecyclerview.setHasFixedSize (true);
        binding.MAinRecyclerview.setLayoutManager (new LinearLayoutManager (this));
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        list=new ArrayList<> ();
        adapter= new myAdapter (this,list);

        binding.MAinRecyclerview.setAdapter (adapter);
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


          binding.Admincreatepost.setOnClickListener (new View.OnClickListener () {
              @Override
              public void onClick(View v) {
                  Intent intent=new Intent (MainActivity.this,Adminpost.class);
                  startActivity (intent);

              }
          });
    }
}