package com.infjz.prm392.slot11;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.infjz.prm392.slot11.Model.Login;
import androidx.appcompat.app.AppCompatActivity;

import com.infjz.prm392.R;
import com.infjz.prm392.slot11.ServerRespone.ServerResponseLogin;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    EditText EDTUsername, EDTPassword;

    Button BTNLogin, BTNCancel, BTNForgetPassword;
    CheckBox CBSavePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EDTUsername = findViewById(R.id.loginEDTUsername);
        EDTPassword = findViewById(R.id.loginEDTPassword);
        BTNLogin = findViewById(R.id.loginBTNLogin);
        CBSavePass = findViewById(R.id.loginCBSavePass);
        BTNForgetPassword = findViewById(R.id.loginBTNForgetPassword);
        BTNForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });

        BTNLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        resetPassword();
    }

    public void login() {
        String username = EDTUsername.getText().toString();
        String password = EDTPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Username or Password is empty", Toast.LENGTH_LONG).show();
            return;
        }

        IStoreService iStoreService = RetrofitClient.getClient("http://192.168.100.127/PRM392/").create(IStoreService.class);
        Login login = new Login(username, password);

        Call<ServerResponseLogin> call = iStoreService.login(login);
        call.enqueue(new Callback<ServerResponseLogin>() {
            @Override
            public void onResponse(Call<ServerResponseLogin> call, Response<ServerResponseLogin> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ServerResponseLogin serverResponseLogin = response.body();
                    Log.d(TAG, "Response: " + serverResponseLogin.getMessage());
                    if (!serverResponseLogin.isError()) {
                        saveDataToPreference(username, password, CBSavePass.isChecked());
                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, Slot11.class));
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), serverResponseLogin.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to login. Please try again.", Toast.LENGTH_LONG).show();
                    Log.e(TAG, "Response not successful: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ServerResponseLogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Login Failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e(TAG, "Login Failed: ", t);
            }
        });
    }

    public void saveDataToPreference(String username, String password, boolean status) {
        SharedPreferences sharedPreferences = getSharedPreferences("H_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!status) {
            editor.clear();
        } else {
            editor.putString("USERNAME", username);
            editor.putString("PASSWORD", password);
            editor.putBoolean("REMEMBER", status);
        }
        editor.apply();
    }

    public List<Object> resetPassword() {
        List<Object> list = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("H_FILE", MODE_PRIVATE);
        boolean check = sharedPreferences.getBoolean("REMEMBER", false);
        if (check) {
            String username = sharedPreferences.getString("USERNAME", "");
            EDTUsername.setText(username);
            String password = sharedPreferences.getString("PASSWORD", "");
            EDTPassword.setText(password);
            list.add(username);
            list.add(password);
            list.add(check);
        }
        CBSavePass.setChecked(check);
        return list;
    }
}
