package com.oneroofit.it.verification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.oneroofit.it.R;
import com.oneroofit.it.activities.MainActivity;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class RegistrationActivity extends AppCompatActivity {
    Button callLoginbtn;
    TextInputLayout txt_reg_confirm_password, txt_reg_phone, txt_reg_email, txt_reg_password, txt_reg_name;
    ProgressDialog progressDialog;
    Button btn_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);

        txt_reg_name = findViewById(R.id.reg_name);
        txt_reg_confirm_password = findViewById(R.id.reg_confirm_password);
        txt_reg_email = findViewById(R.id.reg_email);
        txt_reg_phone = findViewById(R.id.reg_phone);
        txt_reg_password = findViewById(R.id.reg_password);
        btn_signup = findViewById(R.id.signupbtn);
        callLoginbtn = findViewById(R.id.loginbtn);
        progressDialog = new ProgressDialog(RegistrationActivity.this);


        btn_signup.setOnClickListener(new View.OnClickListener() {

            private Boolean validateName() {
                String val = txt_reg_name.getEditText().getText().toString();

                if (val.isEmpty()) {
                    txt_reg_name.setError("Field cannot be empty");
                    return false;
                } else {
                    txt_reg_name.setError(null);
                    return true;
                }
            }

            private Boolean validateEmail() {
                String val = txt_reg_email.getEditText().getText().toString();
                String emailPattern = "[a-zA-z0-9._]+@[a-z]+\\.+[a-z]+";

                if (val.isEmpty()) {
                    txt_reg_email.setError("Field cannot be empty");
                    return false;
                } else if(!val.matches(emailPattern)){
                    txt_reg_email.setError("Invalid email address");
                    return false;
                }
                else {
                    txt_reg_email.setError(null);
                    txt_reg_email.setErrorEnabled(false);
                    return true;
                }
            }

            private Boolean validatePassword() {
                String val = txt_reg_password.getEditText().getText().toString();

                String val1 = txt_reg_confirm_password.getEditText().getText().toString();
                String passwordVal = "^" +
                        "(?=.*[0-9])"+              //At least 1 number
                        "(?=.*[a-z])" +                //atleast 1 lower case
                        "(?=.*[A-Z])" +             //at least 1 upper case letter
                        "(?=.*[a-zA-Z])" +         //at least 1 lower case
                        "(?=.*[@#$%^&+=])" +
                        "(?=\\S+$)" +            //any letters
                        ".{4,}" +               //no white spaces
                        "$";                    //atleast 4 characters

                if(!val1.equals(val)  ){
                    Toast.makeText(RegistrationActivity.this, "Confirm password should be similar to password!", Toast.LENGTH_SHORT).show();
                    return false;
                }
                else if (val.isEmpty()) {
                    txt_reg_password.setError("Field cannot be empty");
                    return false;
                } else if(!val.matches(passwordVal)){
                    txt_reg_password.setError("Password is too weak");
                    return false;
                }
                else {
                    txt_reg_password.setError(null);
                    txt_reg_password.setErrorEnabled(false);
                    return true;
                }
            }

            private Boolean validatePhone() {
                String val = txt_reg_phone.getEditText().getText().toString();

                if (val.isEmpty()) {
                    txt_reg_phone.setError("Field cannot be empty");
                    return false;
                } else {
                    txt_reg_phone.setError(null);
                    txt_reg_phone.setErrorEnabled(false);
                    return true;
                }
            }



            @Override
            public void onClick(View v) {
                String name, username, email, phone,password,rec_password;
                name = String.valueOf(txt_reg_name.getEditText().getText().toString());
                email = String.valueOf(txt_reg_email.getEditText().getText().toString());
                phone = String.valueOf(txt_reg_phone.getEditText().getText().toString());
                password = String.valueOf(txt_reg_password.getEditText().getText().toString());
                rec_password = String.valueOf(txt_reg_confirm_password.getEditText().getText().toString());




                //if(!name.equals("") && !username.equals("") && !email.equals("") && !phone.equals("") && !password.equals("")) {
                if(!validateEmail() | !validateName() | !validatePassword() | !validatePhone()) {
                    return;
                }

                //Start ProgressBar first (Set visibility VISIBLE)
                progressDialog.setMessage("Please Wait");
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
                            field[3] = "password";
                            field[4] = "recpassword";
                            //Creating array for data
                            String[] data = new String[5];
                            data[0] = name;
                            data[1] = email;
                            data[2] = phone;
                            data[3] = password;
                            data[4] = rec_password;

                            PutData putData = new PutData(UrlProvider.webUrl +"User-Registration.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    //End ProgressBar (Set visibility to GONE)
                                    progressDialog.dismiss();
                                    if (result.equals(result)) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(intent);
                                        finish();


                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }

        });


        callLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}