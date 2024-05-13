package com.infjz.prm392.slot3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.infjz.prm392.R;

import org.w3c.dom.Text;

public class Slot33 extends AppCompatActivity {
    EditText EDTA, EDTB, EDTC;
    Button BTNCalculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot33);
        EDTA = findViewById(R.id.slot33EDTA);
        EDTB = findViewById(R.id.slot33EDTB);
        EDTC = findViewById(R.id.slot33EDTC);
        BTNCalculate = findViewById(R.id.slot33BTNCalculate);
        //Handling event
        BTNCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });
    }

    private void sendData(){
        //Get data from input of user
        String a = EDTA.getText().toString();
        String b = EDTB.getText().toString();
        String c = EDTC.getText().toString();
        //Create intent
        Intent myIntent = new Intent(Slot33.this, Slot34.class);
        myIntent.putExtra("a", a);
        myIntent.putExtra("b", b);
        myIntent.putExtra("c", c);
        startActivity(myIntent);

    }
}