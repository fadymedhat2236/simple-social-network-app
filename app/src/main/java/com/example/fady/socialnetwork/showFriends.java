package com.example.fady.socialnetwork;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.fady.socialnetwork.data.SnaContract;
import com.example.fady.socialnetwork.data.SnaDbHelper;

import java.util.ArrayList;

public class showFriends extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_friends);
        String name=getIntent().getExtras().getString("name","default");
        SnaDbHelper dbHelper=new SnaDbHelper(this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        //getting id of the user from the name
        String [] projection={
                SnaContract.UsersEntry._ID
        };
        String[] selArgs1={name};

        Cursor c=db.query(SnaContract.UsersEntry.TABLE_NAME,projection,
                SnaContract.UsersEntry.COLUMN_USER_NAME+"=?",
                selArgs1,
                null,null,null);
        c.moveToFirst();
        int idColomnIndex=c.getColumnIndex(SnaContract.UsersEntry._ID);
        int userId=c.getInt(idColomnIndex);
        c.close();

        //reading the friends table where id==userID

        String []projection2={SnaContract.friendsEntry.COLUMN_USER_FRIEND};
        String[] selArgs2={Integer.toString(userId)};
        Cursor c2=db.query(SnaContract.friendsEntry.TABLE_NAME,
                projection2,
                SnaContract.friendsEntry.COLUMN_USER+"=?",selArgs2,null,null,null);
        int columnIndex=c2.getColumnIndex(SnaContract.friendsEntry.COLUMN_USER_FRIEND);
        ArrayList<String> IDs=new ArrayList<String>();
        while(c2.moveToNext())
        {
            String s=Integer.toString(c2.getInt(columnIndex));
            IDs.add(s);
        }
        c2.close();
        String [] projection3={
                SnaContract.UsersEntry._ID,
                SnaContract.UsersEntry.COLUMN_USER_NAME,
                SnaContract.UsersEntry.COLUMN_USER_GENDER,
                SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_POSTS,
                SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_FRIENDS
        };

        ArrayList<User> users = new ArrayList<User>();
        for(int i=0;i< IDs.size();i++)
        {
            String [] selArgs3= {IDs.get(i)};
            Cursor c3 = db.query(SnaContract.UsersEntry.TABLE_NAME, projection3, SnaContract.UsersEntry._ID + "=?", selArgs3,
                    null, null, null);
            while (c3.moveToNext()) {
                int nameColumnIndex = c3.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NAME);
                String n = c3.getString(nameColumnIndex);

                int genderColumnIndex = c3.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_GENDER);
                int gender = c3.getInt(genderColumnIndex);

                int nofColumnIndex = c3.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_FRIENDS);
                int nof = c3.getInt(nofColumnIndex);

                int nopColumnIndex = c3.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_POSTS);
                int nop = c3.getInt(nopColumnIndex);

                users.add(new User(n, gender, nof, nop));
            }
            c3.close();
        }
        UserAdapter usersArray=new UserAdapter(this,users);
        ListView list=findViewById(R.id.showFriendsList);
        list.setAdapter(usersArray);

    }
}
