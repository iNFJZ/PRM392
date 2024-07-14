package com.infjz.prm392.slot11;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class ProductActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_ADD_PRODUCT = 1;
    ListView lvProducts;
    Button btnAddProduct;
    private IStoreService storeService;
    private ProductListAdapter adapter;

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

        loadProducts();

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, AddProductActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_PRODUCT);
            }
        });

        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = (Product) adapter.getItem(position);
                Intent intent = new Intent(ProductActivity.this, ProductDetailActivity.class);
                intent.putExtra("productID", selectedProduct.getProductID());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProducts();
    }

    private void loadProducts() {
        Call<ServerResponseProduct> call = storeService.getProducts();
        call.enqueue(new Callback<ServerResponseProduct>() {
            @Override
            public void onResponse(Call<ServerResponseProduct> call, Response<ServerResponseProduct> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> products = response.body().getProducts();
                    Log.d("ProductActivity", "Number of products: " + products.size());
                    adapter = new ProductListAdapter(ProductActivity.this, products);
                    lvProducts.setAdapter(adapter);
                } else {
                    Toast.makeText(ProductActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                    Log.d("ProductActivity", "Response not successful or body is null");
                }
            }

            @Override
            public void onFailure(Call<ServerResponseProduct> call, Throwable t) {
                Toast.makeText(ProductActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("ProductActivity", "API call failed: " + t.getMessage());
            }
        });
    }
}
