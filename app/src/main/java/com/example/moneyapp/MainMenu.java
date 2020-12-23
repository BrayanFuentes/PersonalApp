package com.example.moneyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        button = (Button) findViewById(R.id.button3);
        button .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMoneyActivity();
            }
        });

    }
    public void openMoneyActivity()
    {
        Intent intent = new Intent(this,MoneyActivity.class);
        startActivity(intent);
    }
}