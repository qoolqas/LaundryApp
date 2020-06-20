package com.example.laundryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.example.laundryapp.connection.Client;
import com.example.laundryapp.connection.Service;
import com.example.laundryapp.model.login.LoginResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout etEmail, etPassword;
    Button btnLogin;
    TextView register;
    Context mContext = this;
    SharedPrefManager sharedPrefManager;
    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPrefManager = new SharedPrefManager(this);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        ActivityCompat.requestPermissions(LoginActivity.this,
                new String[]{Manifest.permission.INTERNET, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                1);

        initComponents();

    }
    private void initComponents() {
        etEmail = findViewById(R.id.loginEtName);
        etPassword = findViewById(R.id.loginEtPassword);
        btnLogin = findViewById(R.id.loginButtonLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateName() | !validatePassword()) {
                    return;
                }
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestLogin();

            }
        });

    }

    private void requestLogin() {
        Client.getClient().create(Service.class).loginRequest(etEmail.getEditText().getText().toString(), etPassword.getEditText().getText().toString())
                .enqueue(new Callback<LoginResponse>() {

                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.body().isError()) {

                            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(mContext);

                            dlgAlert.setMessage("Mohon Periksa Kembali");
                            dlgAlert.setTitle("Email Atau Password Anda Salah");
                            dlgAlert.setPositiveButton("OK", null);
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();


                            dlgAlert.setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                            loading.dismiss();
                        } else {
                            loading.dismiss();
                            assert response.body() != null;

                            sharedPrefManager.saveEmail(SharedPrefManager.SP_EMAIL, Objects.requireNonNull(etEmail.getEditText()).getText().toString().trim());
                            sharedPrefManager.saveName(SharedPrefManager.SP_NAME, response.body().getUser().getNama());

                            Intent intent = new Intent(LoginActivity.this, DrawerMainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });
    }

    private boolean validateName() {
        String email = (Objects.requireNonNull(etEmail.getEditText())).getText().toString().trim();

        if (email.isEmpty()) {
            etEmail.setError("Field Ini Tidak Boleh Kosong");
            return false;
        } else {
            etEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String pw = Objects.requireNonNull(etPassword.getEditText()).getText().toString().trim();

        if (pw.isEmpty()) {
            etPassword.setError("Password tidak boleh kosong");
            return false;
        } else {
            etPassword.setError(null);
            return true;
        }
    }
}
