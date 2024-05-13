package com.infjz.prm392.slot3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.infjz.prm392.R;

public class Slot31 extends AppCompatActivity {
    EditText EDTUsername, EDTPassword;
    Button BTNLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot31);
        //refer widget to id in XML layout
        EDTUsername = findViewById(R.id.slot31EDTUsername);
        EDTPassword = findViewById(R.id.slot31EDTPassword);
        BTNLogin = findViewById(R.id.slot31BTNLogin);
        BTNLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login(){
        if(EDTUsername.getText().toString().equals("admin") && EDTPassword.getText().toString().equals("123456")){
            Toast.makeText(Slot31.this, "Login Successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(Slot31.this, "Username or Password wrong!!", Toast.LENGTH_SHORT).show();
        }
    }
}