package com.infjz.prm392.slot11;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.infjz.prm392.R;
import com.infjz.prm392.slot11.ServerRespone.ServerResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText EDTEmail;
    Button BTNSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        EDTEmail = findViewById(R.id.forgotEDTEmail);
        BTNSubmit = findViewById(R.id.forgotBTNSubmit);

        BTNSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitEmail();
            }
        });
    }

    public void submitEmail() {
        String email = EDTEmail.getText().toString().trim();
        Log.d("ForgotPasswordActivity", "Email: " + email);

        if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Email is empty", Toast.LENGTH_LONG).show();
            return;
        }

        IStoreService iStoreService = RetrofitClient.getClient("http://192.168.100.127/PRM392/").create(IStoreService.class);
        Call<ServerResponseLogin> call = iStoreService.sendResetCode(email);
        call.enqueue(new Callback<ServerResponseLogin>() {
            @Override
            public void onResponse(Call<ServerResponseLogin> call, Response<ServerResponseLogin> response) {
                if (response.isSuccessful() && !response.body().isError()) {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ForgotPasswordActivity.this, ResetCodeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Failed: " + response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponseLogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("ForgotPasswordActivity", "Error: " + t.getMessage(), t);
            }
        });
    }
}
