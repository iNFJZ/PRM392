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

public class AddCustomerActivity extends AppCompatActivity {

    private EditText editTextCustomerName, editTextContactName, editTextAddress, editTextCity, editTextPostalCode, editTextCountry, editTextPhone;
    private Button buttonAddCustomer;
    private IStoreService storeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        editTextCustomerName = findViewById(R.id.editTextCustomerName);
        editTextContactName = findViewById(R.id.editTextContactName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextCity = findViewById(R.id.editTextCity);
        editTextPostalCode = findViewById(R.id.editTextPostalCode);
        editTextCountry = findViewById(R.id.editTextCountry);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonAddCustomer = findViewById(R.id.buttonAddCustomer);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.127/PRM392/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        storeService = retrofit.create(IStoreService.class);

        buttonAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerName = editTextCustomerName.getText().toString();
                String contactName = editTextContactName.getText().toString();
                String address = editTextAddress.getText().toString();
                String city = editTextCity.getText().toString();
                String postalCode = editTextPostalCode.getText().toString();
                String country = editTextCountry.getText().toString();
                String phone = editTextPhone.getText().toString();

                Call<ServerResponse> call = storeService.insertCustomer(customerName, contactName, address, city, postalCode, country, phone);
                call.enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Toast.makeText(AddCustomerActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK);
                            finish(); // Close the activity after successful insertion
                        } else {
                            Toast.makeText(AddCustomerActivity.this, "Failed to add customer", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        Toast.makeText(AddCustomerActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}