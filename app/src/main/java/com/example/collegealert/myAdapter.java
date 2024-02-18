package com.example.collegealert;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {



    static Context context;
    static ArrayList<KarachiModel> list;
    DatabaseReference databaseReference1;
    public myAdapter(Context context, ArrayList<KarachiModel> list) {
        this.context = context;
        this.list = list;
    }

    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from (context).inflate (R.layout.singlerow,parent,false);
        return new Adapter.MyViewHolder (view);
    }


    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {
        KarachiModel Model=list.get (position);
        Glide.with (context).load (Model.getImg ()).into (holder.img1);
        holder.objective.setText (Model.getObjective ());




        holder.itemView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent (context, UserDetail.class);

                intent.putExtra ("objective", list.get (holder.getAdapterPosition ()).getObjective ());

                intent.putExtra ("description", list.get (holder.getAdapterPosition ()).getDescription ());
                intent.putExtra ("Spinner",list.get (holder.getAdapterPosition ()).getSpinnerdetail ());
                intent.putExtra ("img1", list.get (holder.getAdapterPosition ()).getImg ());



                context.startActivity (intent);







            }

        });


    }


    @Override
    public int getItemCount() {
        return list.size ();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView objective,DESCRIPTION;
        ImageView img1;

        public MyViewHolder(@NonNull View itemView) {
            super (itemView);

            img1=itemView.findViewById (R.id.Adminimg1);
            objective = itemView.findViewById (R.id.objective);

            DESCRIPTION = itemView.findViewById (R.id.Description);
        }

    }}
