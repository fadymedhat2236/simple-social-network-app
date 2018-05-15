package com.example.fady.socialnetwork;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.fady.socialnetwork.data.SnaContract;
import com.example.fady.socialnetwork.data.SnaDbHelper;

import java.util.ArrayList;

public class sortByPosts extends AppCompatActivity {

    private ArrayList<userField> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_by_posts);
        users=new ArrayList<userField>();
        SnaDbHelper dbHelper=new SnaDbHelper(this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String [] projection={
                SnaContract.UsersEntry.COLUMN_USER_NAME,
                SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_POSTS
        };

        Cursor c=db.query(SnaContract.UsersEntry.TABLE_NAME,
                projection,null,null,null,null,null);
        heap h1=new heap();

        h1.initialize(c,"numberOfPosts");
        int n=h1.getCount();
        int count=1;
        int oldNumber=-1;
        for(int i=0;i<n;i++)
        {
            heapNode node=h1.getMax();
            if(i==0) {
                oldNumber = node.getNumber();
            }
            else
            {
                if(node.getNumber()<oldNumber) {
                    count++;
                    oldNumber=node.getNumber();
                }
            }
            users.add(new userField(node.getName(), "number of posts:" + Integer.toString(node.getNumber()), count));
        }
        UserFieldAdapter usersArray=new UserFieldAdapter(this,users);
        ListView list=findViewById(R.id.posts_field_List);
        list.setAdapter(usersArray);
    }
}
