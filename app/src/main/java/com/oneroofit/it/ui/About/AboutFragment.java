package com.oneroofit.it.ui.About;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oneroofit.it.R;

public class AboutFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Play Matches");
        return inflater.inflate(R.layout.fragment_about, container, false);
    }
}