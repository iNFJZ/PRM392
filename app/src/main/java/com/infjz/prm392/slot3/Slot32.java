package com.infjz.prm392.slot3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.infjz.prm392.R;

public class Slot32 extends AppCompatActivity {
    //Declare variable
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot32);
        //Reference
        listView = findViewById(R.id.slot32LV);
        getDataToListView();

    }

    private void getDataToListView(){
        //1. Create data source
        String[] arr = new String[]{
                "Java Programming",
                "Computer Science Introduction",
                ".NET Programming",
                "Mobile Programming",
                "PHP Programming",
                "Laravel Framework",
                "JavaScript Programming",
        };

        //2. Using adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Slot32.this,
                android.R.layout.simple_list_item_1,arr);

        //3. Attach adapter to ListView
        listView.setAdapter(adapter);
    }
}