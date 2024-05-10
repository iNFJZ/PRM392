package com.infjz.prm392.slot2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.infjz.prm392.R;

public class Slot23 extends AppCompatActivity {
    TextView tvResult;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot23);
        tvResult = findViewById(R.id.slot23txtResult);
        btnBack = findViewById(R.id.slot23btnBack);
        Intent myIntent = getIntent();
        double result = myIntent.getDoubleExtra("RESULT", 0);
        tvResult.setText("Result: " + result);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}