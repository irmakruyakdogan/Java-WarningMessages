package com.yirmak.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Welcome User", Toast.LENGTH_LONG).show();

        editText = findViewById(R.id.editTextNumber);
        textView = findViewById(R.id.textView);


        sharedPreferences = this.getSharedPreferences("com.yirmak.storingdata", Context.MODE_PRIVATE);

        int storedAge = sharedPreferences.getInt("storedAge",0);

        if (storedAge == 0) {
            textView.setText("Your age: ");
        } else {
            textView.setText("Your age: " + storedAge);
        }

    }


    public void save(View view) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Save");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Not Saved", Toast.LENGTH_LONG).show();
            }
        });
        alert.show();

        if (!editText.getText().toString().matches("")) {
            int userAge = Integer.parseInt(editText.getText().toString());
            textView.setText("Your age: " + userAge);


            sharedPreferences.edit().putInt("storedAge",userAge).apply();


        }

    }


    public void delete(View view ) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Delete");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Not Deleted", Toast.LENGTH_LONG).show();
            }
        });
        alert.show();

        int storedData = sharedPreferences.getInt("storedAge",0);

        if (storedData != 0) {
            sharedPreferences.edit().remove("storedAge").apply();
            textView.setText("Your age: ");
        }



    }
}