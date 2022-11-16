package com.oneroofit.it.verification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.oneroofit.it.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class ForgetPasswordEmail extends AppCompatActivity {
    Button sendPassbtn;
    TextInputLayout txt_email;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_pass_recovery);

        sendPassbtn = findViewById(R.id.passRecEmail);
        txt_email = findViewById(R.id.recpassemail);
        progressDialog = new ProgressDialog(ForgetPasswordEmail.this);

        sendPassbtn.setOnClickListener(new View.OnClickListener() {
            private Boolean validateEmail() {
                String val = txt_email.getEditText().getText().toString();

                if (val.isEmpty()) {
                    txt_email.setError("Please enter your email!");
                    return false;
                } else {
                    txt_email.setError(null);
                    txt_email.setErrorEnabled(false);
                    return true;
                }
            }

            @Override
            public void onClick(View v) {
                String email;
                email = (txt_email.getEditText().getText().toString());
                if (!validateEmail()) {
                    return;
                }

                progressDialog.setMessage("Please Wait");
                progressDialog.show();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[1];
                        field[0] = "email";

                        String[] data = new String[1];
                        data[0] = email;

                        PutData putData = new PutData(UrlProvider.webUrl+"forgotpassword.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                //End ProgressBar (Set visibility to GONE)
                                progressDialog.dismiss();
                                if (result.equals("Success")) {
                                    Toast.makeText(getApplicationContext(), "Your password has been sent on your given Email! kindly please check your email and login.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }
                        }
                    }
                });
            }
        });
    }
}