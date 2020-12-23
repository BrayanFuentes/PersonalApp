package com.example.moneyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);
        final EditText string  = (EditText) findViewById(R.id.PersonName);
        final EditText number = (EditText) findViewById(R.id.Number);
        Button add_button = (Button) findViewById(R.id.buttonadd);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = string.getText().toString();
                String n = number.getText().toString();
                Integer num = Integer.parseInt(n);
                name = name.toUpperCase();
                Intent intent = new Intent(AddingActivity.this, MoneyActivity.class);
                intent.putExtra("Name",name);
                intent.putExtra("Number",num);
                startActivity(intent);
            }
        });

    }


}