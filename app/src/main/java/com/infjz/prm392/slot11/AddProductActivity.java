package com.infjz.prm392.slot11;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.infjz.prm392.R;
import com.infjz.prm392.slot11.Model.ServerResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddProductActivity extends AppCompatActivity {

    private EditText editTextProductName, editTextSupplierID, editTextCategoryID, editTextUnitPrice, editTextUnitsInStock;
    private Button buttonAddProduct;
    private IStoreService storeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        editTextProductName = findViewById(R.id.editTextProductName);
        editTextSupplierID = findViewById(R.id.editTextSupplierID);
        editTextCategoryID = findViewById(R.id.editTextCategoryID);
        editTextUnitPrice = findViewById(R.id.editTextUnitPrice);
        editTextUnitsInStock = findViewById(R.id.editTextUnitsInStock);
        buttonAddProduct = findViewById(R.id.buttonAddProduct);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.127/PRM392/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        storeService = retrofit.create(IStoreService.class);

        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = editTextProductName.getText().toString();
                int supplierID = Integer.parseInt(editTextSupplierID.getText().toString());
                int categoryID = Integer.parseInt(editTextCategoryID.getText().toString());
                double unitPrice = Double.parseDouble(editTextUnitPrice.getText().toString());
                int unitsInStock = Integer.parseInt(editTextUnitsInStock.getText().toString());

                Call<ServerResponse> call = storeService.insertProduct(productName, supplierID, categoryID, unitPrice, unitsInStock);
                call.enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Toast.makeText(AddProductActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK);
                            finish(); // Close the activity after successful insertion
                        } else {
                            Toast.makeText(AddProductActivity.this, "Failed to add product", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        Toast.makeText(AddProductActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
