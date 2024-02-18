package com.example.collegealert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.collegealert.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

binding= DataBindingUtil.setContentView (this,R.layout.activity_detail);
        Bundle bundle=getIntent ().getExtras ();

    binding.objective.setText (bundle.getString ("objective"));
        Glide.with (this).load (bundle.getString ("img1")).into (binding.Detailimg);
         binding.description.setText (bundle.getString ("description"));
        String spinner=bundle.getString ("Spinner");
        Toast.makeText (this, ""+spinner, Toast.LENGTH_SHORT).show ();

    }
}