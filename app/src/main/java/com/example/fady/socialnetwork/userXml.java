package com.example.fady.socialnetwork;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.fady.socialnetwork.data.SnaContract;
import com.example.fady.socialnetwork.data.SnaDbHelper;

import java.util.ArrayList;
import java.util.Objects;

public class userXml extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_xml);
        TextView XML = findViewById(R.id.userXML);
        String name = getIntent().getStringExtra("name");
        SnaDbHelper dbHelper = new SnaDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        StringBuffer buffer = new StringBuffer();
        String[] projection = {
                SnaContract.UsersEntry._ID,
                SnaContract.UsersEntry.COLUMN_USER_NAME,
                SnaContract.UsersEntry.COLUMN_USER_GENDER,
                SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_POSTS,
                SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_FRIENDS
        };

        Cursor c = db.query(SnaContract.UsersEntry.TABLE_NAME, projection, null, null,
                null, null, null);

        buffer.append("<Users count=\"" + c.getCount() + "\">" + "\n");
        int userId = 0;
        int nop = 0;
        int nof = 0;
        while (c.moveToNext()) {
            int nameColumnIndex = c.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NAME);
            name = c.getString(nameColumnIndex);

            int genderColumnIndex = c.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_GENDER);
            int gender = c.getInt(genderColumnIndex);
            int idColomnIndex = c.getColumnIndex(SnaContract.UsersEntry._ID);
            userId = c.getInt(idColomnIndex);
            String Gender = "unknown";
            if (gender == 0)
                Gender = "unknown";
            else if (gender == 1)
                Gender = "male";
            else if (gender == 2)
                Gender = "female";
            int nofColumnIndex = c.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_FRIENDS);
            nof = c.getInt(nofColumnIndex);

            int nopColumnIndex = c.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_POSTS);
            nop = c.getInt(nopColumnIndex);


            buffer.append("   <User>" + "\n" + "      <name>" + name + "</name>" + "\n" + "      <Gender>" +
                    Gender + "</Gender>" + "\n" + "      <number of friends> count=\"" + nof + "\">" + "\n" + "         <Friends>" + "\n");


       // userFriendsId = new ArrayList<Integer>();

        String[] projection3 = {
                SnaContract.UsersEntry._ID
        };
        String[] selArgs1 = {name};

        Cursor c3 = db.query(SnaContract.UsersEntry.TABLE_NAME, projection3,
                SnaContract.UsersEntry.COLUMN_USER_NAME + "=?",
                selArgs1,
                null, null, null);
        c3.moveToFirst();
        int idColomnIndexxx = c3.getColumnIndex(SnaContract.UsersEntry._ID);
        int userIdd = c3.getInt(idColomnIndexxx);

        //loading all friends of the user into cursor c2
        String[] projection2 = {
                SnaContract.friendsEntry.COLUMN_USER_FRIEND
        };
        String[] selArgs2 = {Integer.toString(userIdd)};
        Cursor c2 = db.query(SnaContract.friendsEntry.TABLE_NAME,
                projection2,
                SnaContract.friendsEntry.COLUMN_USER + "=?", selArgs2, null, null, null);
        int columnIndex = c2.getColumnIndex(SnaContract.friendsEntry.COLUMN_USER_FRIEND);
        ArrayList<String> IDs = new ArrayList<String>();
        while (c2.moveToNext()) {
            String s = Integer.toString(c2.getInt(columnIndex));
            IDs.add(s);
        }

        c2.close();
        String [] projection5={
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
            Cursor c5 = db.query(SnaContract.UsersEntry.TABLE_NAME, projection5, SnaContract.UsersEntry._ID + "=?", selArgs3,
                    null, null, null);
            while (c5.moveToNext()) {
                int nameColumnIndexx = c5.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NAME);
                String namee = c5.getString(nameColumnIndexx);

                int genderColumnIndexx = c5.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_GENDER);
                int genderr = c5.getInt(genderColumnIndex);

                int noffColumnIndex = c5.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_FRIENDS);
                int noff = c5.getInt(noffColumnIndex);

                int noppColumnIndex = c5.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_POSTS);
                int nopp = c5.getInt(noppColumnIndex);

                users.add(new User(namee, genderr, noff, nopp));
                String Genderr = "unknown";
                if (users.get(i).getGender() == 0)
                    Genderr = "unknown";
                else if (users.get(i).getGender() == 1)
                    Genderr = "male";
                else if (users.get(i).getGender() == 2)
                    Genderr = "female";
                buffer.append("            <user>"+"\n"+"               "+"<name>" +users.get(i).getName()+"</name>"+"\n"+
                        "               "+"<gender>" + Genderr+"</gender>"+"\n"+
                        "               "+"<nunmber of friend>" + users.get(i).getFriendsNumber()+"</number of friend>"+"\n"+
                        "               "+"<nunmber of post>" + users.get(i).getPostsNumber()+"</nunmber of post>"+"\n"+"            </user>"+"\n");
            }
            c3.close();

        }
        //for post

            buffer.append("         </Friends>"+"\n"+"      <number of posts> count=\"" + nop + "\">" +
                    "\n"+"            <posts>"+"\n");



            String [] projection7={
                    SnaContract.postsEntry._ID,
                    SnaContract.postsEntry.COLUMN_POST_OWNER_ID,
                    SnaContract.postsEntry.COLUMN_POST_TEXT
            };

                String [] selArgs3= {Integer.toString( userId)};
                Cursor c7=db.query(SnaContract.postsEntry.TABLE_NAME,
                        projection7,
                        SnaContract.postsEntry.COLUMN_POST_OWNER_ID+"=?",
                        selArgs3,
                        null,null,null);
                if(c7.getCount()==0) {
                    buffer.append("            </posts>"+"\n");
                    continue;
                }
                while(c7.moveToNext())
                {

                    int nameColumnnIndex=c7.getColumnIndex(SnaContract.postsEntry.COLUMN_POST_TEXT);
                    String text=c7.getString(nameColumnnIndex);

                    buffer.append("               "+text+"\n");

                }

                c7.close();

            buffer.append("            </posts>"+"\n");
        }

        c.close();

            buffer.append("</Users>");

            XML.setText(buffer.toString());




    }
};