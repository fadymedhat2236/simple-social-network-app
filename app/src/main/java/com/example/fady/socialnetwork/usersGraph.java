package com.example.fady.socialnetwork;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.fady.socialnetwork.data.SnaContract;
import com.example.fady.socialnetwork.data.SnaDbHelper;

import java.util.ArrayList;

public class usersGraph extends AppCompatActivity {
ArrayList<userField> fields;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_graph);
        SnaDbHelper dbHelper=new SnaDbHelper(this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        fields=new ArrayList<userField>();

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

            fields.add(new userField(name,"min path=1",1));
        }
        UserFieldAdapter fieldsArray=new UserFieldAdapter(this,fields);
        ListView list=findViewById(R.id.fieldList);
        list.setAdapter(fieldsArray);
        c.close();
    }
}
