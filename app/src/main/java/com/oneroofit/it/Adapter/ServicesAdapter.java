package com.oneroofit.it.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.oneroofit.it.Helper.ServiceHelperClass;
import com.oneroofit.it.R;
import com.oneroofit.it.activities.ServicesActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder> {

    List<ServiceHelperClass> serviceLocations;
    Context context;

    public ServicesAdapter(Context context, List<ServiceHelperClass> serviceLocations) {
        this.serviceLocations = serviceLocations;
        this.context = context;

    }

    @NonNull
    @Override
    public ServicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.services_card_design,parent,false);
        //ServicesViewHolder viewHolder = new ServicesViewHolder(view);
        final ServicesViewHolder viewHolder = new ServicesViewHolder(view) ;
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, ServicesActivity.class);
                i.putExtra("name",serviceLocations.get(viewHolder.getAdapterPosition()).getTitle());
                i.putExtra("description",serviceLocations.get(viewHolder.getAdapterPosition()).getDescription());
                i.putExtra("image_url",serviceLocations.get(viewHolder.getAdapterPosition()).getImage());

                context.startActivity(i);

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesViewHolder holder, int position) {

        ServiceHelperClass serviceHelperClass = serviceLocations.get(position);
        //holder.image.setImageResource(serviceHelperClass.getImage());
        Picasso.get()
                .load(serviceHelperClass.getImage())
                .into(holder.image);
        holder.title.setText(serviceHelperClass.getTitle());
        holder.desc.setText(serviceHelperClass.getSmall_desc());

        holder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ServicesActivity.class);
                intent.putExtra("name",serviceHelperClass.getTitle());
                intent.putExtra("description",serviceHelperClass.getDescription());
                intent.putExtra("image_url",serviceHelperClass.getImage());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceLocations.size();
    }

    public static class ServicesViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, desc;
        CardView view_container;

        public ServicesViewHolder(@NonNull View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container_services);
            image = itemView.findViewById(R.id.serviceImage);
            title = itemView.findViewById(R.id.serviceTitle);
            desc = itemView.findViewById(R.id.serviceDesc);
        }
    }
}
