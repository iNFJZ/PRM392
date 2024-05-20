package com.infjz.prm392.slot5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private SQLiteHelper sqlite;
    private SQLiteDatabase db;
    private Context context;
    //Create database and table
    public ProductDAO(Context context){
        this.context = context;
        sqlite = new SQLiteHelper(context);
        db = sqlite.getWritableDatabase();
    }
    //Insert
    public int insertProduct(Product p){
        //Data for inserting
        ContentValues values = new ContentValues();
        //Put data
        values.put("id", p.getId());
        values.put("name", p.getName());
        values.put("price", p.getPrice());
        //Insert
        if(db.insert("Product", null, values) < 0){
            //Unsuccessful
            return -1;
        }
        //Successful
        return 1;
    }
    //Get data
    public List<Product> getAll(){
        List<Product> list = new ArrayList<>();
        //Refer a cursor for reading database
        Cursor c = db.query("Product", null, null ,null ,null ,null ,null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            Product product = new Product();
            product.setId(c.getString(0));
            product.setName(c.getString(1));
            product.setPrice(c.getDouble(2));
            list.add(product);
            c.moveToNext();
        }
        c.close();
        return list;
    }

}
