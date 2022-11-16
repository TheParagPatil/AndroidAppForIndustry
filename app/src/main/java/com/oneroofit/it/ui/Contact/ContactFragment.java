package com.oneroofit.it.ui.Contact;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.oneroofit.it.R;
import com.oneroofit.it.verification.RegistrationActivity;
import com.oneroofit.it.verification.UrlProvider;
import com.oneroofit.it.verification.VerifyPhoneActivity;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class ContactFragment extends Fragment {
    Button btn_send_message;
    TextInputLayout txt_con_email, txt_con_phone, txt_con_subject, txt_con_message, txt_con_name;
    ProgressDialog progressDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_contact, container, false);

        txt_con_name = rootview.findViewById(R.id.con_name);
        txt_con_email = rootview.findViewById(R.id.con_email);
        txt_con_phone = rootview.findViewById(R.id.con_phone);
        txt_con_subject = rootview.findViewById(R.id.con_subject);
        txt_con_message = rootview.findViewById(R.id.con_message);
        btn_send_message = rootview.findViewById(R.id.send_con_message);
        progressDialog = new ProgressDialog(getContext());

        btn_send_message.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String name, email, phone, subject, message;
                name = String.valueOf(txt_con_name.getEditText().getText().toString());
                email = String.valueOf(txt_con_email.getEditText().getText().toString());
                phone = String.valueOf(txt_con_phone.getEditText().getText().toString());
                subject = String.valueOf(txt_con_subject.getEditText().getText().toString());
                message = String.valueOf(txt_con_message.getEditText().getText().toString());

                if (!validateEmail() | !validateName() | !validateMessage() | !validatePhone()) {
                    return;
                }
                progressDialog.setMessage("Please Wait");
                progressDialog.setCancelable(false);
                progressDialog.show();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[5];
                        field[0] = "name";
                        field[1] = "email";
                        field[2] = "phone";
                        field[3] = "subject";
                        field[4] = "message";
                        //Creating array for data
                        String[] data = new String[5];
                        data[0] = name;
                        data[1] = email;
                        data[2] = phone;
                        data[3] = subject;
                        data[4] = message;

                        PutData putData = new PutData(UrlProvider.webUrl+"contactUsform.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                //End ProgressBar (Set visibility to GONE)
                                progressDialog.dismiss();
                                if (result.equals(result)) {
                                    Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();


                                } else {
                                    Toast.makeText(getContext(), "Dear +name customer, thanks for reaching out!", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }
                        }
                        //End Write and Read data with URL
                    }
                });
            }


            private Boolean validateMessage() {
                String val = txt_con_message.getEditText().getText().toString();

                if (val.isEmpty()) {
                    txt_con_message.setError("Field cannot be empty");
                    return false;
                } else {
                    txt_con_message.setError(null);
                    return true;
                }
            }

            private Boolean validateName() {
                String val = txt_con_name.getEditText().getText().toString();

                if (val.isEmpty()) {
                    txt_con_name.setError("Field cannot be empty");
                    return false;
                } else {
                    txt_con_name.setError(null);
                    return true;
                }
            }

            private Boolean validateEmail() {
                String val = txt_con_email.getEditText().getText().toString();
                String emailPattern = "[a-zA-z0-9._]+@[a-z]+\\.+[a-z]+";

                if (val.isEmpty()) {
                    txt_con_email.setError("Field cannot be empty");
                    return false;
                } else if (!val.matches(emailPattern)) {
                    txt_con_email.setError("Invalid email address");
                    return false;
                } else {
                    txt_con_email.setError(null);
                    txt_con_email.setErrorEnabled(false);
                    return true;
                }
            }

            private Boolean validatePhone() {
                String val = txt_con_phone.getEditText().getText().toString();

                if (val.isEmpty()) {
                    txt_con_phone.setError("Field cannot be empty");
                    return false;
                } else {
                    txt_con_phone.setError(null);
                    txt_con_phone.setErrorEnabled(false);
                    return true;
                }
            }


        });

        return rootview;


    }

}