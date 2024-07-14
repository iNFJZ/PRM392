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
import com.infjz.prm392.slot11.Adapter.CustomerListAdapter;
import com.infjz.prm392.slot11.Model.Customer;
import com.infjz.prm392.slot11.ServerRespone.ServerResponseCustomer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_ADD_CUSTOMER = 1;
    ListView lvCustomers;
    Button btnAddCustomer;
    private IStoreService storeService;
    private CustomerListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        lvCustomers = findViewById(R.id.listViewCustomers);
        btnAddCustomer = findViewById(R.id.btnAddCustomer);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.127/PRM392/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        storeService = retrofit.create(IStoreService.class);

        loadCustomers();

        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerActivity.this, AddCustomerActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_CUSTOMER);
            }
        });

        lvCustomers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer selectedCustomer = (Customer) adapter.getItem(position);
                Intent intent = new Intent(CustomerActivity.this, CustomerDetailActivity.class);
                intent.putExtra("customerID", selectedCustomer.getCustomerID());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCustomers(); // Reload the customer list when returning to this activity
    }

    private void loadCustomers() {
        Call<ServerResponseCustomer> call = storeService.getCustomers();
        call.enqueue(new Callback<ServerResponseCustomer>() {
            @Override
            public void onResponse(Call<ServerResponseCustomer> call, Response<ServerResponseCustomer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Customer> customers = response.body().getCustomers();
                    Log.d("CustomerActivity", "Number of customers: " + customers.size());
                    adapter = new CustomerListAdapter(CustomerActivity.this, customers);
                    lvCustomers.setAdapter(adapter);
                } else {
                    Toast.makeText(CustomerActivity.this, "Failed to load customers", Toast.LENGTH_SHORT).show();
                    Log.d("CustomerActivity", "Response not successful or body is null");
                }
            }

            @Override
            public void onFailure(Call<ServerResponseCustomer> call, Throwable t) {
                Toast.makeText(CustomerActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("CustomerActivity", "API call failed: " + t.getMessage());
            }
        });
    }
}
