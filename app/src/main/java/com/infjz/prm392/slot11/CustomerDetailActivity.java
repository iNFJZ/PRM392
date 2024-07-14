package com.infjz.prm392.slot11;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.infjz.prm392.R;
import com.infjz.prm392.slot11.Model.Customer;
import com.infjz.prm392.slot11.Model.ServerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.app.AlertDialog;
import android.content.DialogInterface;


public class CustomerDetailActivity extends AppCompatActivity {

    private EditText edtCustomerID, edtCustomerName, edtCustomerContact, edtCustomerAddress, edtCustomerCity, edtCustomerPostalCode, edtCustomerCountry, edtCustomerPhone;
    private Button btnUpdateCustomer, btnDeleteCustomer;
    private IStoreService storeService;
    private int customerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);

        initializeViews();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.127/PRM392/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        storeService = retrofit.create(IStoreService.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            customerID = extras.getInt("customerID");
            loadCustomerDetail(customerID);
        }

        btnUpdateCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCustomer();
            }
        });

        btnDeleteCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCustomer();
            }
        });
    }

    private void initializeViews() {
        edtCustomerID = findViewById(R.id.edtCustomerID);
        edtCustomerName = findViewById(R.id.edtCustomerName);
        edtCustomerContact = findViewById(R.id.edtCustomerContact);
        edtCustomerAddress = findViewById(R.id.edtCustomerAddress);
        edtCustomerCity = findViewById(R.id.edtCustomerCity);
        edtCustomerPostalCode = findViewById(R.id.edtCustomerPostalCode);
        edtCustomerCountry = findViewById(R.id.edtCustomerCountry);
        edtCustomerPhone = findViewById(R.id.edtCustomerPhone);
        btnUpdateCustomer = findViewById(R.id.btnUpdateCustomer);
        btnDeleteCustomer = findViewById(R.id.btnDeleteCustomer);
    }

    private void loadCustomerDetail(int customerID) {
        Call<Customer> call = storeService.getCustomerDetails(customerID);
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    populateCustomerDetails(response.body());
                } else {
                    Toast.makeText(CustomerDetailActivity.this, "Failed to load customer details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Toast.makeText(CustomerDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateCustomerDetails(Customer customer) {
        edtCustomerID.setText(String.valueOf(customer.getCustomerID()));
        edtCustomerName.setText(customer.getCustomerName());
        edtCustomerContact.setText(customer.getContactName());
        edtCustomerAddress.setText(customer.getAddress());
        edtCustomerCity.setText(customer.getCity());
        edtCustomerPostalCode.setText(customer.getPostalCode());
        edtCustomerCountry.setText(customer.getCountry());
        edtCustomerPhone.setText(customer.getPhone());
    }

    private void updateCustomer() {
        String customerName = edtCustomerName.getText().toString();
        String contactName = edtCustomerContact.getText().toString();
        String address = edtCustomerAddress.getText().toString();
        String city = edtCustomerCity.getText().toString();
        String postalCode = edtCustomerPostalCode.getText().toString();
        String country = edtCustomerCountry.getText().toString();
        String phone = edtCustomerPhone.getText().toString();

        Call<ServerResponse> call = storeService.updateCustomer(customerID, customerName, contactName, address, city, postalCode, country, phone);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(CustomerDetailActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(CustomerDetailActivity.this, "Failed to update customer", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(CustomerDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteCustomer() {
        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(CustomerDetailActivity.this);
        builder.setMessage("Are you sure you want to delete this customer?");
        builder.setTitle("Delete Customer");

        // Set the positive button with click listener
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Proceed with deletion
                Call<ServerResponse> call = storeService.deleteCustomer(customerID);
                call.enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Toast.makeText(CustomerDetailActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            Toast.makeText(CustomerDetailActivity.this, "Failed to delete customer", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        Toast.makeText(CustomerDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Set the negative button with click listener
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Cancel deletion
                dialog.dismiss();
            }
        });

        // Create and show the alert dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}