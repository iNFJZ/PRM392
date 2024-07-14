package com.infjz.prm392.slot11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.infjz.prm392.R;

public class Slot11 extends AppCompatActivity {

    Button btnNavigateToProduct, btnNavigateToCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot11);

        btnNavigateToCustomer = findViewById(R.id.btnNavigateToCustomer);
        btnNavigateToProduct = findViewById(R.id.btnNavigateToProduct);
        btnNavigateToCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Slot11.this, CustomerListActivity.class);
                startActivity(intent);
            }
        });

        btnNavigateToProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Slot11.this, ProductListActivity.class);
                startActivity(intent);
            }
        });
    }
}
