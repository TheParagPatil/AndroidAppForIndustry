package com.oneroofit.it.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.oneroofit.it.R;
import com.oneroofit.it.verification.LoginActivity;

public class ServicesActivity extends AppCompatActivity {
    ImageView img;
    TextView tv_title, tv_desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        getSupportActionBar().hide();


        String name = getIntent().getExtras().getString("name");
        String description = getIntent().getExtras().getString("description");
        String image_url = getIntent().getExtras().getString("image_url");


        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);

        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.black));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.black));

        collapsingToolbarLayout.setTitleEnabled(true);
        img = findViewById(R.id.serv_img);
        tv_title = findViewById(R.id.tv_title);
        tv_desc = findViewById(R.id.serv_description);

        tv_title.setText(name);
        tv_desc.setText(description);

        collapsingToolbarLayout.setTitle(name);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
        // set image using Glide
        Glide.with(this).load(image_url).apply(requestOptions).into(img);


    }

}
