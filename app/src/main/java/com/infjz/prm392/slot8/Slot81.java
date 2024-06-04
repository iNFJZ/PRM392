package com.infjz.prm392.slot8;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.infjz.prm392.R;

public class Slot81 extends AppCompatActivity {
    TextView TVEmailSetup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot81);
        TVEmailSetup = findViewById(R.id.slot81TVEmailSetup);
        // Copy font to android system
        Typeface font = Typeface.createFromAsset(getAssets(), "JetBrainsMonoNLNerdFont-Regular.ttf");
        // Use for textview
        TVEmailSetup.setTypeface(font);
    }
}