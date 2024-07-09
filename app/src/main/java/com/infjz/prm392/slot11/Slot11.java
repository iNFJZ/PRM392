package com.infjz.prm392.slot11;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.infjz.prm392.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Slot11 extends AppCompatActivity {
    EditText EDTName, EDTPrice, EDTDescription;
    TextView TVResult;
    Button BTNResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot11);
        EDTName = findViewById(R.id.slot11EDTName);
        EDTPrice = findViewById(R.id.slot11EDTPrice);
        EDTDescription = findViewById(R.id.slot11EDTDescription);
        TVResult = findViewById(R.id.slot11TVInsert);
        BTNResult = findViewById(R.id.slot11BTNInsert);
        BTNResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
    }

    private void insertData(){
        //1. Create a object for archive data
        Product product = new Product();
        //2. Set data to object
        double price = Double.parseDouble(EDTPrice.getText().toString());
        product.setName(EDTName.getText().toString());
        product.setPrice(price);
        product.setDescription(EDTDescription.getText().toString());
        //3. Create a retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.100.127/PRM392/create_product.php/").addConverterFactory(GsonConverterFactory.create()).build();
        //4. Call insert
        //4.1 Create an object
        IProduct iProduct = retrofit.create(IProduct.class);
        //4.2 Prepare function
        Call<ServerResponseProduct> call = iProduct.insertProduct(product.getName(),product.getPrice(),product.getDescription());
        //4.3 Execute function
        call.enqueue(new Callback<ServerResponseProduct>() {
            @Override
            public void onResponse(Call<ServerResponseProduct> call, Response<ServerResponseProduct> response) {
                ServerResponseProduct serverResponseProduct = response.body();
                TVResult.setText(serverResponseProduct.getMessage());
            }

            @Override
            public void onFailure(Call<ServerResponseProduct> call, Throwable throwable) {
                TVResult.setText(throwable.getMessage());
            }
        });
    }
}