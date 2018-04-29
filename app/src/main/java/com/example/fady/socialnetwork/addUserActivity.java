package com.example.fady.socialnetwork;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fady.socialnetwork.data.SnaContract;
import com.example.fady.socialnetwork.data.SnaDbHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class addUserActivity extends AppCompatActivity {

    EditText name;
    EditText gender;
    Button addUserButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        name=(EditText)findViewById(R.id.name);
        gender=(EditText)findViewById(R.id.gender);
        addUserButton=(Button) findViewById(R.id.addUser);


    }

    public void insertUser(View v) {
        String n = name.getText().toString().trim();
        String g = gender.getText().toString().trim().toLowerCase();
        int temp;
        if (g == "male")
            temp = 1;
        else if (g == "female")
            temp = 2;
        else
            temp = 0;


        ContentValues c = new ContentValues();
        c.put(SnaContract.UsersEntry.COLUMN_USER_NAME, n);
        c.put(SnaContract.UsersEntry.COLUMN_USER_GENDER, g);
        c.put(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_FRIENDS, 0);
        c.put(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_POSTS, 0);

        SnaDbHelper dbHelper=new SnaDbHelper(this);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        long id=db.insert(SnaContract.UsersEntry.TABLE_NAME,null,c);

        if (id != -1) {

            Toast.makeText(addUserActivity.this,"new User was add to the network",Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(addUserActivity.this,"an error occured",Toast.LENGTH_SHORT).show();
        }
    }

}
