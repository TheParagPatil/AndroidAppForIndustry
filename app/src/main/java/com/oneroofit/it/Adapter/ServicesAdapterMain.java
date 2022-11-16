package com.oneroofit.it.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.oneroofit.it.Helper.ServiceHelperClass;
import com.oneroofit.it.R;
import com.oneroofit.it.activities.ServicesActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ServicesAdapterMain extends RecyclerView.Adapter<ServicesAdapterMain.ServicesViewHolder> {
    List<ServiceHelperClass> serviceLocations;
    Context context;
    private int lastPosition = -1;

    public ServicesAdapterMain(Context context, List<ServiceHelperClass> serviceLocations) {
        this.serviceLocations = serviceLocations;
        this.context = context;

    }

    @NonNull
    @Override
    public ServicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_main_card, parent, false);
        //ServicesViewHolder viewHolder = new ServicesViewHolder(view);
        final ServicesViewHolder viewHolder = new ServicesViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, ServicesActivity.class);
                i.putExtra("name", serviceLocations.get(viewHolder.getAdapterPosition()).getTitle());
                i.putExtra("description", serviceLocations.get(viewHolder.getAdapterPosition()).getDescription());
                i.putExtra("image_url", serviceLocations.get(viewHolder.getAdapterPosition()).getImage());

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
        setAnimation(holder.itemView, position);

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

    public static class ServicesViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, desc;
        CardView view_container;

        public ServicesViewHolder(@NonNull View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.service_main_card);
            image = itemView.findViewById(R.id.serv_Image);
            title = itemView.findViewById(R.id.serv_Name);
            desc = itemView.findViewById(R.id.serv_Desc);
        }
    }
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
