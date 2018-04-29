package com.example.fady.socialnetwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileInputStream;

public class adminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        LinearLayout addUser=findViewById(R.id.add_user);
        LinearLayout deleteUser=findViewById(R.id.delete_user);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(view.getContext(),addUserActivity.class);
                startActivity(i);
            }
        });
        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(view.getContext(),deleteUserActivity.class);
                startActivity(i);
            }
        });
        LinearLayout XML_user=findViewById(R.id.viewXML);
        XML_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(view.getContext(),userXml.class);
                startActivity(i2);

            }
        });

    }
}
