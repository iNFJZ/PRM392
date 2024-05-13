package com.infjz.prm392.slot3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.infjz.prm392.R;

public class Slot34 extends AppCompatActivity {
    TextView TVResult;
    Button BTNBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot34);
        TVResult = findViewById(R.id.slot34TVResult);
        BTNBack = findViewById(R.id.slot34BTNBack);
        //Get data from intent
        Intent myIntent = getIntent();
        //Extract data
        int a = Integer.parseInt(myIntent.getExtras().getString("a"));
        int b = Integer.parseInt(myIntent.getExtras().getString("b"));
        int c = Integer.parseInt(myIntent.getExtras().getString("c"));
        //Calculate
        float delta = (b*b)-(4*a*c);
        if(delta < 0){
            TVResult.setText("Phương trình vô nghiệm!");
        } else if (delta == 0) {
            TVResult.setText("Phương trình có nghiệm kép x = "+ (-b)/(2*a));
        }else{
            float x1 = (float) ((-b+Math.sqrt(delta))/(2*a));
            float x2 = (float) ((-b-Math.sqrt(delta))/(2*a));
            TVResult.setText("Phương trình có 2 nghiệm x1 = "+x1+" và x2 = "+x2);
        }

        BTNBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}