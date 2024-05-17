package com.infjz.prm392.slot4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.infjz.prm392.R;

import java.util.List;

public class Slot41BaseAdapter extends BaseAdapter {
    private Context context;
    private List<Slot41Student> list;

    public Slot41BaseAdapter(Context context, List<Slot41Student> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Create a blank view and Refer to view holder
    //Set data for view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1. Create view
        Slot41ViewHolder holder;
        if (convertView == null){ //If view is not exist -> create new view
            //Create a blank view
            convertView = LayoutInflater.from(context).inflate(R.layout.slot41_item_view, parent, false);
            //Refer to the view holder
            holder = new Slot41ViewHolder();
            holder.IMGImage = convertView.findViewById(R.id.slot41_itemview_image);
            holder.TVName = convertView.findViewById(R.id.slot_41_itemview_name);
            holder.TVAge = convertView.findViewById(R.id.slot_41_itemview_age);
            //Create a template for later
            convertView.setTag(holder);
        }
        else{ //If view is exist -> get view
            holder = (Slot41ViewHolder) convertView.getTag();
        }
        //2. Set data
        Slot41Student student = list.get(position); //Get a object
        holder.IMGImage.setImageResource(student.getStudentImage());
        holder.TVName.setText(student.getStudentName());
        holder.TVAge.setText(student.getStudentAge());
        return convertView;
    }

    //Create a class for reference to components of item view: view holder
    static class Slot41ViewHolder {
        ImageView IMGImage;
        TextView TVName, TVAge;
    }
}
