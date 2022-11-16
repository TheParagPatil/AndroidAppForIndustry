package com.oneroofit.it.ui.Privacy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oneroofit.it.R;
public class PrivacyPolicyFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        {
            //((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Play Matches");
            return inflater.inflate(R.layout.fragment_privacy_policy, container, false);
        }
    }
}