package com.example.fady.socialnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class userActivity extends AppCompatActivity {
    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        if(addUserActivity.addedUsers==null)
        {
            //dummy data
            users = new ArrayList<>();
            users.add(new User("fady faragallah", 12, 10));
            users.add(new User("fady medhat", 2, 30));
            users.add(new User("fady george", 16, 8));
            users.add(new User("sara sami", 20, 24));
            users.add(new User("fady faragallah", 12, 10));
        }
        else
        {
            users=addUserActivity.addedUsers;
        }
        UserAdapter usersArray=new UserAdapter(this,users);
        ListView list=findViewById(R.id.list);
        list.setAdapter(usersArray);



    }
}
