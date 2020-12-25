package com.example.moneyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MoneyActivity extends AppCompatActivity {
    //Map will be organized as Name, Amount
    Map<String,String> horz_buttons = new HashMap<String,String>();
    private boolean clicked = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moneyactivity);

//        Intent intent = getIntent();
//        String name = intent.getStringExtra("Name");
//        Integer amnt = intent.getIntExtra("Number",0);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/androidapp","root","");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM debtlist");
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                horz_buttons.put(rs.getString("Name"), rs.getString("AmountOwed"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        HorizontalScrollView horiz = findViewById(R.id.horizontalScroll);
        LinearLayout layout = findViewById(R.id.layout1);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        for(String name : horz_buttons.keySet())
        {
            Button b = new Button(this);
            b.setText(name);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(140,140);
            params.setMargins(20,50,0,0);
            b.setLayoutParams(params);
            layout.addView(b);
        }



//        Button b = new Button(this);
//        b.setText(name);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(140,140);
//        params.setMargins(20,50,0,0);
//        b.setLayoutParams(params);
//        layout.addView(b);



        FloatingActionButton handler = findViewById(R.id.headline_btn);
        FloatingActionButton add = findViewById(R.id.add_btn);
        FloatingActionButton edit = findViewById(R.id.edit_btn);

        handler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerBtnClicked();

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddingActivity();
            }
        });


    }
    public void handlerBtnClicked()
    {
        setVisibility(clicked);
        clicked = !clicked;
    }

    public void setVisibility(boolean var){
        View a = findViewById(R.id.add_btn);
        View e = findViewById(R.id.edit_btn);
        if(clicked == true)
        {
            a.setVisibility(View.VISIBLE);
            e.setVisibility(View.VISIBLE);
        }
        else{
            a.setVisibility(View.INVISIBLE);
            e.setVisibility(View.INVISIBLE);
        }
    }
    public void setClickibility()
    {

    }

    public void openAddingActivity()
    {
        Intent intent = new Intent(this,AddingActivity.class);
        startActivity(intent);
    }

}