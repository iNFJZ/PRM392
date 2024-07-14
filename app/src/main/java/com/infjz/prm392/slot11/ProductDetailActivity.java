package com.infjz.prm392.slot11;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.infjz.prm392.R;
import com.infjz.prm392.slot11.Model.Product;
import com.infjz.prm392.slot11.Model.ServerResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class ProductDetailActivity extends AppCompatActivity {

    private EditText edtProductID, edtProductName, edtSupplierID, edtCategoryID, edtUnitPrice, edtUnitsInStock;
    private Button btnUpdateProduct, btnDeleteProduct;
    private IStoreService storeService;
    private int productID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        initializeViews();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.127/PRM392/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        storeService = retrofit.create(IStoreService.class);

        productID = getIntent().getIntExtra("productID", 0);
        loadProductDetails(productID);

        btnUpdateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(edtProductID.getText().toString());
                String name = edtProductName.getText().toString();
                int supplierID = Integer.parseInt(edtSupplierID.getText().toString());
                int categoryID = Integer.parseInt(edtCategoryID.getText().toString());
                double unitPrice = Double.parseDouble(edtUnitPrice.getText().toString());
                int unitsInStock = Integer.parseInt(edtUnitsInStock.getText().toString());

                updateProduct(id, name, supplierID, categoryID, unitPrice, unitsInStock);
            }
        });

        btnDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ProductDetailActivity.this)
                        .setTitle("Delete Product")
                        .setMessage("Are you sure you want to delete this product?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                deleteProduct(productID);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    private void initializeViews() {
        edtProductID = findViewById(R.id.edtProductID);
        edtProductName = findViewById(R.id.edtProductName);
        edtSupplierID = findViewById(R.id.edtSupplierID);
        edtCategoryID = findViewById(R.id.edtCategoryID);
        edtUnitPrice = findViewById(R.id.edtUnitPrice);
        edtUnitsInStock = findViewById(R.id.edtUnitsInStock);
        btnUpdateProduct = findViewById(R.id.btnUpdateProduct);
        btnDeleteProduct = findViewById(R.id.btnDeleteProduct);
    }

    private void loadProductDetails(int productID) {
        Call<Product> call = storeService.getProductDetails(productID);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Product product = response.body();
                    edtProductID.setText(String.valueOf(product.getProductID()));
                    edtProductName.setText(product.getProductName());
                    edtSupplierID.setText(String.valueOf(product.getSupplierID()));
                    edtCategoryID.setText(String.valueOf(product.getCategoryID()));
                    edtUnitPrice.setText(String.valueOf(product.getUnitPrice()));
                    edtUnitsInStock.setText(String.valueOf(product.getUnitsInStock()));
                } else {
                    Toast.makeText(ProductDetailActivity.this, "Failed to load product details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateProduct(int id, String name, int supplierID, int categoryID, double unitPrice, int unitsInStock) {
        Call<ServerResponse> call = storeService.updateProduct(id, name, supplierID, categoryID, unitPrice, unitsInStock);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(ProductDetailActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ProductDetailActivity.this, "Failed to update product", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteProduct(int id) {
        Call<ServerResponse> call = storeService.deleteProduct(id);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(ProductDetailActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ProductDetailActivity.this, "Failed to delete product", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
