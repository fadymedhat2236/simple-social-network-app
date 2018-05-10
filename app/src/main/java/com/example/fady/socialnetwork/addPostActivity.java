package com.example.fady.socialnetwork;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fady.socialnetwork.data.SnaContract;
import com.example.fady.socialnetwork.data.SnaDbHelper;

public class addPostActivity extends AppCompatActivity {
    private EditText postBody;
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        String name=getIntent().getExtras().getString("name","default");
       this.userName=name;
        postBody=findViewById(R.id.post_text);
        //here implement what happens when clicking addPost button
        Button addPostButton=findViewById(R.id.addPost);
        addPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPost();
            }
        });
    }

    public void addPost()
    {
        //get user id
        SnaDbHelper dbHelper=new SnaDbHelper(this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String[] projection1={SnaContract.UsersEntry._ID,
        SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_POSTS};
        String[] selArgs={userName};
        Cursor c=db.query(SnaContract.UsersEntry.TABLE_NAME,
                projection1,
                SnaContract.UsersEntry.COLUMN_USER_NAME+"=?",
                selArgs,
                null,null,null);
        c.moveToNext();
        int id_column_index=c.getColumnIndex(SnaContract.UsersEntry._ID);
        int userID=c.getInt(id_column_index);
        int nopColumnIndex=c.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_POSTS);
        int nop=c.getInt(nopColumnIndex);
        //userId and his number of posts are obtained correctly


        //dbHelper=new SnaDbHelper(this);
        db=dbHelper.getWritableDatabase();
         postBody=findViewById(R.id.post_text);
        String postText=postBody.getText().toString().trim();
        ContentValues newPost=new ContentValues();
        newPost.put(SnaContract.postsEntry.COLUMN_POST_OWNER_ID,userID);
        newPost.put(SnaContract.postsEntry.COLUMN_POST_TEXT,postText);
      long id=  db.insert(SnaContract.postsEntry.TABLE_NAME,null,newPost);
      if(id!=0)
      {
          Toast.makeText(this,"your post was added",Toast.LENGTH_SHORT).show();
          //updating no of posts in users database
          db=dbHelper.getWritableDatabase();
          ContentValues newNoOfPosts=new ContentValues();
          newNoOfPosts.put(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_POSTS,nop+1);
          String[] selArgs2={Integer.toString(userID)};
          db.update(SnaContract.UsersEntry.TABLE_NAME,newNoOfPosts,SnaContract.UsersEntry._ID+"=?",selArgs2);
          Intent i=new Intent(this,UsersActivity.class);
          startActivity(i);
      }
      else
      {
          Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
      }



    }




}
