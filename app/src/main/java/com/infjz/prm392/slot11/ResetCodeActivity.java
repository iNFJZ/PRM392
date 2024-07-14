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

public class ResetCodeActivity extends AppCompatActivity {

    EditText EDTCode;
    Button BTNVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_code);

        EDTCode = findViewById(R.id.resetEDTCode);
        BTNVerify = findViewById(R.id.resetBTNVerify);

        BTNVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyCode();
            }
        });
    }

    public void verifyCode() {
        String code = EDTCode.getText().toString().trim();
        Log.d("ResetCodeActivity", "Code: " + code);

        if (code.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Code is empty", Toast.LENGTH_LONG).show();
            return;
        }

        IStoreService iStoreService = RetrofitClient.getClient("http://192.168.100.127/PRM392/").create(IStoreService.class);
        Call<ServerResponseLogin> call = iStoreService.verifyResetCode(code);
        call.enqueue(new Callback<ServerResponseLogin>() {
            @Override
            public void onResponse(Call<ServerResponseLogin> call, Response<ServerResponseLogin> response) {
                if (response.isSuccessful() && !response.body().isError()) {
                    Toast.makeText(getApplicationContext(), "Code Verified", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ResetCodeActivity.this, NewPasswordActivity.class);
                    intent.putExtra("code", code);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid or expired code", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponseLogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                Log.e("ResetCodeActivity", "Error: " + t.getMessage(), t);
            }
        });
    }
}
