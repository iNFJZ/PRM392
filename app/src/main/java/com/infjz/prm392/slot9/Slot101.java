package com.infjz.prm392.slot9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.infjz.prm392.R;
import com.squareup.picasso.Picasso;

public class Slot101 extends AppCompatActivity {
    private TextView TVStyleID, TVBrand, TVPrice, TVInfor;
    private ImageView IVSearchImage;
    private Button BTNAddToCart;
    Intent intent;
    Product product;
    CartManager cartManager;//Gio hang
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot101);
        TVStyleID = findViewById(R.id.slot101TVStyleID);
        TVBrand = findViewById(R.id.slot101TVBrand);
        TVPrice = findViewById(R.id.slot101TVPrice);
        TVInfor = findViewById(R.id.slot101TVInfor);
        IVSearchImage = findViewById(R.id.slot101IVSearchImage);
        BTNAddToCart = findViewById(R.id.slot101BTNAddToCart);
        // ----
        cartManager = CartManager.getInstance();
        // ----
        intent = getIntent(); //get data from listProduct
        product = intent.getParcelableExtra("PRODUCT");
        if (product != null){
            Picasso.get().load(product.getSearchImage()).into(IVSearchImage);
            TVStyleID.setText(product.getStyleID());
            TVBrand.setText(product.getBrand());
            TVPrice.setText(product.getPrice());
            TVInfor.setText(product.getInfor());
        }
        BTNAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });
    }
    private void addToCart(){
        Intent intent = getIntent();
        Product product = intent.getParcelableExtra("PRODUCT");
        if (product != null) {
            cartManager.addProductToCart(product); //Them san pham vao gio hang
            //Mo cartActivity
            Intent intent1 = new Intent(this, Slot101Cart.class);
            startActivity(intent1);
        }
    }
}