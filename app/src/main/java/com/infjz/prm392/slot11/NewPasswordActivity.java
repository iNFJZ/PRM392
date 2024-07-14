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
import com.infjz.prm392.slot11.Model.Account;
import com.infjz.prm392.slot11.ServerRespone.ServerResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPasswordActivity extends AppCompatActivity {

    EditText EDTNewPassword, EDTConfirmPassword;
    Button BTNSubmit;
    String resetCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        EDTNewPassword = findViewById(R.id.newPasswordEDT);
        EDTConfirmPassword = findViewById(R.id.confirmPasswordEDT);
        BTNSubmit = findViewById(R.id.newPasswordBTNSubmit);

        resetCode = getIntent().getStringExtra("code");

        BTNSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword();
            }
        });
    }

    public void updatePassword() {
        String newPassword = EDTNewPassword.getText().toString().trim();
        String confirmPassword = EDTConfirmPassword.getText().toString().trim();
        Log.d("NewPasswordActivity", "New Password: " + newPassword);
        Log.d("NewPasswordActivity", "Confirm Password: " + confirmPassword);

        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Passwords cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
            return;
        }

        IStoreService iStoreService = RetrofitClient.getClient("http://192.168.100.127/PRM392/").create(IStoreService.class);
        Account account = new Account(null, null, newPassword);
        account.setCode(resetCode);  // Ensure `Account` class has this method

        Call<ServerResponseLogin> call = iStoreService.updatePassword(account);
        call.enqueue(new Callback<ServerResponseLogin>() {
            @Override
            public void onResponse(Call<ServerResponseLogin> call, Response<ServerResponseLogin> response) {
                if (response.isSuccessful() && !response.body().isError()) {
                    Toast.makeText(getApplicationContext(), "Password updated successfully", Toast.LENGTH_LONG).show();

                    // Redirect to LoginActivity
                    Intent intent = new Intent(NewPasswordActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to update password", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponseLogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                Log.e("NewPasswordActivity", "Error: " + t.getMessage(), t);
            }
        });
    }
}
