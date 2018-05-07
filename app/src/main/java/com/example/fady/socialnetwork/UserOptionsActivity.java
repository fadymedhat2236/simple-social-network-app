package com.example.fady.socialnetwork;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.fady.socialnetwork.data.SnaContract;
import com.example.fady.socialnetwork.data.SnaDbHelper;

public class UserOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_options);
        String name=getIntent().getExtras().getString("name","default");

        //setting the label of the activity
     this.setTitle("this is "+name+"'s home page");
     //add friends activity
        LinearLayout AddFriends=findViewById(R.id.add_friends);
        AddFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //delete friends activity
        LinearLayout DeleteFriends=findViewById(R.id.delete_friends);
        DeleteFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //add posts activity
        LinearLayout AddPosts=findViewById(R.id.add_post);
        AddPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),addPostActivity.class);
                startActivity(i);
            }
        });

        //show posts activity
        LinearLayout ShowPosts=findViewById(R.id.show_posts);
        ShowPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
