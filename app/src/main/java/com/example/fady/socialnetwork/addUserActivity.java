package com.example.fady.socialnetwork;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class addUserActivity extends AppCompatActivity {

    public static ArrayList<User> addedUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        //add user button
        Button addUser=findViewById(R.id.addUser);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText addeduser=findViewById(R.id.addedUser);
                //reading from file
                String readText="";
                String writeText="";
                try
                {
                    File file=getBaseContext().getFileStreamPath(MainActivity.fileName);
                    if(file.exists())
                    {
                        //adding the user to the array
                        addedUsers.add(new User(addeduser.getText().toString()));
                        //file is created and needs to be modified
                        FileInputStream fin=openFileInput(MainActivity.fileName);
                        int size=fin.available();
                        byte[] buffer=new byte[size];
                        fin.read(buffer);
                        fin.close();
                        readText=new String(buffer);
                        int pos=readText.indexOf("count=\"");
                        pos+=7;
                        int lastpos=readText.indexOf("\">");
                        int count=Integer.parseInt(readText.substring(pos,lastpos));
                       readText= readText.replaceFirst(readText.substring(pos,lastpos),Integer.toString(count+1));
                        readText= readText.replaceFirst("</Users>","");
                        readText+="\t<name=\""+addeduser.getText().toString().toUpperCase()+"\"  noOfPosts=\"0\"  noOfFriends=\"0\"/>\n";
                        readText+="</Users>";
                        //modifying the file
                        FileOutputStream fout=openFileOutput(MainActivity.fileName,Context.MODE_PRIVATE);
                        fout.write(readText.getBytes());
                        fout.close();
                        Toast.makeText(addUserActivity.this,"new User add to the network",Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        //file not created
                        FileOutputStream fout=openFileOutput(MainActivity.fileName,Context.MODE_PRIVATE);
                        writeText=new String("<Users count=\"1\"> \n");
                        writeText+="\t<name=\""+addeduser.getText().toString().toUpperCase()+"\" noOfPosts=\"0\" noOfFriends=\"0\"/>\n";
                        writeText+="</Users>";
                        fout.write(writeText.getBytes());
                        fout.close();
                        addedUsers=new ArrayList<>();
                        addedUsers.add(new User(addeduser.getText().toString()));
                    }
                   }
                catch (Exception e)
                {
                    addedUsers=new ArrayList<>();
                    e.printStackTrace();
                    Toast.makeText(addUserActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
