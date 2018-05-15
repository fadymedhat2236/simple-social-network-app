package com.example.fady.socialnetwork;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.fady.socialnetwork.data.SnaContract;
import com.example.fady.socialnetwork.data.SnaDbHelper;

import java.util.ArrayList;

public class sortByLikes extends AppCompatActivity {

    private ArrayList<userField> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_by_likes);
        users=new ArrayList<userField>();
        SnaDbHelper dbHelper=new SnaDbHelper(this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String [] projection1={
                SnaContract.UsersEntry.COLUMN_USER_NAME
        };
        Cursor c1=db.query(SnaContract.UsersEntry.TABLE_NAME,
                projection1,null,null,null,null,null);
        ArrayList<String> names=new ArrayList<String>();
        while(c1.moveToNext())
        {
            int x=c1.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NAME);
            names.add(c1.getString(x));
        }
        int size=c1.getCount();
        c1.close();

        String [] projection2={
                SnaContract.postsLikes.COLUMN_POST_LIKER_ID
        };
        Cursor c2=db.query(SnaContract.postsLikes.TABLE_NAME,
                projection2,null,null,null,null,null);
        heap h1=new heap();

        h1.initialize(c2,names);
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
            users.add(new userField(node.getName(), "number of likes:" + Integer.toString(node.getNumber()), count));
        }
        UserFieldAdapter usersArray=new UserFieldAdapter(this,users);
        ListView list=findViewById(R.id.likes_field_List);
        list.setAdapter(usersArray);
    }
}
