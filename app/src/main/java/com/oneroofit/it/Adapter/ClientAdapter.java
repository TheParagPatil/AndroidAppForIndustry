package com.oneroofit.it.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oneroofit.it.Helper.ClientHelperClass;
import com.oneroofit.it.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> {
    List<ClientHelperClass> clientLocations;
    private Context context;


    public ClientAdapter(Context context, List<ClientHelperClass> clientLocations) {
        this.clientLocations = clientLocations;
        this.context = context;
    }


    @NonNull
    @Override
    public ClientAdapter.ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.our_client_card, parent, false);
        ClientAdapter.ClientViewHolder clientViewHolder = new ClientAdapter.ClientViewHolder(view);
        return clientViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClientAdapter.ClientViewHolder holder, int position) {
        ClientHelperClass clientHelperClass = clientLocations.get(position);
        //holder.image.setImageResource(clientHelperClass.getImage());
        Picasso.get()
                .load(clientHelperClass.getImage())
                .into(holder.image);
        holder.title.setText(clientHelperClass.getName());
        holder.desc.setText(clientHelperClass.getDescription());
    }


    @Override
    public int getItemCount() {
        return clientLocations.size();
    }

    public static class ClientViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, desc;

        public ClientViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.clientImage);
            title = itemView.findViewById(R.id.clientName);
            desc = itemView.findViewById(R.id.clientDesc);
        }
    }
}
