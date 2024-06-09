package com.infjz.prm392.slot9;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.infjz.prm392.R;

import java.util.List;

public class Slot101Cart extends AppCompatActivity {
    private ListView listView;
    private CartAdapter cartAdapter;
    CartManager cartManager; //Gio hang
    List<Product> cartItems; //San pham trong gio hang
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot101_cart);
        listView = findViewById(R.id.slot101LVCart);
        // ----
        cartManager = CartManager.getInstance();
        cartItems = cartManager.getCartItems();
        // ----
        cartAdapter = new CartAdapter(this, cartItems);
        listView.setAdapter(cartAdapter);
    }
}