package com.infjz.prm392.slot6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String SQL_CREATE_TABLE_PRODUCT ="create table Product (\n" +
            "ProductCode text PRIMARY KEY,\n" +
            "   ProductName text,\n" +
            "   ProductQuantity text\n" +
            ");";
    public SQLiteHelper(Context context){
        super(context, "QLSP", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Product;");
    }



}
