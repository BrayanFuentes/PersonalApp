package com.example.moneyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                try {
                    boolean doesExist = false;
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/androidapp","root","");
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM debtlist WHERE Name ='"+ name +"'");
                    ResultSet rs = statement.executeQuery();
                    if(rs.next()) {
                        doesExist = true;
                    }
                    if(doesExist == false)
                    {
                        PreparedStatement stmnt = connection.prepareStatement("INSERT INTO debtlist VALUES ('"+name+"','" + n + "')");
                        stmnt.executeUpdate();
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(AddingActivity.this, MoneyActivity.class);
                startActivity(intent);
            }
        });

    }


}