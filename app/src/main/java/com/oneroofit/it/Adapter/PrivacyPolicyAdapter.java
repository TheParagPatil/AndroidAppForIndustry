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

import com.oneroofit.it.Helper.PrivacyPolicyHelperClass;
import com.oneroofit.it.Helper.TeamHelperClass;
import com.oneroofit.it.R;
import com.squareup.picasso.Picasso;

import java.util.List;

//public class PrivacyPolicyAdapter extends RecyclerView.Adapter<PrivacyPolicyAdapter.PrivacyPolicyViewHolder>{
//    List<PrivacyPolicyHelperClass> teamLocations;
//    private Context context;
//    private int lastPosition = -1;
//
//
//    public PrivacyPolicyAdapter(Context context, List<PrivacyPolicyHelperClass> teamLocations) {
//        this.teamLocations = teamLocations;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public TeamAdapter.PrivacyPolicyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_design_card,parent,false);
//        TeamAdapter.PrivacyPolicyViewHolder privacyPolicyViewHolder = new TeamAdapter.PrivacyPolicyViewHolder(view);
//        return privacyPolicyViewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull TeamAdapter.TeamViewHolder holder, int position) {
//        PrivacyPolicyHelperClass privacyPolicyHelperClass = teamLocations.get(position);
//        //holder.image.setImageResource(teamHelperClass.getImage());
//        Picasso.get()
//                .load(privacyPolicyHelperClass.getImage())
//                .into(holder.image);
//        holder.title.setText(privacyPolicyHelperClass.getTitle());
//        holder.desc.setText(privacyPolicyHelperClass.getDescription());
//        setAnimation(holder.itemView, position);
//    }
//
//    @Override
//    public int getItemCount() {
//        return teamLocations.size();
//    }
//
//    public static class TeamViewHolder extends RecyclerView.ViewHolder{
//
//        ImageView image;
//        TextView title, desc;
//
//        public TeamViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            image = itemView.findViewById(R.id.teamArticle);
//            title = itemView.findViewById(R.id.teamTitle);
//            desc = itemView.findViewById(R.id.teamDesc);
//        }
//    }
//    private void setAnimation(View viewToAnimate, int position)
//    {
//        // If the bound view wasn't previously displayed on screen, it's animated
//        if (position > lastPosition)
//        {
//            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
//            viewToAnimate.startAnimation(animation);
//            lastPosition = position;
//        }
//    }
//}
