package com.infjz.prm392.slot5;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.infjz.prm392.R;

import java.util.List;

public class Adapter extends BaseAdapter {
    private List<Product> mList;
    private Context mContext;

    public Adapter(List<Product> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            //Create a blank view
            convertView = LayoutInflater.from(mContext).inflate(R.layout.slot51_item_view,parent,false);
            //Refer ids of item_view
            holder = new ViewHolder();
            holder.IVImage = convertView.findViewById(R.id.slot51IVImage);
            holder.TVId = convertView.findViewById(R.id.slot51TVId);
            holder.TVName = convertView.findViewById(R.id.slot51TVName);
            holder.TVPrice = convertView.findViewById(R.id.slot51TVPrice);
            //Create a template for later
            convertView.setTag(holder);
        }
        //If exists view -> get view
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        //Set data
        Product product = mList.get(position);
        if(product != null) {
            holder.IVImage.setImageResource(R.drawable.apple);
            holder.TVId.setText(product.getId());
            holder.TVName.setText(product.getName());
            holder.TVPrice.setText(String.valueOf(product.getPrice()));
        }
        return convertView;
    }

    static class ViewHolder{
        ImageView IVImage;
        TextView TVId, TVName, TVPrice;
    }
}
