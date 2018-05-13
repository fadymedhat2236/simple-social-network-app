package com.example.fady.socialnetwork;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.fady.socialnetwork.data.SnaContract;
import com.example.fady.socialnetwork.data.SnaDbHelper;

import java.util.ArrayList;

public class UserPosts extends AppCompatActivity {

    public static final int PET_LOADER = 0;

    private ArrayList<Post> posts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_posts);
        String name=getIntent().getExtras().getString("name","default");
        this.setTitle("this is "+name+"'s posts");


        //to view the posts
        SnaDbHelper dbHelper=new SnaDbHelper(this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        posts=new ArrayList<Post>();

        String [] projection={
                SnaContract.postsEntry._ID,
                SnaContract.postsEntry.COLUMN_POST_OWNER_ID,
                SnaContract.postsEntry.COLUMN_POST_TEXT
        };

        Cursor c=db.query(SnaContract.postsEntry.TABLE_NAME,projection,null,null,
                null,null,null);

        while(c.moveToNext())
        {

            int nameColumnIndex=c.getColumnIndex(SnaContract.postsEntry.COLUMN_POST_TEXT);
            String text=c.getString(nameColumnIndex);


            posts.add(new Post(name,text));
        }
        PostAdapter postsArray=new PostAdapter(this,posts);
        ListView list=findViewById(R.id.postList);
        list.setAdapter(postsArray);
        c.close();

    }
}
