package com.infjz.prm392.slot9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.infjz.prm392.R;

import org.w3c.dom.Text;

import java.util.List;

public class CartAdapter extends ArrayAdapter<Product> {
    private Context mContext;
    public CartAdapter(Context context,List<Product> products) {
        super(context, 0, products);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null){
            listItem = LayoutInflater.from(mContext).inflate(R.layout.slot101_cart_items, parent, false);
        }
        //Get current product
        Product currentProduct = getItem(position);
        //Display product name in cart product
        TextView productName = listItem.findViewById(R.id.slot101_CartItems_TVProductName);
        productName.setText(currentProduct.getStyleID());
        //Display product quantity
        TextView productQuantity = listItem.findViewById(R.id.slot101_CartItems_TVProductQuantity);
        productQuantity.setText("Quantity: " + 1);
        return listItem;
    }
}
