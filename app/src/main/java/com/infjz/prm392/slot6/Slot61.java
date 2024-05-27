package com.infjz.prm392.slot6;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.infjz.prm392.R;

import java.util.ArrayList;
import java.util.List;

public class Slot61 extends AppCompatActivity {
    EditText EDTProductCode, EDTProductName, EDTProductQuantity;
    Button BTNLoad, BTNAdd, BTNDelete, BTNUpdate;
    ListView LV;
    ProductDAO productDAO;
    ArrayAdapter<String> arrayAdapter;
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot61);
        EDTProductCode = findViewById(R.id.slot61EDTProductCode);
        EDTProductName = findViewById(R.id.slot61EDTProductName);
        EDTProductQuantity = findViewById(R.id.slot61EDTProductQuantity);
        BTNLoad = findViewById(R.id.slot61BTNLoad);
        BTNAdd = findViewById(R.id.slot61BTNAdd);
        BTNUpdate = findViewById(R.id.slot61BTNUpdate);
        BTNDelete = findViewById(R.id.slot61BTNDelete);
        LV = findViewById(R.id.slot61LV);
        productDAO = new ProductDAO(this);
        BTNAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product p = new Product();
                p.setProductCode(EDTProductCode.getText().toString());
                p.setProductName(EDTProductName.getText().toString());
                p.setProductQuantity(Integer.parseInt(EDTProductQuantity.getText().toString()));
                int result = productDAO.insertProduct(p);
                if (result == -1) {
                    Toast.makeText(getApplicationContext(), "Insert failed.", Toast.LENGTH_SHORT).show();
                    loadProductList();
                }
                if (result == 1) {
                    Toast.makeText(getApplicationContext(), "Insert successful.", Toast.LENGTH_SHORT).show();
                    loadProductList();
                }
            }
        });
        BTNDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = productDAO.deleteProduct(EDTProductCode.getText().toString());
                if (result == -1) {
                    Toast.makeText(getApplicationContext(), "Delete failed.", Toast.LENGTH_SHORT).show();
                    loadProductList();
                }
                if (result == 1) {
                    Toast.makeText(getApplicationContext(), "Delete successful.", Toast.LENGTH_SHORT).show();
                    loadProductList();
                }
            }
        });
        BTNUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product p = new Product();
                p.setProductCode(EDTProductCode.getText().toString());
                p.setProductName(EDTProductName.getText().toString());
                p.setProductQuantity(Integer.parseInt(EDTProductQuantity.getText().toString()));
                int result = productDAO.updateProduct(p);
                if (result == -1) {
                    Toast.makeText(getApplicationContext(), "Update failed.", Toast.LENGTH_SHORT).show();
                    loadProductList();
                }
                if (result == 1) {
                    Toast.makeText(getApplicationContext(), "Update successful.", Toast.LENGTH_SHORT).show();
                    loadProductList();
                }
            }
        });
        BTNLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadProductList();
            }
        });
    }
        private void loadProductList() {
            list.clear();
            list = productDAO.getAllProductToString();
            arrayAdapter = new ArrayAdapter<>(Slot61.this, android.R.layout.simple_list_item_1, list);
            LV.setAdapter(arrayAdapter);
        }
}