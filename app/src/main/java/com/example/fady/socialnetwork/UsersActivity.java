package com.example.fady.socialnetwork;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fady.socialnetwork.data.SnaContract;
import com.example.fady.socialnetwork.data.SnaDbHelper;

import java.util.ArrayList;

public class UsersActivity extends AppCompatActivity
{

    public static final int PET_LOADER = 0;

    private ArrayList<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);


        SnaDbHelper dbHelper=new SnaDbHelper(this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        users=new ArrayList<User>();

        String [] projection={
                SnaContract.UsersEntry._ID,
                SnaContract.UsersEntry.COLUMN_USER_NAME,
                SnaContract.UsersEntry.COLUMN_USER_GENDER,
                SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_POSTS,
                SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_FRIENDS
        };

        Cursor c=db.query(SnaContract.UsersEntry.TABLE_NAME,projection,null,null,
                null,null,null);

        while(c.moveToNext())
        {
            int nameColumnIndex=c.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NAME);
            String name=c.getString(nameColumnIndex);

            int genderColumnIndex=c.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_GENDER);
            int gender=c.getInt(genderColumnIndex);

            int nofColumnIndex=c.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_FRIENDS);
            int nof=c.getInt(nofColumnIndex);

            int nopColumnIndex=c.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_POSTS);
            int nop=c.getInt(nopColumnIndex);

            users.add(new User(name,gender,nof,nop));
        }
        UserAdapter usersArray=new UserAdapter(this,users);
        ListView list=findViewById(R.id.list);
        list.setAdapter(usersArray);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListView list=findViewById(R.id.list);
                TextView name=view.findViewById(R.id.name);
                String n=(String) name.getText();
                n=n.replaceFirst("User Name : ","");
                Intent intent=new Intent(UsersActivity.this,UserOptionsActivity.class).putExtra("name",n);

                startActivity(intent);
            }
        });
    }

}
