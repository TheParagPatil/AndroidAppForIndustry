package com.oneroofit.it.verification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.oneroofit.it.activities.MainActivity;
import com.oneroofit.it.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class LoginActivity extends AppCompatActivity {

    Button callRegister,login_btn,forgotPasswordbtn;
    ImageView image;
    private SessionManager session;
    TextView logoText, sloganText;
    TextInputLayout txt_log_email,txt_log_password;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        session = new SessionManager(getApplicationContext());
        callRegister = findViewById(R.id.register);
        image = findViewById(R.id.logoImage);
        logoText = findViewById(R.id.logoName);
        sloganText = findViewById(R.id.slogan_name);
        txt_log_email = findViewById(R.id.email);
        txt_log_password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login);
        forgotPasswordbtn = findViewById(R.id.forgetPassword);

        progressDialog = new ProgressDialog(LoginActivity.this);


        forgotPasswordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this,FPMakeSelection.class);
                startActivity(intent);
                finish();
            }
        });

        callRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);

                Pair[] pairs = new Pair[7];

                pairs[0] = new Pair<View,String>(image,"logo_image");
                pairs[1] = new Pair<View,String>(logoText,"logo_text");
                pairs[2] = new Pair<View,String>(sloganText,"logo_desc");
                pairs[3] = new Pair<View,String>(txt_log_email,"email_trans");
                pairs[4] = new Pair<View,String>(txt_log_password,"password_trans");
                pairs[5] = new Pair<View,String>(login_btn,"button_trans");
                pairs[6] = new Pair<View,String>(callRegister,"login_signup_trans");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this,pairs);
                    startActivity(intent,options.toBundle());
                }
            }
        });
        //Login Code

            login_btn.setOnClickListener(new View.OnClickListener() {

                private Boolean validatePassword() {
                    String val = txt_log_password.getEditText().getText().toString();
                    if (val.isEmpty()) {
                        txt_log_password.setError("Password cannot be empty");
                        return false;
                    }
                    else {
                        txt_log_password.setError(null);
                        txt_log_password.setErrorEnabled(false);
                        return true;
                    }
                }
                private Boolean validateEmail() {
                    String val = txt_log_email.getEditText().getText().toString();

                    if (val.isEmpty()) {
                        txt_log_email.setError("Email cannot be empty");
                        return false;
                    }
                    else{
                        txt_log_email.setError(null);
                        txt_log_email.setErrorEnabled(false);
                        return true;
                    }
                }


                @Override
                public void onClick(View v) {



                    String email,password;
                    email = (txt_log_email.getEditText().getText().toString());
                    password = (txt_log_password.getEditText().getText().toString());
                    if(!validatePassword() | !validateEmail()) {
                        return;
                    }
                    //Start ProgressBar first (Set visibility VISIBLE)
                    progressDialog.setMessage("Please Wait..");
                    progressDialog.show();
                    if (!isConnectedToInternet(LoginActivity.this)) {
                        showCustomDialog();
                    }
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "email";
                            field[1] = "password";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = email;
                            data[1] = password;

                            PutData putData = new PutData(UrlProvider.webUrl+"login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    //End ProgressBar (Set visibility to GONE)
                                    progressDialog.dismiss();
                                    if(result.equals("Success")){

                                        
                                        session.createLoginSession(password,email);

                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        finish();


                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }
                                }
                            }
                        }
                    });
                }
            });



    }

    private void showCustomDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("Please connect to the internet")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                    }
                });
    }

    private boolean isConnectedToInternet(LoginActivity loginActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) loginActivity.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiConn != null && wifiConn.isConnected() || (mobileConn != null && mobileConn.isConnected()))) {
            return true;
        } else {
            return false;
        }
    }
}
