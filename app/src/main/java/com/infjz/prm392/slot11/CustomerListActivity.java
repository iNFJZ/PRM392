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

public class CustomerListActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_CUSTOMER = 1;

    private ListView lvCustomers;
    private Button btnAddCustomer;
    private CustomerListAdapter mAdapter;
    private IStoreService storeService;

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

        lvCustomers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer customer = (Customer) mAdapter.getItem(position);
                if (customer != null) {
                    Intent intent = new Intent(CustomerListActivity.this, CustomerDetailActivity.class);
                    intent.putExtra("customerID", customer.getCustomerID());
                    startActivity(intent);
                }
            }
        });

        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerListActivity.this, AddCustomerActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_CUSTOMER);
            }
        });

        getCustomers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCustomers();
    }

    private void getCustomers() {
        Call<ServerResponseCustomer> call = storeService.getCustomers();
        call.enqueue(new Callback<ServerResponseCustomer>() {
            @Override
            public void onResponse(Call<ServerResponseCustomer> call, Response<ServerResponseCustomer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Customer> customers = response.body().getCustomers();
                    mAdapter = new CustomerListAdapter(CustomerListActivity.this, customers);
                    lvCustomers.setAdapter(mAdapter);
                } else {
                    Toast.makeText(CustomerListActivity.this, "Failed to fetch customers", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponseCustomer> call, Throwable t) {
                Toast.makeText(CustomerListActivity.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
