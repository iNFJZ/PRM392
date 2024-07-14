package com.infjz.prm392.slot11.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infjz.prm392.R;
import com.infjz.prm392.slot11.Model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public ProductAdapter(List<Product> productList, OnItemClickListener listener) {
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product currentProduct = productList.get(position);
        holder.tvProductName.setText(currentProduct.getProductName());
        holder.tvProductID.setText(String.valueOf(currentProduct.getProductID()));
        holder.tvUnitPrice.setText(String.valueOf(currentProduct.getUnitPrice()));
        holder.tvUnitsInStock.setText(String.valueOf(currentProduct.getUnitsInStock()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public Product getProductAtPosition(int position) {
        return productList.get(position);
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView tvProductName;
        private TextView tvProductID;
        private TextView tvUnitPrice;
        private TextView tvUnitsInStock;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductID = itemView.findViewById(R.id.tvProductID);
            tvUnitPrice = itemView.findViewById(R.id.tvUnitPrice);
            tvUnitsInStock = itemView.findViewById(R.id.tvUnitsInStock);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }
}
