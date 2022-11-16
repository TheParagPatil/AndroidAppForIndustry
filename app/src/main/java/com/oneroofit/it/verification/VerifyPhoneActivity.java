package com.oneroofit.it.verification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.oneroofit.it.R;

public class VerifyPhoneActivity extends AppCompatActivity {

    Button verify_btn;
    EditText phoneEnteredByTheUser;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);

        verify_btn = findViewById(R.id.signup_next_button);
        phoneEnteredByTheUser = findViewById(R.id.signup_phone_number);
        
    }
}