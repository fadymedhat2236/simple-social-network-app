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
        String name=getIntent().getExtras().getString("name","default");

        fields=new ArrayList<userField>();
        ArrayList<Node> nodes=new ArrayList<>();
        //getting the parent id
        String [] projection={
                SnaContract.UsersEntry._ID,
        };
        String[] selArgs={name};
        Cursor c=db.query(SnaContract.UsersEntry.TABLE_NAME,projection,
                SnaContract.UsersEntry.COLUMN_USER_NAME+"=?",
                selArgs,
                null,null,null);
        c.moveToNext();
        int idColumnIndex=c.getColumnIndex(SnaContract.UsersEntry._ID);
        int parentId=c.getInt(idColumnIndex);
        c.close();
        Graph graph=new Graph(new Node(parentId));
        //getting the rest of the network ids
        Cursor c2=db.query(
                SnaContract.UsersEntry.TABLE_NAME,projection,
                SnaContract.UsersEntry.COLUMN_USER_NAME+"!=?",
                selArgs,null,null,null
        );
        while(c2.moveToNext())
        {
            idColumnIndex=c2.getColumnIndex(SnaContract.UsersEntry._ID);
            int friendId=c2.getInt(idColumnIndex);
            graph.addToNetwork(new Node(friendId));
        }
        c2.close();
        graph.constructGraph(db);
        nodes=graph.getNear();
        int counter=1;
        for(int i=0;i<nodes.size();i++)
        {
            String[] projection2={SnaContract.UsersEntry.COLUMN_USER_NAME};
            String[] selArgs2={Integer.toString(nodes.get(i).getId())};
            Cursor c3=db.query(
                    SnaContract.UsersEntry.TABLE_NAME,projection2,
                    SnaContract.UsersEntry._ID+"=?",selArgs2,
                    null,null,null
            );
            c3.moveToNext();
            int nameColumnIndex=c3.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NAME);
            String username=c3.getString(nameColumnIndex);
            c3.close();
            fields.add(new userField(username,"min path: "+Integer.toString(nodes.get(i).getDistance()),counter));
            counter++;
        }
        UserFieldAdapter fieldsArray=new UserFieldAdapter(this,fields);
        ListView list=findViewById(R.id.fieldList);
        list.setAdapter(fieldsArray);
        c.close();
    }
}
