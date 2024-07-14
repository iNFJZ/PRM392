package com.infjz.prm392.slot11;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infjz.prm392.R;
import com.infjz.prm392.slot11.Model.Customer;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    private List<Customer> customerList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public CustomerAdapter(List<Customer> customerList, OnItemClickListener listener) {
        this.customerList = customerList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_item, parent, false);
        return new CustomerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        Customer currentCustomer = customerList.get(position);
        holder.textViewCustomerName.setText(currentCustomer.getCustomerName());
        holder.textViewCustomerContact.setText(currentCustomer.getContactName());
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public Customer getCustomerAtPosition(int position) {
        return customerList.get(position);
    }

    class CustomerViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewCustomerName;
        private TextView textViewCustomerContact;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCustomerName = itemView.findViewById(R.id.textViewCustomerName);
            textViewCustomerContact = itemView.findViewById(R.id.textViewCustomerContact);

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
