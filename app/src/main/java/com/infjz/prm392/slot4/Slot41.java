package com.infjz.prm392.slot4;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.infjz.prm392.R;

import java.util.ArrayList;
import java.util.List;

public class Slot41 extends AppCompatActivity {
    private ListView listView;
    private Slot41BaseAdapter adapter;
    private List<Slot41Student> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot41);
        listView = findViewById(R.id.slot41LV);
        list.add(new Slot41Student("Nguyen Tien Dung","21", R.drawable.apple));
        list.add(new Slot41Student("Vu Thuy Trang","22", R.drawable.facebook));
        list.add(new Slot41Student("Bui Quang Truong","21", R.drawable.hancock));
        list.add(new Slot41Student("Nguyen Tien A","21", R.drawable.android));
        list.add(new Slot41Student("Nguyen Tien B","21", R.drawable.hp));
        list.add(new Slot41Student("Nguyen Tien C","21", R.drawable.blogger));
        list.add(new Slot41Student("Nguyen Tien D","21", R.drawable.chrome));
        //Create adapter
        adapter = new Slot41BaseAdapter(this, list);
        //Attach adapter to listView
        listView.setAdapter(adapter);
    }
}