package com.infjz.prm392.slot9;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.infjz.prm392.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Context context;
    private List<Product> productList;

    public Adapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        //Create layout
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.slot91_itemview,parent,false);
            holder = new ViewHolder();
            holder.IVSearchImage = convertView.findViewById(R.id.slot91IVSearchImage);
            holder.TVStyleID = convertView.findViewById(R.id.slot91TVStyleID);
            holder.TVBrand = convertView.findViewById(R.id.slot91TVBrand);
            holder.TVPrice = convertView.findViewById(R.id.slot91TVPrice);
            holder.TVInfo = convertView.findViewById(R.id.slot91TVInfo);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        //Set data
        Product product = productList.get(position);
        if (product != null){
            Picasso.get().load(product.getSearchImage()).into(holder.IVSearchImage);
            holder.TVStyleID.setText(product.getStyleID());
            holder.TVBrand.setText(product.getBrand());
            holder.TVPrice.setText(product.getPrice());
            holder.TVInfo.setText(product.getInfor());
        }
        //Event
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = productList.get(position);
                Intent intent = new Intent(context, Slot101.class);
                intent.putExtra("PRODUCT", product);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder{
        ImageView IVSearchImage;
        TextView TVStyleID, TVBrand, TVPrice, TVInfo;

    }
}
