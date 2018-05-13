package com.example.fady.socialnetwork;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fady.socialnetwork.data.SnaContract;
import com.example.fady.socialnetwork.data.SnaDbHelper;

import java.util.ArrayList;

public class addFriendList extends AppCompatActivity {

    private ArrayList<Integer> userFriendsId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend_list);
        String name=getIntent().getExtras().getString("name","default");
        SnaDbHelper dbHelper=new SnaDbHelper(this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        userFriendsId=new ArrayList<Integer>();

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
        //loading all friends of the user into cursor c2
        String [] projection2={
                SnaContract.friendsEntry.COLUMN_USER_FRIEND
        };
        String[] selArgs2={Integer.toString(userId)};
        Cursor c2=db.query(SnaContract.friendsEntry.TABLE_NAME,
                projection2,
                SnaContract.friendsEntry.COLUMN_USER+"=?",selArgs2,null,null,null);
      //  c2.moveToNext();
        if(c2.getCount()==0)
        {
            c2.close();
            displayAll(name);

        }
        else
        {

        }

    }
    void  displayAll(final String _name)
    {
        SnaDbHelper dbHelper=new SnaDbHelper(this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();

       ArrayList<User> users=new ArrayList<User>();

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
            if(name.toLowerCase().equals(_name.toLowerCase()))
            {
                continue;
            }
            int genderColumnIndex=c.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_GENDER);
            int gender=c.getInt(genderColumnIndex);

            int nofColumnIndex=c.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_FRIENDS);
            int nof=c.getInt(nofColumnIndex);

            int nopColumnIndex=c.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_POSTS);
            int nop=c.getInt(nopColumnIndex);

            users.add(new User(name,gender,nof,nop));
        }
        UserAdapter usersArray=new UserAdapter(this,users);
        ListView list=findViewById(R.id.friendList);
        list.setAdapter(usersArray);
        c.close();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListView list=findViewById(R.id.list);
                TextView name=view.findViewById(R.id.name);
                String _name2=(String) name.getText();
                _name2=_name2.replaceFirst("User Name : ","");
                //now we have the 2 friends names
                connectFriends(_name,_name2);
                view.setClickable(true);
                view.setEnabled(true);

            }
        });

    }
    private void connectFriends(String name1,String name2)
    {
        SnaDbHelper dbHelper=new SnaDbHelper(this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        //first user id
        String [] projection1={
                SnaContract.UsersEntry._ID,
                SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_FRIENDS
        };
        String[] selArgs1={name1};

        Cursor c1=db.query(SnaContract.UsersEntry.TABLE_NAME,projection1,
                SnaContract.UsersEntry.COLUMN_USER_NAME+"=?",
                selArgs1,
                null,null,null);
        c1.moveToFirst();
        int idColomnIndex1=c1.getColumnIndex(SnaContract.UsersEntry._ID);
        int userId1=c1.getInt(idColomnIndex1);
        int nofColumnIndex=c1.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_FRIENDS);
        int nof1=c1.getInt(nofColumnIndex);
        c1.close();
        //seoond user id
        String [] projection2={
                SnaContract.UsersEntry._ID,
                SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_FRIENDS
        };
        String[] selArgs2={name2};

        Cursor c2=db.query(SnaContract.UsersEntry.TABLE_NAME,projection2,
                SnaContract.UsersEntry.COLUMN_USER_NAME+"=?",
                selArgs2,
                null,null,null);
        c2.moveToFirst();
        int idColomnIndex2=c2.getColumnIndex(SnaContract.UsersEntry._ID);
        int userId2=c2.getInt(idColomnIndex2);
        int nof2=c2.getInt(nofColumnIndex);
        c2.close();
        db=dbHelper.getWritableDatabase();
        ContentValues newFriend=new ContentValues();
        newFriend.put(SnaContract.friendsEntry.COLUMN_USER,userId1);
        newFriend.put(SnaContract.friendsEntry.COLUMN_USER_FRIEND,userId2);
        long id=  db.insert(SnaContract.friendsEntry.TABLE_NAME,null,newFriend);
        if(id!=0)
        {
            Toast.makeText(this,"the selected friend was added",Toast.LENGTH_SHORT).show();
            //updating no of friends in users database
            //1
            db=dbHelper.getWritableDatabase();
            ContentValues newNoOfFriends1=new ContentValues();
            newNoOfFriends1.put(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_FRIENDS,nof1+1);
            String[] selArgs3={Integer.toString(userId1)};
            db.update(SnaContract.UsersEntry.TABLE_NAME,newNoOfFriends1,SnaContract.UsersEntry._ID+"=?",selArgs3);
            //2
            ContentValues newNoOfFriends2=new ContentValues();
            newNoOfFriends2.put(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_FRIENDS,nof2+1);
            String[] selArgs4={Integer.toString(userId2)};
            db.update(SnaContract.UsersEntry.TABLE_NAME,newNoOfFriends2,SnaContract.UsersEntry._ID+"=?",selArgs4);


        }

    }
}
