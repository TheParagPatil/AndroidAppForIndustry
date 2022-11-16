package com.oneroofit.it.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oneroofit.it.Helper.TeamHelperClass;
import com.oneroofit.it.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder>{
    List<TeamHelperClass> teamLocations;
    private Context context;
    private int lastPosition = -1;


    public TeamAdapter(Context context, List<TeamHelperClass> teamLocations) {
        this.teamLocations = teamLocations;
        this.context = context;
    }

    @NonNull
    @Override
    public TeamAdapter.TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_design_card,parent,false);
        TeamAdapter.TeamViewHolder teamViewHolder = new TeamAdapter.TeamViewHolder(view);
        return teamViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamAdapter.TeamViewHolder holder, int position) {
        TeamHelperClass teamHelperClass = teamLocations.get(position);
        //holder.image.setImageResource(teamHelperClass.getImage());
        Picasso.get()
                .load(teamHelperClass.getImage())
                .into(holder.image);
        holder.title.setText(teamHelperClass.getTitle());
        holder.desc.setText(teamHelperClass.getDescription());
        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return teamLocations.size();
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, desc;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.teamArticle);
            title = itemView.findViewById(R.id.teamTitle);
            desc = itemView.findViewById(R.id.teamDesc);
        }
    }
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
