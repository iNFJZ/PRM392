package com.infjz.prm392.slot11.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.infjz.prm392.R;
import com.infjz.prm392.slot11.Model.Product;

import java.util.List;

public class ProductListAdapter extends BaseAdapter {

    private Context context;
    private List<Product> products;

    public ProductListAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_product_list, parent, false);
            holder = new ViewHolder();
            holder.productName = view.findViewById(R.id.tvProductName);
            holder.productID = view.findViewById(R.id.tvProductID);
            holder.unitPrice = view.findViewById(R.id.tvUnitPrice);
            holder.unitsInStock = view.findViewById(R.id.tvUnitsInStock);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Product product = products.get(position);
        holder.productName.setText(product.getProductName());
        holder.productID.setText(String.valueOf(product.getProductID()));
        holder.unitPrice.setText(String.valueOf(product.getUnitPrice()));
        holder.unitsInStock.setText(String.valueOf(product.getUnitsInStock()));

        return view;
    }

    private static class ViewHolder {
        TextView productName;
        TextView productID;
        TextView unitPrice;
        TextView unitsInStock;
    }
}
