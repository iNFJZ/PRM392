package com.infjz.prm392.slot11.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.infjz.prm392.R;
import com.infjz.prm392.slot11.Model.Customer;

import java.util.List;

public class CustomerListAdapter extends BaseAdapter {

    private Context context;
    private List<Customer> customers;

    public CustomerListAdapter(Context context, List<Customer> customers) {
        this.context = context;
        this.customers = customers;
    }

    @Override
    public int getCount() {
        return customers.size();
    }

    @Override
    public Customer getItem(int position) {
        return customers.get(position);
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
            view = LayoutInflater.from(context).inflate(R.layout.customer_item, parent, false);
            holder = new ViewHolder();
            holder.customerImage = view.findViewById(R.id.imageViewCustomer);
            holder.customerName = view.findViewById(R.id.textViewCustomerName);
            holder.customerContact = view.findViewById(R.id.textViewCustomerContact);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Customer customer = customers.get(position);
        holder.customerName.setText(customer.getCustomerName());
        holder.customerContact.setText(customer.getContactName());
        // Assuming you have a placeholder image or method to load the customer's image
        holder.customerImage.setImageResource(R.drawable.hancock);

        return view;
    }

    private static class ViewHolder {
        ImageView customerImage;
        TextView customerName;
        TextView customerContact;
    }
}
