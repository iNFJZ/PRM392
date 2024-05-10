package com.infjz.prm392.slot2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.infjz.prm392.R;

public class Slot22 extends AppCompatActivity {
    EditText edtA, edtB;
    Button btnResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot22);
        //Anh xa Id
        edtA = findViewById(R.id.slot22edtA);
        edtB = findViewById(R.id.slot22edtB);
        btnResult = findViewById(R.id.slot22btnResult);
        //Xu ly Click
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lay du lieu
                double a = Double.parseDouble(edtA.getText().toString());
                double b = Double.parseDouble(edtB.getText().toString());
                double result = a + b;
                //Khai bao Intent
                Intent myIntent = new Intent(Slot22.this, Slot23.class);
                myIntent.putExtra("RESULT", result);
                startActivity(myIntent);
            }
        });
    }
}