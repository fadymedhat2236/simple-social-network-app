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
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_options);
        String name=getIntent().getExtras().getString("name","default");

        //setting the label of the activity
     this.setTitle("this is "+name+"'s home page");
     userName=name;
     //add friends activity
        LinearLayout AddFriends=findViewById(R.id.add_friends);
        AddFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),addFriendList.class).putExtra("name",userName);
                startActivity(i);
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
                Intent i=new Intent(v.getContext(),addPostActivity.class).putExtra("name",userName);
                startActivity(i);
            }
        });

        //show posts activity
        LinearLayout ShowPosts=findViewById(R.id.show_posts);
        ShowPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),UserPosts.class).putExtra("name",userName);
                startActivity(i);
            }
        });

        //show friends
        LinearLayout showFriends=findViewById(R.id.show_friends);
        showFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),showFriends.class).putExtra("name",userName);
                startActivity(i);
            }
        });

        //sugget friends
        LinearLayout suggestFriends=findViewById(R.id.suggest_friend);
        suggestFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),usersGraph.class).putExtra("name",userName);
                startActivity(i);
            }
        });

    }
}
