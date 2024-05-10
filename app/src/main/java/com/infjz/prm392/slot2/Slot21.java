package com.infjz.prm392.slot2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.infjz.prm392.R;

public class Slot21 extends AppCompatActivity {
    EditText edtA, edtB;
    Button btnCalculate;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot21);
        edtA = findViewById(R.id.slot21edtA);
        edtB = findViewById(R.id.slot21edtB);
        btnCalculate = findViewById(R.id.slot21btnResult);
        tvResult = findViewById(R.id.slot21txtResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double a = Double.parseDouble(edtA.getText().toString());
                double b = Double.parseDouble(edtB.getText().toString());
                double result = a + b;
                tvResult.setText("RESULT: " + result);
            }
        });
    }
}