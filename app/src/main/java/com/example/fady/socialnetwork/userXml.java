package com.example.fady.socialnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileInputStream;

public class userXml extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_xml);
        TextView XML=findViewById(R.id.userXML);
        try
        {
            FileInputStream fin = openFileInput(MainActivity.fileName);
            int size=fin.available();
            byte[] buffer=new byte[size];
            fin.read(buffer);
            fin.close();
            String text=new String(buffer);
            XML.setText(text);

        }
        catch (Exception e)
        {

        }
    }
}
