package com.infjz.prm392.slot6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    private Context context;
    public static final String TABLE_NAME = "Product";
    public ProductDAO(Context context) {
        this.context = context;
        dbHelper = new SQLiteHelper(context); //Create database
        db = dbHelper.getWritableDatabase(); //Allow write data to database
    }
    public int insertProduct(Product p){
        ContentValues values = new ContentValues();
        values.put("ProductCode", p.getProductCode());
        values.put("ProductName", p.getProductName());
        values.put("ProductQuantity", String.valueOf(p.getProductQuantity()));
        if (db.insert(TABLE_NAME, null, values)<=0){
            return -1; //Unsuccessful
        }
        return 1;//Successful
    }
    public int deleteProduct(String ProductCode){
      if  (db.delete(TABLE_NAME, "ProductCode=?", new String[]{ProductCode})<=0){
          return -1;
      }
      return 1;
    }
    public int updateProduct(Product p){
        ContentValues values = new ContentValues();
        values.put("ProductCode", p.getProductCode());
        values.put("ProductName", p.getProductName());
        values.put("ProductQuantity", String.valueOf(p.getProductQuantity()));
        if (db.update(TABLE_NAME, values, "ProductCode=?", new String[]{p.getProductCode()})<=0){
            return -1;
        }
        return 1;
    }
    public List<Product> getAllProduct(){
        List<Product> ls = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            Product p = new Product();
            p.setProductCode(c.getString(0));
            p.setProductName(c.getString(1));
            p.setProductQuantity(c.getInt(2));
            ls.add(p);
            c.moveToNext();
        }
        c.close();
        return ls;
    }

    public List<String> getAllProductToString(){
        List<String> ls = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            Product p = new Product();
            p.setProductCode(c.getString(0));
            p.setProductName(c.getString(1));
            p.setProductQuantity(c.getInt(2));
            String str = p.getProductCode()+" - "+p.getProductName()+" - "+p.getProductQuantity();
            ls.add(str);
            c.moveToNext();
        }
        c.close();
        return ls;
    }

}
