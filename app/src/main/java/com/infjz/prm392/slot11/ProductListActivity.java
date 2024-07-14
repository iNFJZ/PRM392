package com.infjz.prm392.slot11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.infjz.prm392.R;
import com.infjz.prm392.slot11.Adapter.ProductListAdapter;
import com.infjz.prm392.slot11.Model.Product;
import com.infjz.prm392.slot11.ServerRespone.ServerResponseProduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductListActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_PRODUCT = 1;

    private ListView lvProducts;
    private Button btnAddProduct;
    private ProductListAdapter mAdapter;
    private IStoreService storeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        lvProducts = findViewById(R.id.listViewProducts);
        btnAddProduct = findViewById(R.id.btnAddProduct);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.127/PRM392/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        storeService = retrofit.create(IStoreService.class);

        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = mAdapter.getItem(position);
                if (product != null) {
                    Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                    intent.putExtra("productID", product.getProductID());
                    startActivity(intent);
                }
            }
        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductListActivity.this, AddProductActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_PRODUCT);
            }
        });

        getProducts();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getProducts();
    }

    private void getProducts() {
        Call<ServerResponseProduct> call = storeService.getProducts();
        call.enqueue(new Callback<ServerResponseProduct>() {
            @Override
            public void onResponse(Call<ServerResponseProduct> call, Response<ServerResponseProduct> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> products = response.body().getProducts();
                    mAdapter = new ProductListAdapter(ProductListActivity.this, products);
                    lvProducts.setAdapter(mAdapter);
                } else {
                    Toast.makeText(ProductListActivity.this, "Failed to fetch products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponseProduct> call, Throwable t) {
                Toast.makeText(ProductListActivity.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
