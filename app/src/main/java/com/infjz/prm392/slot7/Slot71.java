package com.infjz.prm392.slot7;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.infjz.prm392.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Slot71 extends AppCompatActivity {
    Context context = this;
    EditText EDTInput;
    TextView TVResult;
    Button BTNSave, BTNRead;

    EditText EDTUsername, EDTPassword;
    Button BTNLogin, BTNCancel;
    CheckBox CBSavePass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.slot71_login_layout);
        EDTUsername = findViewById(R.id.slot71EDTUsername);
        EDTPassword = findViewById(R.id.slot71EDTPassword);
        BTNLogin = findViewById(R.id.slot71BTNLogin);
        BTNCancel = findViewById(R.id.slot71BTNCancel);
        CBSavePass = findViewById(R.id.slot71CBSavePass);
        resetPassword();
        BTNLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        /*
        EDTInput = findViewById(R.id.slot71EDTInput);
        TVResult = findViewById(R.id.slot71TVResult);
        BTNRead = findViewById(R.id.slot71BTNRead);
        BTNSave = findViewById(R.id.slot71BTNSave);
        requestPermission();
        BTNSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(EDTInput.getText().toString(), context);
            }
        });
        BTNRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = readData(context);
                TVResult.setText(data);
            }
        });
        */
    }

    String username, password;
    public void login(){
        username = EDTUsername.getText().toString();
        password = EDTPassword.getText().toString();
        if (username.isEmpty() || password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Username or Password is not empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")){
            saveDataToPreference(username, password, CBSavePass.isChecked());
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
        }
    }

    //Save data to file function
    public void saveDataToFile(String data){
        // Step 1: Get path
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/data1.txt";
        // Step 2: Create a stream for saving
        try {
            OutputStreamWriter o = new OutputStreamWriter(new FileOutputStream(path));
            // Step 3: Write data
            o.write(data);
            o.close();
        }catch (FileNotFoundException e){
            throw  new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean requestPermission(){ // Version after android api 23
        if (Build.VERSION.SDK_INT >= 23){
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.MANAGE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                return true;
            }
            else{
                ActivityCompat.requestPermissions(Slot71.this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.MANAGE_EXTERNAL_STORAGE
                }, 1);
                return false;
            }
        }
        else{
            return true;
        }
    }

    public String saveData(String data, Context context){ // With android api after 32
        String path = "";
        ContextWrapper contextWrapper = new ContextWrapper(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1){
            path = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_DCIM)+"/data1.txt";
        }
        else{
            path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/data1.txt";
        }
        // Step 2: Create a stream for saving
        try {
            OutputStreamWriter o = new OutputStreamWriter(new FileOutputStream(path));
            // Step 3: Write data
            o.write(data);
            o.close();
            return "Save data successful";
        } catch (FileNotFoundException e){
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String readData(Context context){ // With android api after 32
        String result = "";
        String path = "";
        ContextWrapper contextWrapper = new ContextWrapper(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1){
            path = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_DCIM)+"/data1.txt";
        }
        else{
            path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/data1.txt";
        }
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNext()){
                result = scanner.nextLine()+"\n";
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public String readDataFromFile(){
        String data = "";
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/data1.txt";
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNext()){
                data = scanner.nextLine()+"\n";
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public void saveDataToPreference(String username, String password, boolean status){
        // Step 1: Create file for saving
        SharedPreferences sharedPreferences = getSharedPreferences("H_FILE", MODE_PRIVATE);
        // Step 2: Enable editor mode
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!status){ // If exist password
            editor.clear();
        }
        else{ // Save new data
            editor.putString("USERNAME", username);
            editor.putString("PASSWORD", password);
            editor.putBoolean("REMEMBER", status);
        }
        // Step 3: Commit data to file
        editor.commit();
    }

    public List<Object> resetPassword(){
        List<Object> list = new ArrayList<>();
        // Step 1: Open file
        SharedPreferences sharedPreferences = getSharedPreferences("H_FILE", MODE_PRIVATE);
        // Step 2: Check request
        boolean check = sharedPreferences.getBoolean("REMEMBER", false);
        if (check){
            String username = sharedPreferences.getString("USERNAME", "");
            EDTUsername.setText(username);
            String password = sharedPreferences.getString("PASSWORD", "");
            EDTPassword.setText(password);
            list.add(username);
            list.add(password);
            list.add(check);
        }
        CBSavePass.setChecked(check);
        return list;
    }


}