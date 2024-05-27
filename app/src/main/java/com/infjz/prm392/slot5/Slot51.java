package com.infjz.prm392.slot5;

import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.infjz.prm392.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Slot51 extends AppCompatActivity {
    ListView LVResult;
    Adapter adapter;
    List<Product> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot51);
        LVResult = findViewById(R.id.slot51LVResult);
        //SQLiteHelper sqlite = new SQLiteHelper(this);
        //SQLiteDatabase db = sqlite.getReadableDatabase();
        ProductDAO dao = new ProductDAO(this);
//        Product product = new Product("1", "Asus", 15000, "1");
        Product product = new Product("3", "Dell", 30000, "3");
        int result = dao.insertProduct(product);
        list = dao.getAll();
        adapter = new Adapter(list, this);
        LVResult.setAdapter(adapter);
//        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }
}