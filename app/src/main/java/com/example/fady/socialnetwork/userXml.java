package com.example.fady.socialnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.io.FileInputStream;
import com.example.fady.socialnetwork.data.SnaContract;
import com.example.fady.socialnetwork.data.SnaDbHelper;
public class userXml extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_xml);
        TextView XML=findViewById(R.id.userXML);
        SnaDbHelper dbHelper=new SnaDbHelper(this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        String [] projection={
                SnaContract.UsersEntry._ID,
                SnaContract.UsersEntry.COLUMN_USER_NAME,
                SnaContract.UsersEntry.COLUMN_USER_GENDER,
                SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_POSTS,
                SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_FRIENDS
        };

        Cursor c=db.query(SnaContract.UsersEntry.TABLE_NAME,projection,null,null,
                null,null,null);
        StringBuffer buffer=new StringBuffer();
        buffer.append("<Users count=\""+c.getCount()+"\">"+"\n");
        while(c.moveToNext())
        {
            int nameColumnIndex=c.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NAME);
            String name=c.getString(nameColumnIndex);

            int genderColumnIndex=c.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_GENDER);
            int gender=c.getInt(genderColumnIndex);
            String Gender="unknown" ;
            if(gender==0)
                Gender="unknown" ;
            else if(gender==1)
                Gender="male";
            else if(gender==2)
                Gender="female";
            int nofColumnIndex=c.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_FRIENDS);
            int nof=c.getInt(nofColumnIndex);

            int nopColumnIndex=c.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_POSTS);
            int nop=c.getInt(nopColumnIndex);


            buffer.append("   <User>"+"\n"+"      <name>"+name+"</name>"+"\n"+"      <Gender>"+Gender+"</Gender>"+"\n"+
                    "      <number of friends>"+nof+"</number of friends>"+"\n"
                    +"      <number of post>"+nop+"</number of post>"+"\n"+"   </User>"+"\n");

        }
        buffer.append("</Users>");
        XML.setText(buffer.toString());
//     //*   try
//        {
//            FileInputStream fin = openFileInput(MainActivity.fileName);
//            int size=fin.available();
//            byte[] buffer=new byte[size];
//            fin.read(buffer);
//            fin.close();
//             String text=new String(buffer);
//            XML.setText(text);
//
//        }
//        catch (Exception e)
//        {
//
//
//        }
        c.close();
    }
};