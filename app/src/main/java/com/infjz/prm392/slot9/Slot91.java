package com.infjz.prm392.slot9;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.infjz.prm392.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Slot91 extends AppCompatActivity {
    private ListView LVProduct;
    private Adapter adapter;
    private List<Product> productList;
    CartManager cartManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot91);
        LVProduct = findViewById(R.id.slot91LVProduct);
        cartManager = CartManager.getInstance();
        productList = new ArrayList<>();
        adapter = new Adapter(this, productList);
        LVProduct.setAdapter(adapter);
        new FetchProduct().execute();
    }

    private class FetchProduct extends AsyncTask<Void,Void,String>{
        //Read data from server
        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder response = new StringBuilder();
            try {
                URL url = new URL("http://192.168.100.237/PRM392/select.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line; //Read by line
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return response.toString();
        }

        //Return data to client
        @Override
        protected void onPostExecute(String s) {
            if (s != null && !s.isEmpty()) {
                try {
                    JSONObject json = new JSONObject(s);
                    JSONArray productsArray = json.getJSONArray("products");
                    for (int i = 0; i < productsArray.length(); i++){
                        JSONObject productsObject = productsArray.getJSONObject(i);
                        String styleID = productsObject.getString("styleid");
                        String brand = productsObject.getString("brands_filter_facet");
                        String price = productsObject.getString("price");
                        String info = productsObject.getString("product_additional_info");
                        String searchImage = productsObject.getString("search_image");
                        Product product = new Product(styleID, brand, price, info, searchImage);
                        productList.add(product);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}