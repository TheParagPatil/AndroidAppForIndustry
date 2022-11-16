package com.oneroofit.it.verification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oneroofit.it.R;

public class FPMakeSelection extends AppCompatActivity {

    Button CallemailScr,CallPhoneScr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_p_make_selection);

        CallemailScr = findViewById(R.id.emailScrBtn);
        CallPhoneScr = findViewById(R.id.emailScrBtn);



        CallemailScr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FPMakeSelection.this, ForgetPasswordEmail.class);
                startActivity(intent);
                finish();
            }
        });
        CallPhoneScr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FPMakeSelection.this, ForgetPasswordEmail.class);
                startActivity(intent);
                finish();
            }
        });

    }
}