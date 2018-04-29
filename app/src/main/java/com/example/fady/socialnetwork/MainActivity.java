package com.example.fady.socialnetwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    public static String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // file name
        fileName="Users.txt";
        //set on click for the admin layout
        LinearLayout admin_layout= findViewById(R.id.admin);
        admin_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(),adminActivity.class);
                startActivity(i);
            }
        });
        //set on click for the user layout
        LinearLayout user_layout= findViewById(R.id.user);
        user_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(),usersActivity.class);
                startActivity(i);
            }
        });
        //set on click for the group layout
        LinearLayout group_layout= findViewById(R.id.group);
        group_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(),groupActivity.class);
                startActivity(i);
            }
        });




    }
}
